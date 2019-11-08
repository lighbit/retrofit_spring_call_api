package netzme.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import netzme.model.Result;
import netzme.model.User;
import netzme.service.UserService;

@RestController
public class MyController {

	@Autowired
	UserService userService;

	@RequestMapping("/")
	public String welcome() {	// Welcome page, non-rest
		return "Welcome to RestTemplate Example.";
	}

	// TODO: TEST WITH OTHER API
	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public User getUser() {
		User user = new User();

		return userService.getUser(user);
	}

	// TODO: TEST LIST OTHER API
	@RequestMapping(value = "/users/", method = RequestMethod.GET)
	public List<User> getUsers() {
		User users = new User();

		List<User> user = userService.getManyUser(users);

		return user;
	}

	// TODO: CALL RANDOM USER TO https://randomuser.me/
	@RequestMapping(value = "/person/", method = RequestMethod.GET)
	public Result getPerson() {
		Result result = new Result();

		return userService.getPerson(result);
	}
	
	// TODO: CALL RANDOM USER LIST TO https://randomuser.me/
	@RequestMapping(value = "/persons/", method = RequestMethod.GET)
	public List<Result> getPersonList() {
		Result results = new Result();
		
		List<Result> result = userService.getPersonList(results);

		return result;
	}
}