package com.example.learnspringwebfluxmongodb.domain.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContactResponse {
    private String id;
    private String name;
    private String email;
    private String phone;
}
