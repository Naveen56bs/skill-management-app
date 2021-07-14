package com.nhs.skillmanagementservice.repository;

import com.nhs.skillmanagementservice.repository.entity.SkillsetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface SkillSetRepository extends JpaRepository<SkillsetEntity, String> {

    SkillsetEntity findBySkillId(String skillId);

    Integer deleteBySkillName(String skillName);

    List<SkillsetEntity> findBySkillIdIn(List<String> skillIds);
}
