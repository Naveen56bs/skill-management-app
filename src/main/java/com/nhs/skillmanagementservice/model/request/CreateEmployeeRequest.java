package com.nhs.skillmanagementservice.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CreateEmployeeRequest {
    @NotNull(message = "employee first name cannot be null")
    @NotEmpty(message = "employee first name cann0t be empty")
    @Size(min = 3, max = 30)
    private String employeeFirstName;

    @NotNull(message = "employee last name cannot be null")
    private String employeeLastName;
}
