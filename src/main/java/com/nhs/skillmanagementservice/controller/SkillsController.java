package com.nhs.skillmanagementservice.controller;

import com.nhs.skillmanagementservice.model.request.SkillRequest;
import com.nhs.skillmanagementservice.model.request.UpdateSkillRequest;
import com.nhs.skillmanagementservice.service.SkillsService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequestMapping("/")
public class SkillsController {
    @Autowired
    private SkillsService skillsService;

    @PostMapping("/createSkill")
    @ApiOperation(value = " CREATE skill by passing the skill name in the database.")
    public ResponseEntity<Object> createSkill(@RequestBody @Valid SkillRequest skillRequest) {
        return skillsService.createSkill(skillRequest);
    }

    @PutMapping("/updateSkillName")
    @ApiOperation(value = " UPDATE skillName based on the skillId in the database.")
    public ResponseEntity<Object> updateSkill(@RequestBody @Valid UpdateSkillRequest updateSkillRequest) {
        return skillsService.updateSkillName(updateSkillRequest);
    }

    @GetMapping("/retrieveAllSkills")
    @ApiOperation(value = " RETRIEVE all the skill details from the database.")
    public ResponseEntity<Object> gellAllSkills() {
        return skillsService.gellAllSkills();
    }

    @GetMapping("/getSkillById")
    @ApiOperation(value = " RETRIEVE the skill based on id in the database.")
    public ResponseEntity<Object> getSkillBySkillId(@RequestParam(value = "skillId") @NotNull
                                                    @NotEmpty String skillId) {
        return skillsService.getSkillId(skillId);
    }

    @DeleteMapping("/deleteSkillName")
    @ApiOperation(value = " DELETE the skill based on skill name in the database.")
    public ResponseEntity<Object> deleteSkill(@RequestParam(value = "skillName") @NotNull
                                              @NotEmpty String skillName) {
        return skillsService.deleteSkill(skillName);
    }

}
