package com.nhs.skillmanagementservice.controller;


import com.nhs.skillmanagementservice.model.request.CreateEmployeeRequest;
import com.nhs.skillmanagementservice.model.request.SearchCriteria;
import com.nhs.skillmanagementservice.model.request.UpdateEmployeeRequest;
import com.nhs.skillmanagementservice.model.request.LevelAndSkillWithSearchCriteria;
import com.nhs.skillmanagementservice.repository.EmployeeRepository;
import com.nhs.skillmanagementservice.service.EmployeeService;
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
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/createEmployee")
    @ApiOperation(value = "CREATE employee in the database.")
    public ResponseEntity<Object> createEmployee(@Valid @RequestBody CreateEmployeeRequest employee) {
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/updateEmployee")
    @ApiOperation(value = "Update Employee details in the database.")
    public ResponseEntity<Object> updateEmployee(@NotNull @RequestBody @Valid UpdateEmployeeRequest employee) {
        return employeeService.updateEmployee(employee);
    }

    @PostMapping("/getAllEmployees")
    @ApiOperation(value = "GET list of all the employees in the database.")
    public ResponseEntity<Object> getAllEmployees(@NotNull @RequestBody @Valid SearchCriteria searchCriteria) {
        return employeeService.getAllEmployees(searchCriteria);
    }

    @GetMapping("/getEmployee")
    @ApiOperation(value = "GET employee based on employeeId")
    public ResponseEntity<Object> findEmployee(@RequestParam(value = "employeeId") @NotNull @NotEmpty
                                                       String employeeId) {
        return employeeService.getEmployee(employeeId);
    }

    @DeleteMapping("/deleteEmployee")
    @ApiOperation(value = "Delete employee from the database.")
    public ResponseEntity<Object> deleteEmployee(@RequestParam(value = "employeeId") @NotEmpty @NotNull String employeeId) {
        return employeeService.deleteEmployee(employeeId);
    }

    @PostMapping("/retrieveEmployeesOnSkills")
    @ApiOperation(value = "Retrieve employees from the database based on their skills and" +
            " level(Expert|Practitioner|Working|Awareness)")
    public ResponseEntity<Object> retrieveEmployeesBasedOnSkills(@RequestBody @Valid
                                                                         LevelAndSkillWithSearchCriteria levelAndSkillWithSearchCriteria) {
        return employeeService.retrieveEmployeesBasedOnSkills(levelAndSkillWithSearchCriteria);
    }


}
