package com.nhs.skillmanagementservice.model.response.wrapper;

import com.nhs.skillmanagementservice.model.SkillAndLevel;
import lombok.Data;

import java.util.List;

/**
 * SkillManagement Response to get employees with their skillsets
 */
@Data
public class SkillManagementResponse {
    private String employeeFirstName;
    private String employeeLastName;
    private List<SkillAndLevel> skillAndLevels;
}
