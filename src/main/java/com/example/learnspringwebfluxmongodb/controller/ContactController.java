package com.example.learnspringwebfluxmongodb.controller;

import com.example.learnspringwebfluxmongodb.domain.dto.request.ContactRequest;
import com.example.learnspringwebfluxmongodb.domain.dto.response.ContactResponse;
import com.example.learnspringwebfluxmongodb.service.ContactService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<ContactResponse>> add(@RequestBody ContactRequest request) {
        return ResponseEntity.ok(contactService.add(request));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<ContactResponse>> update(@RequestBody ContactRequest request) {
        return ResponseEntity.ok(contactService.update(request));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<ContactResponse>> get(@PathVariable("id") String id) {
        return ResponseEntity.ok(contactService.get(id));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Flux<ContactResponse>> getAll() {
        return ResponseEntity.ok(contactService.getAll());
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<ContactResponse>> delete(@PathVariable("id") String id) {
        return ResponseEntity.ok(contactService.delete(id));
    }
}
