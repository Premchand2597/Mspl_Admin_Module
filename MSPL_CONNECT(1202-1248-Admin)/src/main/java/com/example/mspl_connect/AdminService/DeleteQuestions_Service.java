package com.example.mspl_connect.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminRepo.DeleteQuestions_Repo;

import jakarta.transaction.Transactional;

@Service
public class DeleteQuestions_Service {

	@Autowired
	private DeleteQuestions_Repo deleteQuestions_Repo;
	
	@Transactional
	public String deleteQuestions(String languageSelected, int id) {
		
		if ("C".equals(languageSelected)) {
			deleteQuestions_Repo.delete_c_questions(id);
            return "success";
        }else if ("Aptitude".equals(languageSelected)) {
        	deleteQuestions_Repo.delete_apptitude_questions(id);
            return "success";
        } else if ("Java".equals(languageSelected)) {
        	deleteQuestions_Repo.delete_java_questions(id);
            return "success";
        } else if ("Python".equals(languageSelected)) {
        	deleteQuestions_Repo.delete_python_questions(id);
            return "success";
        }else if ("SQL".equals(languageSelected)) {
        	deleteQuestions_Repo.delete_sql_questions(id);
            return "success";
        } else {
            return "Invalid language selected";
        }
    }
}