package com.miolivc.contract.management.api.database;

import com.miolivc.contract.management.api.domain.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

    @Query("SELECT c FROM Contract c WHERE EXTRACT(DAY FROM c.expirationDate - NOW()) <= :daysToExpire")
    List<Contract> getContractsDueIn(@Param("daysToExpire") Integer daysToExpire);

}
