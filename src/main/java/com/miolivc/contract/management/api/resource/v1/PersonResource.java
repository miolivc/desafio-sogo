package com.miolivc.contract.management.api.resource.v1;

import com.miolivc.contract.management.api.database.PersonRepository;
import com.miolivc.contract.management.api.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@Slf4j
@RestController("/v1/pessoa")
public class PersonResource {

    @Autowired
    private PersonRepository repository;

    @GetMapping
    ResponseEntity getAll() {
        var persons = repository.findAll();

        if (persons == null || persons.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Pessoa não encontrada");
        }

        return ResponseEntity.ok(persons);
    }

    @GetMapping("/{id}")
    ResponseEntity findById(@RequestParam("id") Long id) {

        if (id == null || id < 1) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("É necessario informar um ID valido");
        }

        Optional<Person> person = repository.findById(id);

        return person.isPresent()
                ? ResponseEntity.ok(person.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada");
    }

    @PostMapping
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

    @DeleteMapping(value = "/{id}")
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
