package com.nhs.skillmanagementservice.model.request;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@JsonPropertyOrder({"employeeId, employeeFirstName, employeeLastName"})
public class UpdateEmployeeRequest {

    private String employeeFirstName;

    private String employeeLastName;

    @NotNull(message = "EmployeeId cannot be null")
    @NotEmpty(message = "EmployeeId cannot be empty")
    private String employeeId;
}
