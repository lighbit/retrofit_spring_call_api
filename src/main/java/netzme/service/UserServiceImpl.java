package netzme.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import netzme.model.Person;
import netzme.model.Result;
import netzme.model.User;
import retrofit2.Call;
import retrofit2.Response;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	RetrofitService retrofitService;

	@Value("${personGet}")
	private int counting;

	@Value("${usernameGet}")
	private String username;

	@Value("${pagesGet}")
	private int page_page;

	@Value("${pageGet}")
	private int page;

	public User getUser(User user) {

		// TODO: Using RetrofitGenerator
		GetUserService getUserService = retrofitService.createService(GetUserService.class);
		Call<User> callSync = getUserService.getUser(username);

		try {
			Response<User> response = callSync.execute();
			user = response.body();
			user.setCode(response.code());
		} catch (IOException ex) {
			user.setMessage(ex.getMessage());

		}

		return user;

	}

	public List<User> getManyUser(User user) {
		List<User> users = null;
		// TODO: Using RetrofitGenerator
		GetUserService getUserService = retrofitService.createService(GetUserService.class);
		Call<List<User>> callSync = getUserService.getUsers(page_page, page);

		try {
			Response<List<User>> response = callSync.execute();
			users = response.body();

		} catch (IOException ex) {
		}
		return users;
	}

	// TODO: GET PERSON 1
	public Result getPerson(Result result) {

		// TODO: Using RetrofitGenerator
		GetUserService getUserService = retrofitService.createServicePerson(GetUserService.class);
		Call<Person> callSync = getUserService.getPosts();

		try {
			Response<Person> response = callSync.execute();
			Person person = new Person();

			person = response.body();

			result.setGender(person.getResults().get(0).getGender());
			result.setFullname(person.getResults().get(0).getName().getTitle() + " "
					+ person.getResults().get(0).getName().getFirst() + " "
					+ person.getResults().get(0).getName().getLast());
			result.setAddress(person.getResults().get(0).getLocation().getStreet().getName() + " "
					+ person.getResults().get(0).getLocation().getStreet().getNumber() + " "
					+ person.getResults().get(0).getLocation().getCity());
			result.setPicture(person.getResults().get(0).getPicture().get("large"));
			result.setCode(response.code());

		} catch (IOException ex) {
			result.setMessaage(ex.getMessage());
			result.setCode(522);
		}
		return result;

	}

	// TODO: LIST GET PERSON
	public List<Result> getPersonList(Result result) {

		Person persons = null;

		// TODO: Using RetrofitGenerator
		GetUserService getUserService = retrofitService.createServicePerson(GetUserService.class);
		Call<Person> callSync = getUserService.getUserList(counting);

		List<Result> results = new ArrayList<Result>();

		try {
			Response<Person> response = callSync.execute();
			persons = response.body();

			// TODO: LIST WITH FOR EACH
			for (Integer i = 0; i < persons.getInfo().getResults(); i++) {
				result = new Result();

				result.setGender(persons.getResults().get(i).getGender());
				result.setFullname(persons.getResults().get(i).getName().getTitle() + " "
						+ persons.getResults().get(i).getName().getFirst() + " "
						+ persons.getResults().get(i).getName().getLast());
				result.setAddress(persons.getResults().get(i).getLocation().getStreet().getName() + " "
						+ persons.getResults().get(i).getLocation().getStreet().getNumber() + " "
						+ persons.getResults().get(i).getLocation().getCity());
				result.setPicture(persons.getResults().get(i).getPicture().get("large"));
				result.setCode(response.code());

				results.add(result);
			}

		} catch (IOException ex) {
			result.setMessaage(ex.getMessage());
			result.setCode(522);
		}
		return results;

	}

}
