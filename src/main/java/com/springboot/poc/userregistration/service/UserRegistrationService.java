package com.springboot.poc.userregistration.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.springboot.poc.userregistration.model.User;
import com.springboot.poc.userregistration.model.UserBean;
import com.springboot.poc.userregistration.repository.UserRegistrationRepository;

@Component
public class UserRegistrationService {

	@Autowired
	UserRegistrationRepository userRegistrationRepository;
	
	public User registerUser(User user) {
		return userRegistrationRepository.save(user);
	}
	
	public List<User> getAllUsers(){
		return (List<User>) userRegistrationRepository.findAll();
	}
	
	public User getUser(long id) {
		return userRegistrationRepository.findById(id).get();
	}
		
	public void delete(long id) {
		userRegistrationRepository.deleteById(id);
    }
	
	public User updateUser(User updatedUser) {
		
		try{
			Optional<User> user = userRegistrationRepository.findById(updatedUser.getId());
			if(user.get() != null) {
				return userRegistrationRepository.save(updatedUser);
			}else {
				return null;
			}
			
		}catch(NoSuchElementException nsee) {
			return null;
		}
	}
    
	public List<User> getPagenatedListofUsers(int page, int size){
		Pageable pageable = PageRequest.of(page, size);
		return userRegistrationRepository.findFirstAndLastName(pageable);
	}
	
	private User mapUserBeanToUser(UserBean userBean) {
		User user = new User();
		user.setAddress(userBean.getAddress());
		user.setFirstName(userBean.getFirstName());
		user.setLastName(userBean.getLastName());
		user.setEmail(userBean.getEmail());
		user.setNumber(userBean.getNumber());
		
		return user;
	}
}
