package com.miolivc.contract.management.api.domain;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Slf4j
@Entity
@SequenceGenerator(name = "person_seq", sequenceName = "person_seq", allocationSize = 1)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoa_seq")
    private Long id;

    @Pattern(
            regexp = "[A-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ]+([ ][A-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ]+)*",
            message = "O nome deve atender ao padrão," +
                    " sendo pelo menos dois nomes sem conter caracteres especiais"
    )
    @NotBlank(message = "O nome não pode estar vazio")
    @JsonProperty(required = true)
    private String name;

    @CPF
    @JsonProperty(required = true)
    @Column(length = 14, nullable = false, unique = true)
    private String cpf;

    @Embedded
    private Address address;

    @PostPersist
    public void logPersonAdded() {
        log.info("Person Added: " + this);
    }

    @PostUpdate
    public void logPersonUpdated() {
        log.info("Person Updated: " + this);
    }

}