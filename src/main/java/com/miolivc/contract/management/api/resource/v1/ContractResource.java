package com.miolivc.contract.management.api.resource.v1;

import com.miolivc.contract.management.api.database.ContractRepository;
import com.miolivc.contract.management.api.domain.Contract;
import com.miolivc.contract.management.api.exception.ResourceNotFoundException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class ContractResource {

    @Autowired
    private ContractRepository repository;

    @ApiOperation(value = "Get all contracts and also filter by daysToExpire")
    @GetMapping("/contracts")
    ResponseEntity<List<Contract>> getAll(@RequestParam(value = "daysToExpire", required = false) Integer daysToExpire) {

        List<Contract> contracts;
        if (daysToExpire != null) {
            contracts = repository.getContractsDueIn(daysToExpire);
        } else {
            contracts = repository.findAll();
        }

        if (contracts == null || contracts.isEmpty()) {
            throw new ResourceNotFoundException(Contract.class);
        }

        return ResponseEntity.ok(contracts);
    }

    @ApiOperation(value = "Get a contract by number")
    @GetMapping("/contract/{number}")
    ResponseEntity findById(@PathVariable("number") Long number) {

        if (number == null || number < 1) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("É necessario informar um ID valido");
        }

        Optional<Contract> contract = repository.findById(number);

        if (contract.isEmpty()) {
            throw new ResourceNotFoundException(Contract.class);
        }

        return ResponseEntity.ok(contract.get());
    }

    @ApiOperation(value = "Save a Contract")
    @PostMapping("/contract")
    ResponseEntity<Contract> save(@Valid @RequestBody Contract contract) {

        repository.save(contract);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{number}")
                .buildAndExpand(contract.getNumber()).toUri();

        return ResponseEntity
                .created(location)
                .build();
    }

    @ApiOperation(value = "Remove a contract by id")
    @DeleteMapping(value = "/contract/{id}")
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
