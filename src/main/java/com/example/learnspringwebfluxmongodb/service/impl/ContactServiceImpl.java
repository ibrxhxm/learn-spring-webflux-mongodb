package com.example.learnspringwebfluxmongodb.service.impl;

import com.example.learnspringwebfluxmongodb.domain.dto.request.ContactRequest;
import com.example.learnspringwebfluxmongodb.domain.dto.response.ContactResponse;
import com.example.learnspringwebfluxmongodb.domain.model.Contact;
import com.example.learnspringwebfluxmongodb.exception.ApiException;
import com.example.learnspringwebfluxmongodb.repository.ContactRepository;
import com.example.learnspringwebfluxmongodb.service.ContactService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;
    private final ModelMapper modelMapper;

    public ContactServiceImpl(ContactRepository contactRepository, ModelMapper modelMapper) {
        this.contactRepository = contactRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Mono<ContactResponse> add(ContactRequest request) {
        Contact contact = modelMapper.map(request, Contact.class);

        return contactRepository.save(contact).map(x -> modelMapper.map(x, ContactResponse.class));
    }

    @Override
    public Mono<ContactResponse> update(ContactRequest request) {
        return find(request.getId()).flatMap(x -> {
            modelMapper.map(modelMapper.map(request, Contact.class), x);

            return contactRepository.save(x).map(y -> modelMapper.map(y, ContactResponse.class));
        });
    }

    @Override
    public Mono<ContactResponse> get(String id) {
        return find(id).map(x -> modelMapper.map(x, ContactResponse.class));
    }

    @Override
    public Flux<ContactResponse> getAll() {
        return contactRepository.findAll().map(x -> modelMapper.map(x, ContactResponse.class));
    }

    @Override
    public Mono<ContactResponse> delete(String id) {
        return find(id).flatMap(x -> contactRepository.delete(x).then(Mono.just(modelMapper.map(x, ContactResponse.class))));
    }


    private Mono<Contact> find(String id) {
//        return contactRepository.findById(id).map(x -> {
//            if (Objects.isNull(x)){
//                throw new ApiException(HttpStatus.NOT_FOUND, String.format("contact with id: %s not found", id));
//            }
//            return x;
//        });

        return Mono.just(id)
                .flatMap(contactRepository::findById)
                .switchIfEmpty(Mono.error(new ApiException(HttpStatus.NOT_FOUND, String.format("contact with id: %s not found", id))));
    }
}
