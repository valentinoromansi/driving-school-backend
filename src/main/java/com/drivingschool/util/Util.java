package com.drivingschool.util;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class Util {

    public static <T> ResponseEntity<List<T>> getPaginatedResponse(Page<T> page) {
        HttpHeaders headers = new HttpHeaders() {{
            add("X-Total-Count", String.valueOf(page.getTotalElements()));
        }};
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(page.getContent());
    }
}
