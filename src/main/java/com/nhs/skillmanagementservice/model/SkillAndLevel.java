package com.nhs.skillmanagementservice.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class SkillAndLevel {
    @NotNull(message = "SkillId cannot be null")
    @NotEmpty(message = "SkillId cannot be empty")
    private String skillId;

    @NotEmpty(message = "Level cannot be empty")
    @NotNull(message = "Level cannot be null")
    @Pattern(regexp = "Expert|Practitioner|Working|Awareness", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String level;
}
