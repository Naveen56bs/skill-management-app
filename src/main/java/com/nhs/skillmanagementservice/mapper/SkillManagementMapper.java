package com.nhs.skillmanagementservice.mapper;

import com.nhs.skillmanagementservice.model.response.wrapper.SkillManagementResponse;
import com.nhs.skillmanagementservice.model.SkillAndLevel;
import com.nhs.skillmanagementservice.repository.entity.SkillsetManagementEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Entity - Model Converter for SkillsetManagement objects.
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface SkillManagementMapper {

    /**
     * Convert List of SkillsetManagementEntity to model SkillManagementResponse.
     *
     * @param skillsetManagementEntities List of SkillsetManagementEntity
     * @return SkillManagementResponse model
     */
    default SkillManagementResponse skillManagementEntityToSkillManagementResp(List<SkillsetManagementEntity> skillsetManagementEntities) {
        SkillManagementResponse skillManagementResponse = new SkillManagementResponse();
        skillManagementResponse.setEmployeeFirstName(skillsetManagementEntities.get(0).getEmployeeEntity().getEmployeeFirstName());
        skillManagementResponse.setEmployeeLastName(skillsetManagementEntities.get(0).getEmployeeEntity().getEmployeeLastName());
        List<SkillAndLevel> skillAndLevels =
                skillsetManagementEntities.stream()
                        .map(skillsetManagementEntity -> {
                            SkillAndLevel s = new SkillAndLevel();
                            s.setSkillId(skillsetManagementEntity.getSkillsetEntity().getSkillId());
                            s.setLevel(skillsetManagementEntity.getLevel());
                            return s;
                        }).collect(Collectors.toList());
        skillManagementResponse.setSkillAndLevels(skillAndLevels);
        return skillManagementResponse;
    }

}

