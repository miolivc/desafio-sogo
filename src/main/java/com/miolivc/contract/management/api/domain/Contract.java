package com.miolivc.contract.management.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
@Slf4j
@Entity
public class Contract {

    @Id
    private Long number;

    @NotNull(message = "A data de registro não pode ser nula")
    @PastOrPresent(message = "A data de registro não pode ser superior ao dia atual")
    @JsonProperty(required = true)
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate registerDate;

    @NotNull(message = "A data de registro não pode ser nula")
    @FutureOrPresent(message = "A data de admissão não pode ser inferior ao dia atual")
    @JsonProperty(required = true)
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate expirationDate;

    @ManyToOne(optional = false)
    private Person person;

    @PostPersist
    public void logContractAdded() {
        log.info("Contract Added: " + this);
    }

    @PostUpdate
    public void logContractUpdated() {
        log.info("Contract Updated: " + this);
    }

}
