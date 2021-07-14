package com.nhs.skillmanagementservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionErrorResponse {
    private LocalDateTime errorTimestamp;
    private String errorMessage;
}