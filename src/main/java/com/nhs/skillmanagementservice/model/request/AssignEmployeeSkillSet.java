package com.nhs.skillmanagementservice.model.request;

import lombok.Data;

import javax.validation.Valid;

@Data
public class AssignEmployeeSkillSet {
    @Valid
    private EmployeeSkill employeeSkill;
}
