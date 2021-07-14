package com.nhs.skillmanagementservice.service;

import com.nhs.skillmanagementservice.exception.ResourceNotFoundException;
import com.nhs.skillmanagementservice.exception.ServiceExceptionHandler;
import com.nhs.skillmanagementservice.mapper.SkillsetMapper;
import com.nhs.skillmanagementservice.model.response.RowsDeleted;
import com.nhs.skillmanagementservice.model.request.SkillRequest;
import com.nhs.skillmanagementservice.model.request.UpdateSkillRequest;
import com.nhs.skillmanagementservice.repository.SkillSetRepository;
import com.nhs.skillmanagementservice.repository.entity.SkillsetEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

/**
 * Service class to deal with the CRUD operations with respect to skills.
 */
@Slf4j
@Service
public class SkillsService {
    @Autowired
    private SkillSetRepository skillSetRepository;
    @Autowired
    private SkillsetMapper skillsetMapper;
    @Autowired
    private ServiceExceptionHandler serviceExceptionHandler;

    /**
     * Method to retrieve all the skills in the database, assuming there would be maximum 100 skills only.
     *
     * @return List of all skills
     */
    public ResponseEntity<Object> gellAllSkills() {
        return ResponseEntity.ok().body(skillsetMapper.
                entityListToEventList(skillSetRepository.findAll()));
    }

    /**
     * Method to retrieve the skill info about a particular skillId.
     *
     * @param skillId
     * @return Retrieve a particular skillId information
     */
    public ResponseEntity<Object> getSkillId(String skillId) {
        try {
            SkillsetEntity skillsetEntity = skillSetRepository.findBySkillId(skillId);
            if (ObjectUtils.isEmpty(skillsetEntity)) {
                throw new ResourceNotFoundException("KO Invalid Request - Skill  with " +
                        "given skillId does not exist");
            } else {
                return ResponseEntity.ok().body(skillsetMapper.entityToEvent(skillsetEntity));
            }
        } catch (Exception ex) {
            log.error("KO -  exception while getting the skill details for the given skill Name - {}",
                    skillId);
            return serviceExceptionHandler.handleException(ex);
        }
    }

    /**
     * Method to update the skillname for a particular skillId
     *
     * @param updateSkillRequest UpdateSkillRequest
     * @return Returns the updated skill
     */
    public ResponseEntity<Object> updateSkillName(UpdateSkillRequest updateSkillRequest) {
        SkillsetEntity skillsetEntity = skillSetRepository.findBySkillId(updateSkillRequest.getSkillId());
        if (!ObjectUtils.isEmpty(skillsetEntity)) {
            Optional.ofNullable(updateSkillRequest.getNewSkillName()).ifPresent(skillsetEntity::setSkillName);
        } else {
            throw new ResourceNotFoundException("KO Invalid Request - Skill " +
                    " with given skillId does not exist");
        }
        return ResponseEntity.ok().body(
                skillsetMapper.entityToEvent(skillSetRepository.save(skillsetEntity)));
    }

    /**
     * Method to create a skillset in the database.
     *
     * @param skillRequest SkillRequest
     * @return returns the created skillset in the database
     */
    public ResponseEntity<Object> createSkill(SkillRequest skillRequest) {
        SkillsetEntity skillsetEntity = skillsetMapper.eventToEntity(skillRequest);
        return ResponseEntity.ok().body(
                skillsetMapper.entityToEvent(skillSetRepository.save(skillsetEntity)));
    }

    /**
     * Method to delete a skilset in the database.
     *
     * @param skillName String
     * @return total count of the row deleted
     */
    public ResponseEntity<Object> deleteSkill(String skillName) {
        Integer rowDeleted = skillSetRepository.deleteBySkillName(skillName);
        RowsDeleted rowsDeleted = new RowsDeleted();
        rowsDeleted.setRowsDeleted(rowDeleted);
        return ResponseEntity.ok().body(rowsDeleted);
    }
}
