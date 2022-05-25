package com.example.learnspringwebfluxmongodb.repository;

import com.example.learnspringwebfluxmongodb.domain.model.Contact;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ContactRepository extends ReactiveMongoRepository<Contact, String> {
}
