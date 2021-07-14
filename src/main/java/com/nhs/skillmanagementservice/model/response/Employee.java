package com.nhs.skillmanagementservice.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.nhs.skillmanagementservice.repository.entity.SkillsetManagementEntity;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee {
    private String employeeId;
    private String employeeFirstName;
    private String employeeLastName;
    private LocalDateTime createdOn;
    @JsonIgnore
    private Set<SkillsetManagementEntity> skillsetManagementEntities;
}
