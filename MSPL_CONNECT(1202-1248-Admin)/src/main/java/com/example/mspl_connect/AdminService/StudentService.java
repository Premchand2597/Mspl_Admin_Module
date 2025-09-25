package com.example.mspl_connect.AdminService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.Candidate_Entity;
import com.example.mspl_connect.AdminEntity.LinkGeneratedMoatUserTableEntity;
import com.example.mspl_connect.AdminRepo.Link_generated_table_repo;
import com.example.mspl_connect.AdminRepo.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class StudentService {
	
	 @Autowired
	 private StudentRepository repository;
	 
	 @Autowired
	 private Link_generated_table_repo generated_table_repo; 
	 
	 private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	 
	 @Transactional
	 public Candidate_Entity save(Candidate_Entity candidate_Entity) {
	  return repository.save(candidate_Entity);
	 }
	 
	 @Transactional
	 public LinkGeneratedMoatUserTableEntity fetchLinkGeneratedAllDetailsBasedOnRegisteredEmailId(String candidateEmail) {
		 return generated_table_repo.fetchLinkGeneratedAllDataBasedOnRegisteredEmailId(candidateEmail);
	 }
	 
	 @Transactional
	 public void deleteLinkDataByToken(String token) {
		 generated_table_repo.deleteTokenDataBasedOnToken(token);
	 }
	 
	 @Transactional
	 public boolean isEmailExists(String email) {
	      return repository.existsByEmail(email);
	 }
	 
	 public void setOngoingTestStatusFlag(String candidate_id) {
		 repository.updateCandidateOngoingTestStatusFlag(candidate_id);
	 }
	 
	 public void setCompletedTestStatusFlag(String candidate_id) {
		 repository.updateCandidateCompletedTestStatusFlag(candidate_id);
	 }
	 
	 public void resetTestStatusFlag(String candidate_id) {
		 repository.resetCandidateTestStatusFlag(candidate_id);
	 }
	 
	 @Transactional
	    public void updateCandidateDetails(LinkGeneratedMoatUserTableEntity candidateDetails, LocalDateTime firstAccessTime, LocalDateTime expirationTime) {
	        
		// Null check on firstAccessTime and expirationTime
		    if (firstAccessTime != null && expirationTime != null) {
		        // Convert LocalDateTime to String using formatter
		        String firstAccessTimeString = firstAccessTime.format(formatter);
		        String expirationTimeString = expirationTime.format(formatter);

		        // Set the formatted strings to the candidate entity
		        candidateDetails.setFirstAccessTime(firstAccessTimeString);
		        candidateDetails.setExpirationTime(expirationTimeString);
		    }
		    
		    // Set test valid flag to "0" regardless of null or not
		    candidateDetails.setTest_valid_flag("0");
		    
	        // Save the entity
	        generated_table_repo.save(candidateDetails);
	    }
	 
	 @Transactional
	 public boolean updateDocumentsForSelectedCandidates(String candidate_id, String profilePicPath, String panPicPath,
			 String aadharPic, String voterIdPic, String tenthPic, String pucPic, String degreePic, 
			 String expLetterPic, String pgPic, String payslipPic) {
		 
		 int rowsAffected = repository.updateSelectedCandidatesDocs(candidate_id, profilePicPath, panPicPath, aadharPic, voterIdPic, tenthPic, pucPic, degreePic, expLetterPic, pgPic, payslipPic);
		 
		 return rowsAffected > 0;
		 
		/* if(updatedValue > 0) {
			 return "Success";
		 }else {
			 return "null";
		 } */
	 }
}
