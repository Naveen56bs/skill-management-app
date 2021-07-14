package com.nhs.skillmanagementservice.controller;

import com.nhs.skillmanagementservice.model.request.AssignEmployeeSkillSet;
import com.nhs.skillmanagementservice.service.SkillManagementService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@RestController
@Slf4j
@RequestMapping("/")
public class SkillManagementController {
    @Autowired
    private SkillManagementService skillManagementService;

    @PostMapping("/assignSkillset")
    @ApiOperation(value = "Assign an employee in the organization with the skillset and his level in the same.")
    public ResponseEntity<Object>
    assignEmployeeToTheSkillset(@NotNull @NotEmpty @RequestBody @Valid
                                        AssignEmployeeSkillSet assignEmployeeSkillSet) {
        return skillManagementService.upsertEmployeeSkillset(assignEmployeeSkillSet);
    }
    //// TODO: 14-07-2021 : Create a method which accepts list of employees and their skillsets and save all of them together.

}
