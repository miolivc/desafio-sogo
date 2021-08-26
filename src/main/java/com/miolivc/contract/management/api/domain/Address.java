package com.miolivc.contract.management.api.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Embeddable
public class Address {

    @Pattern(
            regexp="\\d{5}-\\d{3}",
            message = "O CEP precisa conter numeros, como em 58900-000"
    )
    @NotBlank(message = "O CEP não pode estar vazio")
    @Column(length = 9, nullable = false)
    private String cep;

    @NotBlank(message = "O logradouro não pode estar vazio")
    @Column(nullable = false)
    private String street;

    @NotBlank(message = "O numero não pode estar vazio")
    @Column(length = 10, nullable = false)
    private String number;

    @NotBlank(message = "O complemento não pode estar vazio")
    @Column(length = 150)
    private String additional;

    @NotBlank(message = "O bairro pode estar vazio")
    @Column(length = 150, nullable = false)
    private String district;

    @NotBlank(message = "A cidade não pode estar vazia")
    @Column(length = 150, nullable = false)
    private String city;

    @NotBlank(message = "A UF não pode estar vazia")
    @Column(length = 2, nullable = false)
    private String state;

}