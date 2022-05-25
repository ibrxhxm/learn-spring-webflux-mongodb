package com.example.learnspringwebfluxmongodb.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Map<String, Object> map = super.getErrorAttributes(request, options);

        if (getError(request) instanceof ApiException) {
            ApiException ex = (ApiException) getError(request);
            map.put("exception", ex.getClass().getSimpleName());
            map.put("message", ex.getStatus().name());
            map.put("status", String.valueOf(ex.getStatus().value()));
            map.put("info", ex.getReason());

            return map;
        }

        map.put("exception", "SystemException");
        map.put("message", "INTERNAL_SERVER_ERROR");
        map.put("status", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        map.put("info", " System Error");

        return map;
    }
}
