package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.UserEntity.DepartmentDocument;


@Repository
public interface DepartmentDocumentRepository extends JpaRepository<DepartmentDocument, Long>{
	//List<DepartmentDocument> findByDepartment(String department);
	List<DepartmentDocument> findByDepartment(Long department); // CORRECT

    List<DepartmentDocument> findByBookOfKnowledgeDeptId(Long deptId);
}
