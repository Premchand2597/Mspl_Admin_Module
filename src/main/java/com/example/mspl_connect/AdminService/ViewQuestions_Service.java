package com.example.mspl_connect.AdminService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.AddQuestions_Entity;
import com.example.mspl_connect.AdminRepo.ViewQuestions_Repo;

import jakarta.transaction.Transactional;

@Service
public class ViewQuestions_Service {

	@Autowired
	private ViewQuestions_Repo viewQuestions_Repo;
	
	@Transactional
    public List<AddQuestions_Entity> getAllQuestions(String languageSelected) {
     
        switch (languageSelected) {
            case "C":
                return viewQuestions_Repo.fetch_all_c_questions();
            case "Aptitude":
                return viewQuestions_Repo.fetch_all_apptitude_questions();
            case "Java":
                return viewQuestions_Repo.fetch_all_java_questions();
            case "Python":
                return viewQuestions_Repo.fetch_all_python_questions();
            case "SQL":
                return viewQuestions_Repo.fetch_all_sql_questions();
            default:
                return List.of();
        }
    }
}
