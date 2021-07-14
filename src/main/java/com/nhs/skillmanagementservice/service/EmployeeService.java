package com.nhs.skillmanagementservice.service;

import com.nhs.skillmanagementservice.exception.InvalidRequestException;
import com.nhs.skillmanagementservice.exception.ResourceNotFoundException;
import com.nhs.skillmanagementservice.exception.ServiceExceptionHandler;
import com.nhs.skillmanagementservice.mapper.EmployeeMapper;
import com.nhs.skillmanagementservice.model.request.CreateEmployeeRequest;
import com.nhs.skillmanagementservice.model.request.SearchCriteria;
import com.nhs.skillmanagementservice.model.request.UpdateEmployeeRequest;
import com.nhs.skillmanagementservice.model.response.RowsDeleted;
import com.nhs.skillmanagementservice.model.response.wrapper.EmployeePageResponse;
import com.nhs.skillmanagementservice.model.request.LevelAndSkillWithSearchCriteria;
import com.nhs.skillmanagementservice.repository.EmployeeRepository;
import com.nhs.skillmanagementservice.repository.entity.EmployeeEntity;
import com.nhs.skillmanagementservice.util.SkillManagementConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

/**
 * EmployeeService class deal with all the CRUD operations related with employees
 * and retrieving employee details based on their skillset and level of expertise.
 */
@Slf4j
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    ServiceExceptionHandler serviceExceptionHandler;

    /**
     * @param searchCriteria SearchCriteria
     * @return List of employee with their details in a pageble format.
     */
    public ResponseEntity<Object> getAllEmployees(SearchCriteria searchCriteria) {
        try {
            PageRequest pageRequest = PageRequest.of(searchCriteria.getPageNumber(),
                    searchCriteria.getPageSize(),
                    Sort.by(searchCriteria.getSortBy()));
            EmployeePageResponse employeeWrapper = employeeMapper.getEmployeeWrapper
                    (employeeRepository.findAll(pageRequest));
            if (searchCriteria.getPageNumber() >= employeeWrapper.getTotalPages()) {
                throw new InvalidRequestException(SkillManagementConstants.INVALID_PAGE_NUMBER);
            }
            return ResponseEntity.ok().body(employeeWrapper);
        } catch (Exception ex) {
            log.error("KO - exception while getting all the details of the employees.", ex);
            return serviceExceptionHandler.handleException(ex);
        }
    }

    /**
     * @param employeeId String
     * @return Give a particular employee detail
     */
    public ResponseEntity<Object> getEmployee(String employeeId) {
        try {
            EmployeeEntity employeeEntity = employeeRepository.findByEmployeeId(employeeId);
            if (ObjectUtils.isEmpty(employeeEntity)) {
                throw new ResourceNotFoundException("KO Invalid Request - Employee with given employeeId " +
                        "does not exist");
            } else {
                return ResponseEntity.ok()
                        .body(employeeMapper.entityToEvent(employeeEntity));
            }
        } catch (Exception ex) {
            log.error("KO -  exception while getting the employee details for the given employeeId - {}", employeeId);
            return serviceExceptionHandler.handleException(ex);
        }
    }

    /**
     * @param employee UpdateEmployeeRequest
     * @return would return the updated employee.
     */
    public ResponseEntity<Object> updateEmployee(UpdateEmployeeRequest employee) {
        EmployeeEntity employeeEntityToBeSaved = null;
        EmployeeEntity existingEmployeeEntity = employeeRepository.findByEmployeeId(employee.getEmployeeId());
        if (!ObjectUtils.isEmpty(existingEmployeeEntity)) {
            employeeEntityToBeSaved = upsertEmployeeEntity(employee, existingEmployeeEntity);
        } else {
            throw new ResourceNotFoundException("KO Invalid Request - Employee with given employeeId does not exist");
        }
        return ResponseEntity.ok().
                body(employeeMapper.entityToEvent
                        (employeeRepository.save(employeeEntityToBeSaved)));
    }

    /**
     * @param employee CreateEmployeeRequest
     * @return Returns the employee that would have been created in the database
     */
    public ResponseEntity<Object> createEmployee(CreateEmployeeRequest employee) {
        return ResponseEntity.ok().body(employeeMapper.entityToEvent(
                employeeRepository.save(employeeMapper.eventToEntity(employee))));
    }

    /**
     * @param employeeId String
     * @return Would return the rowcount indicating if the specified employee has been deleted or not
     */
    public ResponseEntity<Object> deleteEmployee(String employeeId) {
        Integer rowDeleted = employeeRepository.deleteByEmployeeId(employeeId);
        RowsDeleted rowsDeleted = new RowsDeleted();
        rowsDeleted.setRowsDeleted(rowDeleted);
        return ResponseEntity.ok().body(rowsDeleted);
    }

    /**
     * @param levelAndSkillWithSearchCriteria LevelAndSkillWithSearchCriteria
     * @return retrieves all the employees based on their skillset and expertise in their skills in pageable format.
     */
    public ResponseEntity<Object> retrieveEmployeesBasedOnSkills(LevelAndSkillWithSearchCriteria
                                                                         levelAndSkillWithSearchCriteria) {
        try {
            Pageable pageRequest = PageRequest.of(levelAndSkillWithSearchCriteria.getPageNumber(),
                    levelAndSkillWithSearchCriteria.getPageSize(),
                    Sort.by(levelAndSkillWithSearchCriteria.getSortBy()));
            EmployeePageResponse employeeWrapper = employeeMapper.getEmployeeWrapper
                    (employeeRepository.
                            findBySkillsetManagementEntitiesSkillManagementIdSkillIdAndSkillsetManagementEntitiesLevel(
                                    levelAndSkillWithSearchCriteria.getSkillId(),
                                    levelAndSkillWithSearchCriteria.getLevel().toUpperCase(), pageRequest));
            if (levelAndSkillWithSearchCriteria.getPageNumber() >= employeeWrapper.getTotalPages()) {
                throw new InvalidRequestException(SkillManagementConstants.INVALID_PAGE_NUMBER);
            }
            return ResponseEntity.ok().body(employeeWrapper);
        } catch (Exception ex) {
            log.error("KO - exception while getting all the details of the employees.", ex);
            return serviceExceptionHandler.handleException(ex);
        }
    }


    private EmployeeEntity upsertEmployeeEntity(UpdateEmployeeRequest employee, EmployeeEntity existingEmployeeEntity) {
        if(employee.getEmployeeFirstName() != null && !employee.getEmployeeFirstName().isEmpty()){
            existingEmployeeEntity.setEmployeeFirstName(employee.getEmployeeFirstName());
        }
        Optional.ofNullable(employee.getEmployeeLastName()).ifPresent(existingEmployeeEntity::setEmployeeLastName);
        return existingEmployeeEntity;
    }
}
