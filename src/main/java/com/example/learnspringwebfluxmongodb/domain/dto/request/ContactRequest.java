package com.example.learnspringwebfluxmongodb.domain.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContactRequest {
    private String id;
    private String name;
    private String email;
    private String phone;
}
