package se.iths.jelleryd.worldpopulation.remoteserver.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * JSON object mapping for the "total life expectancy" Rest Service.
 * 
 * @author Lars Jelleryd
 */
public class TotalLifeExpectancy {

	@SerializedName("dob")
	@Expose
	private String dob;
	@SerializedName("country")
	@Expose
	private String country;
	@SerializedName("total_life_expectancy")
	@Expose
	private Double totalLifeExpectancy;
	@SerializedName("sex")
	@Expose
	private String sex;

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Double getTotalLifeExpectancy() {
		return totalLifeExpectancy;
	}

	public void setTotalLifeExpectancy(Double totalLifeExpectancy) {
		this.totalLifeExpectancy = totalLifeExpectancy;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "TotalLifeExpectancy{" + "dob=" + dob + ", country=" + country + ", totalLifeExpectancy="
				+ totalLifeExpectancy + ", sex=" + sex + '}';
	}

}
