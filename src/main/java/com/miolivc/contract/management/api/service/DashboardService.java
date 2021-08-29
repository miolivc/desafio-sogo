package com.miolivc.contract.management.api.service;

import com.miolivc.contract.management.api.resource.v1.value.ContractDashboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ContractDashboard getContractDashboard() {

        var query = "SELECT COUNT(*) AS numberOfContracts, " +
                "COUNT(expiration_date <= NOW()) AS numberOfActualContracts, " +
                "AVG(expiration_date - register_date) AS serviceAvgTimeInDays " +
                "FROM contract";

        return jdbcTemplate.queryForObject(query, (rs, rowNum) -> new ContractDashboard(
                rs.getInt("numberOfContracts"),
                rs.getInt("numberOfActualContracts"),
                rs.getInt("serviceAvgTimeInDays"))
        );
    }

}
