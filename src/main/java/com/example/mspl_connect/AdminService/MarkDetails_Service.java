package com.example.mspl_connect.AdminService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.MarkDetails_Entity;
import com.example.mspl_connect.AdminRepo.MarkDetails_Repo;

import jakarta.transaction.Transactional;

@Service
public class MarkDetails_Service {
	
	@Autowired
	private MarkDetails_Repo markDetails_Repo;

	@Transactional
	public List<MarkDetails_Entity> getMarkDetails(String student_id){
		return markDetails_Repo.fetch_studentMarksDetails_ByStudentId(student_id);
	}
}
