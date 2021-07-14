package com.nhs.skillmanagementservice.repository.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "skillset_management_detail")
@Getter
@Setter
@NoArgsConstructor
public class SkillsetManagementEntity implements Serializable {

    @EmbeddedId
    private SkillManagementId skillManagementId;

    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employeeEntity;

    @ManyToOne
    @MapsId("skillId")
    @JoinColumn(name = "skill_id")
    private SkillsetEntity skillsetEntity;

    @Column(nullable = false)
    private String level;

    @UpdateTimestamp
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    public SkillsetManagementEntity(EmployeeEntity employee, SkillsetEntity skillset, String level) {
        this.skillManagementId = new SkillManagementId(employee.getEmployeeId(), skillset.getSkillId());
        this.employeeEntity = employee;
        this.skillsetEntity = skillset;
        this.level = level;
    }
}
