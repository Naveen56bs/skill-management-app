package com.nhs.skillmanagementservice.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Skill {
    private String skillName;
    private String skillId;
    private String createdOn;
}
