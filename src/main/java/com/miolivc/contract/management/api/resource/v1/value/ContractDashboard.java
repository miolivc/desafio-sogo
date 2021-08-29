package com.miolivc.contract.management.api.resource.v1.value;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class ContractDashboard implements Serializable {

    private Integer numberOfContracts;
    private Integer numberOfActualContracts;
    private Integer serviceAvgTimeInDays;

}
