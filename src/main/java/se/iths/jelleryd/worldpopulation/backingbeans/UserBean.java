package se.iths.jelleryd.worldpopulation.backingbeans;

import java.util.Date;

import javax.faces.bean.ManagedBean;

import se.iths.jelleryd.worldpopulation.remoteserver.client.RestClient;

@ManagedBean(name = "user")
public class UserBean {

	private final String[] GENDERS = { "male", "female" };

	private String country;
	private String gender;
	private Date dateOfBirth;
	private String totalLifeExpectancy = "Unknown";

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTotalLifeExpectancy() {
		if (country != null && gender != null && dateOfBirth != null) {
			try {
				totalLifeExpectancy = RestClient.getTotalLifeExpectancy(gender, country, dateOfBirth);
				totalLifeExpectancy += " years";
			} catch (Exception e) {
				totalLifeExpectancy = "Error! Rest service not available?";
			}
		}
		return totalLifeExpectancy;
	}

	public String[] getGENDERS() {
		return GENDERS;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

}
