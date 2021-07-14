package com.nhs.skillmanagementservice.mapper;

import com.nhs.skillmanagementservice.model.request.SkillRequest;
import com.nhs.skillmanagementservice.model.response.Skill;
import com.nhs.skillmanagementservice.model.response.wrapper.SkillsetPageResponse;
import com.nhs.skillmanagementservice.repository.entity.SkillsetEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Entity - Model Converter for Skillset entity objects.
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface SkillsetMapper {

    /**
     * Convert from entity to response object.
     *
     * @param skillsetEntity SkillsetEntity
     * @return Skill
     */
    Skill entityToEvent(SkillsetEntity skillsetEntity);

    /**
     * Convert from List of skillsetEntities to List of skills.
     *
     * @param skillsetEntities List<SkillsetEntity>
     * @return List<Skill>
     */
    List<Skill> entityListToEventList(List<SkillsetEntity> skillsetEntities);

    /**
     * Convert the model class skill to SkillsetEnitty
     *
     * @param skill SkillRequest
     * @return SkillsetEntity
     */
    SkillsetEntity eventToEntity(SkillRequest skill);

}
