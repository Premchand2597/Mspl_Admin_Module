package com.example.mspl_connect.UserRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.UserEntity.BookofknowledgeDepartment;

@Repository
public interface BookofKnowledgeDepartmentRepository extends JpaRepository<BookofknowledgeDepartment, Long> {
	 Optional<BookofknowledgeDepartment> findByDeptName(String deptName);
}
