package com.pundix.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class AppProperties {

    @Autowired
    private Environment env;

    public String getRoleAuthToken() {
        return env.getProperty("role.auth.token");
    }
}

