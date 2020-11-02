package com.cognizant.authapi.base.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * Created by 784420 on 7/17/2019 11:49 AM
 */
@Data
@NotBlank
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
    private int status;
    private String statusMessage;
    private String message;
}
