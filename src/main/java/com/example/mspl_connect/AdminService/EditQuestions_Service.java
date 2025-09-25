package com.example.mspl_connect.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminRepo.EditQuestions_Repo;

import jakarta.transaction.Transactional;

@Service
public class EditQuestions_Service {
	
	@Autowired
	private EditQuestions_Repo editQuestions_Repo;
	
	@Transactional
	public String updateQuestions(String languageSelected, int id, String questions, String op1, String op2, String op3, String op4, String answers) {
		
		if ("C".equals(languageSelected)) {
			editQuestions_Repo.update_c_questions(id, questions, op1, op2, op3, op4, answers);
            return "success";
        }else if ("Aptitude".equals(languageSelected)) {
        	editQuestions_Repo.update_apptitude_questions(id, questions, op1, op2, op3, op4, answers);
            return "success";
        } else if ("Java".equals(languageSelected)) {
        	editQuestions_Repo.update_java_questions(id, questions, op1, op2, op3, op4, answers);
            return "success";
        } else if ("Python".equals(languageSelected)) {
        	editQuestions_Repo.update_python_questions(id, questions, op1, op2, op3, op4, answers);
            return "success";
        }else if ("SQL".equals(languageSelected)) {
        	editQuestions_Repo.update_sql_questions(id, questions, op1, op2, op3, op4, answers);
            return "success";
        } else {
            return "Invalid language selected";
        }
    }
}