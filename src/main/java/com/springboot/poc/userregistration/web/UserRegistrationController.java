package com.springboot.poc.userregistration.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.poc.userregistration.model.User;
import com.springboot.poc.userregistration.service.UserRegistrationService;

@RestController
@RequestMapping("/registration")
public class UserRegistrationController {

	@Autowired
	UserRegistrationService userRegistrationService;
	
	@GetMapping
	public String welcome() {	
		return "Welcome to the user registation API.";
	}
	
	@RequestMapping(method = RequestMethod.POST,
            value = "/registerUser",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public User registerUser(@RequestBody User user) {
		return userRegistrationService.registerUser(user);
	}
	
	@GetMapping(value="/getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUser() {
        List<User> users= userRegistrationService.getAllUsers();
        return users;

    }
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
	        User user = userRegistrationService.getUser(id);
	        if (user == null) {
	            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<User>(user, HttpStatus.OK);
	    }
	
	 @PutMapping(value="/update", consumes = MediaType.APPLICATION_JSON_VALUE,
	            produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<User> updateUser(@RequestBody User updatedUser)
	    {
	        User user = userRegistrationService.updateUser(updatedUser);
	        
	        if (user == null) {
	            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<User>(user, HttpStatus.OK);
	    }
	
	 @RequestMapping(method = RequestMethod.GET, value = "/{pageNumber}/{size}", produces = MediaType.APPLICATION_JSON_VALUE)
	    public List<User> getPageableUsers(@PathVariable("pageNumber") int pageNumber, @PathVariable("size") int size){
		 return userRegistrationService.getPagenatedListofUsers(pageNumber, size);
	    }
	
}
