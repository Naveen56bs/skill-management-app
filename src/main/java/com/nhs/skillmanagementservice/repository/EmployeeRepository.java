package com.nhs.skillmanagementservice.repository;

import com.nhs.skillmanagementservice.repository.entity.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {

    EmployeeEntity findByEmployeeId(String employeeId);

    Integer deleteByEmployeeId(String employeeId);

    List<EmployeeEntity> findByEmployeeIdIn(List<String> employeeIds);

    Page<EmployeeEntity> findBySkillsetManagementEntitiesSkillManagementIdSkillIdAndSkillsetManagementEntitiesLevel
            (String skillId, String level, Pageable pageRequest);

}
