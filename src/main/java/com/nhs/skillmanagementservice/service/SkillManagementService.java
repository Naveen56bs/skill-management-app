package com.nhs.skillmanagementservice.service;

import com.nhs.skillmanagementservice.exception.ResourceNotFoundException;
import com.nhs.skillmanagementservice.exception.ServiceExceptionHandler;
import com.nhs.skillmanagementservice.mapper.SkillManagementMapper;
import com.nhs.skillmanagementservice.model.request.AssignEmployeeSkillSet;
import com.nhs.skillmanagementservice.repository.EmployeeRepository;
import com.nhs.skillmanagementservice.repository.SkillSetRepository;
import com.nhs.skillmanagementservice.repository.SkillsetManagementRepository;
import com.nhs.skillmanagementservice.repository.entity.EmployeeEntity;
import com.nhs.skillmanagementservice.repository.entity.SkillsetEntity;
import com.nhs.skillmanagementservice.repository.entity.SkillsetManagementEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class to deal with management of Skillsets and their employees
 */
@Slf4j
@Service
public class SkillManagementService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private SkillSetRepository skillSetRepository;
    @Autowired
    private SkillsetManagementRepository skillsetManagementRepository;
    @Autowired
    private SkillManagementMapper skillManagementMapper;
    @Autowired
    ServiceExceptionHandler serviceExceptionHandler;

    /**
     * Method used to upsert the employee with his skillsets along with his levels of expertise.
     *
     * @param assignEmployeeSkillSet AssignEmployeeSkillSet
     * @return Returns the upserted Employee detail with the skillset
     */
    public ResponseEntity<Object> upsertEmployeeSkillset(AssignEmployeeSkillSet assignEmployeeSkillSet) {
        String employeeId = assignEmployeeSkillSet.getEmployeeSkill().getEmployeeId();
        EmployeeEntity employeeEntity = employeeRepository.findByEmployeeId(employeeId);
        if (!ObjectUtils.isEmpty(employeeEntity)) {
            List<String> skillIds = assignEmployeeSkillSet.getEmployeeSkill().getSkillAndLevels().stream().
                    map(skillAndLevel -> skillAndLevel.getSkillId()).collect(Collectors.toList());
            List<SkillsetEntity> skillsetEntities = skillSetRepository.findBySkillIdIn(skillIds);
            if (skillsetEntities.size() > 0 &&
                    skillIds.size() == skillSetRepository.findBySkillIdIn(skillIds).size()) {
                List<SkillsetManagementEntity> skillsetManagementEntities =
                        assignEmployeeSkillSet.getEmployeeSkill().getSkillAndLevels().stream().map(
                                skillAndLevel -> {
                                    return new SkillsetManagementEntity(employeeRepository.findByEmployeeId(employeeId),
                                            skillSetRepository.findBySkillId(
                                                    skillAndLevel.getSkillId()), skillAndLevel.getLevel().toUpperCase());
                                }
                        ).collect(Collectors.toList());
                return ResponseEntity.ok().body(skillManagementMapper.
                        skillManagementEntityToSkillManagementResp(
                                skillsetManagementRepository.saveAll(skillsetManagementEntities)));
            } else {
                throw new RuntimeException("The skillIds must be a registered skillId or there" +
                        " might be duplicate skillIds.");
            }
        } else {
            throw new ResourceNotFoundException("All the employeeIds provided must be registered employees.");
        }
    }
}
