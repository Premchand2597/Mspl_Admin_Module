package com.example.mspl_connect.AdminService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.Releasenote;
import com.example.mspl_connect.AdminRepo.ReleaseNoteRepo;
import com.example.mspl_connect.AdminRepo.ReleaseUpdateRepo;

import jakarta.transaction.Transactional;

@Service
public class ReleaseNoteService {

	@Autowired
    private ReleaseNoteRepo releaseNoteRepository;
	
	@Autowired
    private ReleaseUpdateRepo releaseUpdateRepo;

    public Optional<Releasenote> getLatestDocumentByModule(String module) {
        return releaseNoteRepository.findTopByModuleOrderByIdDesc(module);
    }
    
    @Transactional
    public void updateReleaseNoteFlagValue(String empId) {
    	System.out.println("hrlllloooo");
    	releaseUpdateRepo.updateReleaseNoteFlag(empId);
    }
	
}
