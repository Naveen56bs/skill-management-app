package com.nhs.skillmanagementservice.repository;

import com.nhs.skillmanagementservice.repository.entity.SkillManagementId;
import com.nhs.skillmanagementservice.repository.entity.SkillsetManagementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface SkillsetManagementRepository extends JpaRepository<SkillsetManagementEntity, SkillManagementId> {

}
