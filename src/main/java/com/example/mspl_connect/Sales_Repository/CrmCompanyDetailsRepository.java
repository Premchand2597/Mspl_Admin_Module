package com.example.mspl_connect.Sales_Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Sales_Entity.CrmCompanyDetails;


@Repository
public interface CrmCompanyDetailsRepository extends JpaRepository<CrmCompanyDetails, Long> {
	@Query("SELECT c FROM CrmCompanyDetails c LEFT JOIN FETCH c.contactPersons WHERE c.id = :id")
    Optional<CrmCompanyDetails> findByIdWithContacts(@Param("id") Long id);
}
