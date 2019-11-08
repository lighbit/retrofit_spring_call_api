package netzme.model;

import com.google.gson.annotations.SerializedName;

public class Location {

	@SerializedName("street")
	private Street street;

	@SerializedName("city")
	private String city;

	public Street getStreet() {
		return street;
	}

	public void setStreet(Street street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
