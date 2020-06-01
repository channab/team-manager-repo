package com.test.team.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.team.response.model.WebResponse;
import com.test.team.service.UserAccessService;

@RestController
public class UserAccessController {

	@Autowired
	private UserAccessService userAccessService;
	
	@PutMapping("user-access")
	public ResponseEntity<WebResponse> addUserAccess(@RequestParam(name = "empid") String empId){
		
		userAccessService.addUserAccess(empId);
		
		return ResponseEntity.ok(new WebResponse("Success", "User accesses added for user", Response.SC_OK));
	}
	
	@PutMapping("users-access")
	public ResponseEntity<WebResponse> addUsersAccess(){
		
		userAccessService.addAllUsersAccess();
		
		return ResponseEntity.ok(new WebResponse("Success", "User accesses added for all users", Response.SC_OK));
	}
}
