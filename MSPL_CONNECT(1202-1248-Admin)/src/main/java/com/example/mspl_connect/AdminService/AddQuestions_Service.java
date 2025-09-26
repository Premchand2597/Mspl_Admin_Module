package com.example.mspl_connect.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.AddQuestions_Entity;
import com.example.mspl_connect.AdminRepo.AddQuestions_Repo;

import jakarta.transaction.Transactional;

@Service
public class AddQuestions_Service {

	@Autowired
	private AddQuestions_Repo addQuestions_Repo;
	
	@Transactional
    public String insertQuestions(String languageSelected, String questions, String op1, String op2, String op3, 
                                                        String op4, String answers) {
		
		AddQuestions_Entity addQuestions_Entity = null;
        
        if ("C".equals(languageSelected)) {
        	int result = addQuestions_Repo.insertCQuestions(questions, op1, op2, op3, op4, answers);
        	if(result > 0) {
        		addQuestions_Entity = addQuestions_Repo.fetchRecentlyInsertedCQuestionDetails();
        		addQuestions_Repo.insertCQuestionsBackup(questions, addQuestions_Entity.getId(), op1, op2, op3, op4, answers);
        	}
            return "success";
        }else if ("Aptitude".equals(languageSelected)) {
        	int result = addQuestions_Repo.insertApptitudeQuestions(questions, op1, op2, op3, op4, answers);
        	if(result > 0) {
        		addQuestions_Entity = addQuestions_Repo.fetchRecentlyInsertedAptitudeQuestionDetails();
        		addQuestions_Repo.insertApptitudeQuestionsBackup(questions, addQuestions_Entity.getId(), op1, op2, op3, op4, answers);
        	}
            return "success";
        } else if ("Java".equals(languageSelected)) {
        	int result = addQuestions_Repo.insertJavaQuestions(questions, op1, op2, op3, op4, answers);
        	if(result > 0) {
        		addQuestions_Entity = addQuestions_Repo.fetchRecentlyInsertedJavaQuestionDetails();
        		addQuestions_Repo.insertJavaQuestionsBackup(questions, addQuestions_Entity.getId(), op1, op2, op3, op4, answers);
        	}
            return "success";
        } else if ("Python".equals(languageSelected)) {
        	int result = addQuestions_Repo.insertPythonQuestions(questions, op1, op2, op3, op4, answers);
        	if(result > 0) {
        		addQuestions_Entity = addQuestions_Repo.fetchRecentlyInsertedPythonQuestionDetails();
        		addQuestions_Repo.insertPythonQuestionsBackup(questions, addQuestions_Entity.getId(), op1, op2, op3, op4, answers);
        	}
            return "success";
        }else if ("SQL".equals(languageSelected)) {
        	int result = addQuestions_Repo.insertSqlQuestions(questions, op1, op2, op3, op4, answers);
        	if(result > 0) {
        		addQuestions_Entity = addQuestions_Repo.fetchRecentlyInsertedSQLQuestionDetails();
        		addQuestions_Repo.insertSqlQuestionsBackup(questions, addQuestions_Entity.getId(), op1, op2, op3, op4, answers);
        	}
            return "success";
        } else {
            return "Invalid language selected";
        }
    }
}