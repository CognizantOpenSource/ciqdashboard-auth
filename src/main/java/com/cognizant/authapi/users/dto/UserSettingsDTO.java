package com.cognizant.authapi.users.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * Created by 784420 on 8/1/2019 12:11 PM
 */
@Data
@NoArgsConstructor
@Validated
public class UserSettingsDTO {
    @Valid
    private Object jenkins;
    @Valid
    private Object gitlab;
    @Valid
    private Object github;
    @Valid
    private Object bitbucket;
    @Valid
    private Object externalApps;

    private Object dashboard;
}
