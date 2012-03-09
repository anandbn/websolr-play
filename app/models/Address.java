package models;

import org.codehaus.jackson.annotate.JsonProperty;

public class Address {

	public String getGender() {
		return gender;
	}
	@JsonProperty("Gender")
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	@JsonProperty("StreetAddress")
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getCity() {
		return city;
	}
	@JsonProperty("City")
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	@JsonProperty("State")
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	@JsonProperty("ZipCode")
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	@JsonProperty("TelephoneNumber")
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public String getGivenName() {
		return givenName;
	}
	@JsonProperty("GivenName")
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private  String gender;
	private  String streetAddress;
	private  String city;
	private  String state;
	private  String zipCode;
	private  String telephoneNumber;
	private  String givenName;
	private  String id;
	
}
