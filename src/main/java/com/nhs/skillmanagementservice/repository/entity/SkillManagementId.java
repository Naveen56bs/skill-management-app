package com.nhs.skillmanagementservice.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class SkillManagementId implements Serializable {

    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "skill_id")
    private String skillId;
}
