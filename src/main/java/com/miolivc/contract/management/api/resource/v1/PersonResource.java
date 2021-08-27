package com.miolivc.contract.management.api.resource.v1;

import com.miolivc.contract.management.api.database.PersonRepository;
import com.miolivc.contract.management.api.domain.Person;
import com.miolivc.contract.management.api.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.net.URI;
import java.util.Optional;

@RestController
public class PersonResource {

    @Autowired
    private PersonRepository repository;

    @GetMapping("/persons")
    ResponseEntity getAll() {
        var persons = repository.findAll();

        if (persons == null || persons.isEmpty()) {
            throw new ResourceNotFoundException(Person.class);
        }

        return ResponseEntity.ok(persons);
    }

    @GetMapping("/person/{id}")
    ResponseEntity findById(@PathVariable("id") Long id) {

        if (id == null || id < 1) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("É necessario informar um ID valido");
        }

        Optional<Person> person = repository.findById(id);

        if (person.isEmpty()) {
            throw new ResourceNotFoundException(Person.class);
        }

        return ResponseEntity.ok(person.get());
    }

    @PostMapping("/person")
    ResponseEntity save(@Valid @RequestBody Person person) {

        repository.save(person);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(person.getId()).toUri();

        return ResponseEntity
                .created(location)
                .build();
    }

    @DeleteMapping(value = "/person/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {

        if (id == null || id < 1) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("É necessario informar um ID valido");
        }

        repository.deleteById(id);

        return ResponseEntity
                .ok()
                .build();
    }

}
