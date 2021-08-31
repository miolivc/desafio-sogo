package com.miolivc.contract.management.api.database;

import com.miolivc.contract.management.api.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@RepositoryRestResource
public interface PersonRepository extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {

}
