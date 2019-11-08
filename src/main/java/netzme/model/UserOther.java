package netzme.model;

import java.util.Map;

import com.google.gson.annotations.SerializedName;

public class UserOther {

	@SerializedName("gender")
	private String gender;

	@SerializedName("name")
	private Name name;

	@SerializedName("location")
	private Location location;

	@SerializedName("picture")
	private Map<Object, String> picture;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Map<Object, String> getPicture() {
		return picture;
	}

	public void setPicture(Map<Object, String> picture) {
		this.picture = picture;
	}

}
