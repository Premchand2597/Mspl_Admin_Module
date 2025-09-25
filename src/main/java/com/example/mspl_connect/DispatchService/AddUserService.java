package com.example.mspl_connect.DispatchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.DispatchEntity.AddUser;
import com.example.mspl_connect.DispatchRepo.AddUserRepository;


@Service
public class AddUserService {
	
	@Autowired
	private AddUserRepository addUserRepository;
	
	public AddUser saveUser(AddUser addUser) {
        return addUserRepository.save(addUser);
    }
	
	public void deleteData(long id) {
		addUserRepository.deleteById(id);
	}
	
	public boolean isUserNameExistsOrNot(String name) {
		String exists = addUserRepository.isUserNameExists(name);
		if(exists != null) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isUserEmailExistsOrNot(String email) {
		String exists = addUserRepository.isUserEmailExists(email);
		if(exists != null) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isContactNoExists(String contact_no) {
		String exists = addUserRepository.isContactNoExists(contact_no);
		if(exists != null) {
			return true;
		}else {
			return false;
		}
	}
}
