package com.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.entity.AuthResponse;
import com.user.entity.Login;
import com.user.entity.OutputModel;
import com.user.entity.User;
import com.user.security.jwt.JwtUtils;
import com.user.service.UserImplementation;
import com.user.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService customerService;
	
	@GetMapping(value = "/welcome")
	public String test() {
		return "Hello !!!!";
	} 
	/**
	 * @author User registration
	 *
	 */
	@PostMapping("User/registration")
	Integer createUser(@Valid @RequestBody User user) {
		Integer id = customerService.saveUser(user);
		return id;
	}
	
	
	@Autowired
	private JwtUtils jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserImplementation useraccountServiceImpl;

	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@RequestBody Login login) throws Exception {

		try { 

			User useraccount = useraccountServiceImpl.getUseraccountByUsername(login.getUsername());
			if (useraccount != null) {
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));

				String jwt = jwtUtil.generateToken(login.getUsername());
				AuthResponse auth = new AuthResponse(jwt, useraccount.getId(), useraccount.getUserName(), "",
						useraccount.getName(), useraccount.getEmail(),true,"Login Successfully");
				return ResponseEntity.ok(auth);

			} else {
				OutputModel outputModel = new OutputModel(false, "username or password invalid");
				return ResponseEntity.ok(outputModel);
			}

		} catch (Exception e) {
			System.err.println(e);
			OutputModel outputModel = new OutputModel(false, "username or password invalid");
			return ResponseEntity.ok(outputModel);
			
		}
	}
	
	/**
	
	
	@GetMapping("/getSession/{token}")
	public ResponseEntity<?> getSession(@PathVariable String token) throws Exception {
		try {
			Useraccount useraccount = useraccountServiceImpl.getUseraccountByUsername(login.getUsername());
			if (useraccount != null) {
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
				String jwt = jwtUtil.generateToken(login.getUsername());
				AuthResponse auth = new AuthResponse(jwt, useraccount.getUserId(), useraccount.getUserName(), "",
						useraccount.getFirstName(), useraccount.getLastName());
				return ResponseEntity.ok(auth);
			} else {
				OutputModel outputModel = new OutputModel(false, "username or password invalid");
				return ResponseEntity.ok(outputModel);
			}
		} catch (Exception e) {
			System.err.println(e);
			OutputModel outputModel = new OutputModel(false, "username or password invalid");
			return ResponseEntity.ok(outputModel);
			
		}
	}
**/
	
}
