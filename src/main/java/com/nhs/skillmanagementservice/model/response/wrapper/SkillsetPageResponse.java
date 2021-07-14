package com.nhs.skillmanagementservice.model.response.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nhs.skillmanagementservice.model.response.Skill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Skillset Pageable Response.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SkillsetPageResponse extends PageableResponse {
    private List<Skill> skills;
    private int totalSkillsCount;
}
