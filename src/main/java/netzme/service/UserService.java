package netzme.service;

import java.util.List;
import netzme.model.Result;
import netzme.model.User;

public interface UserService {

	public User getUser(User user);

	public List<User> getManyUser(User user);

	public Result getPerson(Result person);

	public List<Result> getPersonList(Result results);

}
