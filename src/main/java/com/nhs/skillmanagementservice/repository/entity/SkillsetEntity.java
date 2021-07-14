package com.nhs.skillmanagementservice.repository.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "skillset_detail")
public class SkillsetEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "skill_seq")
    @GenericGenerator(name = "skill_seq",
            strategy = "com.nhs.skillmanagementservice.repository.entity.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "50"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "SKILL_"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
            }
    )


    @Column(updatable = false, nullable = false, name = "skill_id")
    private String skillId;

    @Column(nullable = false)
    private String skillName;

    @CreationTimestamp
    private LocalDateTime createdOn;

    @UpdateTimestamp
    private LocalDateTime updatedOn;

    @OneToMany(mappedBy = "skillsetEntity", cascade = CascadeType.ALL)
    private Set<SkillsetManagementEntity> skillsetManagementEntities;

    public SkillsetEntity(String skillName) {
        this.skillName = skillName;
    }

}
