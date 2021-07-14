package com.nhs.skillmanagementservice.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
@Data
public class SkillRequest {
    @NotNull(message = "skillName cannot be empty")
    @NotEmpty(message = "skill name cannot be empty")
    String skillName;
}

