package com.nhs.skillmanagementservice.model.request;

import com.nhs.skillmanagementservice.model.SkillAndLevel;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class EmployeeSkill {
    @NotNull(message= "employeeId cannot be null")
    @NotEmpty(message="employeeId cannot be empty")
    private String employeeId;

    @NotNull(message = "There should be at least one skill and level selected")
    @NotEmpty(message="SkillAndLevel cannot be an empty list")
    @Valid
    private List<SkillAndLevel> skillAndLevels;
}
