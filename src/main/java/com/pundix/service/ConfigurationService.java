package com.pundix.service;

import com.pundix.entity.configuration.Configuration;
import com.pundix.repository.ConfigurationRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConfigurationService {

    private final ConfigurationRepository configurationRepository;
    private final Map<String, String> configurationCache = new HashMap<>();

    public ConfigurationService(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    @PostConstruct
    public void init() {
        List<Configuration> configurations = configurationRepository.findAll();
        configurations.forEach(configuration -> configurationCache.put(configuration.getKey(), configuration.getValue()));
    }

    public String getValue(String key) {
        return configurationCache.get(key);
    }

    public Integer getValueAsInteger(String key) {
        return Integer.valueOf(configurationCache.get(key));
    }

    public boolean getValueAsBoolean(String key) {
        return Boolean.parseBoolean(configurationCache.get(key));
    }
}