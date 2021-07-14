package com.nhs.skillmanagementservice.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SearchCriteria {
    @JsonIgnore
    private String sortBy = "employeeId";

    @NotNull(message = "Page Size cannot be null")
    @ApiModelProperty(example = "30")
    private Integer pageSize;

    @NotNull(message = "Page Number cannot be null")
    @ApiModelProperty(example = "0")
    private Integer pageNumber;

}
