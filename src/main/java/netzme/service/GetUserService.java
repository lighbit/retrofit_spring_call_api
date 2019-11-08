package netzme.service;

import java.util.List;
import netzme.model.Person;
import netzme.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetUserService {

	@GET("/api")
	public Call<Person> getPosts();

	@GET("/users")
	public Call<List<User>> getUsers(@Query("per_page") int per_page, @Query("page") int page);

	@GET("/users/{company}")
	public Call<User> getUser(@Path("company") String username);

	@GET("/api")
	public Call<Person> getUserList(@Query("results") int string);

}
