package com.nhs.skillmanagementservice.model.response.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageableResponse {
    private int totalPages;
    private int count;
    private int pageNumber;
}
