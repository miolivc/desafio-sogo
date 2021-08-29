package com.miolivc.contract.management.api.resource.v1;

import com.miolivc.contract.management.api.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardResource {

    @Autowired
    private DashboardService service;

    @GetMapping("/dashboard/contract")
    ResponseEntity getContractDashboard() {
        return ResponseEntity.ok(service.getContractDashboard());
    }

}
