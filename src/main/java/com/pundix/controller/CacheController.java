package com.pundix.controller;

import com.pundix.service.ConfigurationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "cache")
public class CacheController {

    private final ConfigurationService configurationService;

    public CacheController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @GetMapping("/clear")
    public String clearCache() {
        configurationService.init();
        return "Successfully !";
    }
}
