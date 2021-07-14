package com.nhs.skillmanagementservice.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class LevelAndSkillWithSearchCriteria {
    @NotNull(message = "SkillId cannot be null")
    @NotEmpty(message = "SkillId cannot be empty")

    private String skillId;

    @NotEmpty(message = "Level cannot be empty")
    @NotNull(message = "Level cannot be null")
    @Pattern(regexp = "Expert|Practitioner|Working|Awareness", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String level;

    @JsonIgnore
    private String sortBy = "employeeId";

    @NotNull(message = "Page Size cannot be null")
    @ApiModelProperty(example = "30")
    private Integer pageSize;

    @NotNull(message = "Page Number cannot be null")
    @ApiModelProperty(example = "0")
    private Integer pageNumber;
}
