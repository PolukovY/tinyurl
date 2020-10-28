package com.levik.shorturl.infa.api.general;

import com.levik.shorturl.infa.api.general.filter.CustomCommonsRequestLoggingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfiguration {

    @Bean
    public CustomCommonsRequestLoggingFilter logFilter() {
        CustomCommonsRequestLoggingFilter filter = new CustomCommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludeHeaders(true);
        filter.setIncludeClientInfo(true);

        return filter;
    }
}
