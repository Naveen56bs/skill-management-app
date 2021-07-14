package com.nhs.skillmanagementservice.model.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateSkillRequest {

    @NotNull(message = "SkillId cannot be null")
    @NotEmpty(message = "SkillId cannot be empty")
    private String skillId;

    @NotNull(message = "newSkillName cannot be null")
    @NotEmpty(message = "newSkillName cannot be empty")
    private String newSkillName;
}
