package com.example.mspl_connect.AdminRepo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.Releasenote;

import jakarta.transaction.Transactional;

@Repository
public interface ReleaseNoteRepo extends JpaRepository<Releasenote, Long> {

	 // This method will fetch the document with the highest ID (i.e. latest) for a given module.
    Optional<Releasenote> findTopByModuleOrderByIdDesc(String module);
    
    
    
    
}
