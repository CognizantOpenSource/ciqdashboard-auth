package com.cognizant.authapi.collector.cotroller;

import com.cognizant.authapi.collector.service.CollectorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * Created by 784420 on 10/31/2019 2:08 PM
 */
@RestController
@RequestMapping(value = "/collector")
@Slf4j
@AllArgsConstructor
public class CollectorController {

    private CollectorService service;

    /**
     * Generating token for the collector application which is use to fetch the third party application information
     * @param email to generate token based on the collector token
     * @param days no.of days to generate token (Expiry time)
     * @return token and expiry time details
     */
    @PostMapping(value = "/token")
    @Validated
    public Map<String, Object> generateCollectorToken(@Valid @RequestParam @NotBlank String email, @RequestParam @Min(1) int days) {
        return service.generateCollectorToken(email, days);
    }

}
