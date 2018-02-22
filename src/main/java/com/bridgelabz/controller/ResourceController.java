package com.bridgelabz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.model.User;
import com.bridgelabz.service.GenericService;

@RestController
@RequestMapping("/springjwt")
@PreAuthorize("hasAuthority('STANDARD_USER')")
public class ResourceController {
	@Autowired
	private GenericService userService;

	@RequestMapping(value = "/users/{name}")
	//@PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
	public User getUser(@PathVariable("name") String username) {
		System.out.println("Name got: "+username);
		String name = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("From context: " + name);
		return userService.findByUsername(username);
	}

	@RequestMapping(value = "/admin/users", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN_USER')")
	public List<User> getUsers() {
		return userService.findAllUsers();
	}
}
