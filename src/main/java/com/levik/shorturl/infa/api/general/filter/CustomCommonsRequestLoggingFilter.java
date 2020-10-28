package com.levik.shorturl.infa.api.general.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class CustomCommonsRequestLoggingFilter extends CommonsRequestLoggingFilter {

    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        return logger.isDebugEnabled();
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        //Nothing to do
    }
}