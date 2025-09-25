package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mspl_connect.UserEntity.DocumentView;

public interface DocumentViewRepository  extends JpaRepository<DocumentView, Long>{
	 List<DocumentView> findByDocumentIdOrderByViewTimeDesc(Long documentId);
	
}
