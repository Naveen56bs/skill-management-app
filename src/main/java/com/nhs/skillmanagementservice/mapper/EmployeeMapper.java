package com.nhs.skillmanagementservice.mapper;

import com.nhs.skillmanagementservice.model.request.CreateEmployeeRequest;
import com.nhs.skillmanagementservice.model.response.Employee;
import com.nhs.skillmanagementservice.model.response.wrapper.EmployeePageResponse;
import com.nhs.skillmanagementservice.repository.entity.EmployeeEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Entity - Model Converter for Employee objects.
 */
@Mapper(componentModel = "spring", injectionStrategy=InjectionStrategy.FIELD)
public interface EmployeeMapper {
    /**
     * Convert from entity to response object.
     *
     * @param employeeEntity EmployeeEntity
     * @return Employee
     */
    Employee entityToEvent(EmployeeEntity employeeEntity);


    /**
     * Convert list of entities to response object list.
     *
     * @param employeeEntities List<EmployeeEntity>
     * @return List<Employee>
     */
    List<Employee> entityListToEventList(List<EmployeeEntity> employeeEntities);


    /**
     * Get the supplier wrapper from supplier entitity list.
     *
     * @param employeeEntities Page<EmployeeEntity>
     * @return EmployeePageResponse
     */
    default EmployeePageResponse getEmployeeWrapper(Page<EmployeeEntity> employeeEntities) {
        EmployeePageResponse response = new EmployeePageResponse();
        response.setEmployees(entityListToEventList(employeeEntities.getContent()));
        response.setTotalEmployeeCount((int) employeeEntities.getTotalElements());
        response.setPageNumber(employeeEntities.getNumber());
        response.setTotalPages(employeeEntities.getTotalPages());
        response.setCount(employeeEntities.getContent().size());
        return response;
    }

    /**
     * Converts the employee response object to the supplier entity object
     *
     * @param employeeRequest Employee
     * @return EmployeeEntity
     */
    EmployeeEntity eventToEntity(CreateEmployeeRequest employeeRequest);

}
