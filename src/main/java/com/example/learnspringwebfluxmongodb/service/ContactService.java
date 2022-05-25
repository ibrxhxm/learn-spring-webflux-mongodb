package com.example.learnspringwebfluxmongodb.service;

import com.example.learnspringwebfluxmongodb.domain.dto.request.ContactRequest;
import com.example.learnspringwebfluxmongodb.domain.dto.response.ContactResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ContactService {
    Mono<ContactResponse> add(ContactRequest request);

    Mono<ContactResponse> update(ContactRequest request);

    Mono<ContactResponse> get(String id);

    Flux<ContactResponse> getAll();

    Mono<ContactResponse> delete(String id);
}
