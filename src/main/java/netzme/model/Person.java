package netzme.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Person {

	@SerializedName("results")
	private List<UserOther> results;

	@SerializedName("info")
	private Info info;

	public List<UserOther> getResults() {
		return results;
	}

	public void setResults(List<UserOther> results) {
		this.results = results;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

}