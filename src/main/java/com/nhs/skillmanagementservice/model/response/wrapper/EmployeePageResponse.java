package com.nhs.skillmanagementservice.model.response.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nhs.skillmanagementservice.model.response.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Pageable response of Employee list.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeePageResponse extends PageableResponse {
    private List<Employee> employees;
    private int totalEmployeeCount;
}
