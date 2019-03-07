package se.iths.jelleryd.worldpopulation.remoteserver.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PopulationAgeGroup {

	@SerializedName("females")
	@Expose
	private Integer females;
	@SerializedName("country")
	@Expose
	private String country;
	@SerializedName("age")
	@Expose
	private Integer age;
	@SerializedName("males")
	@Expose
	private Integer males;
	@SerializedName("year")
	@Expose
	private Integer year;
	@SerializedName("total")
	@Expose
	private Integer total;

	public Integer getFemales() {
		return females;
	}

	public void setFemales(Integer females) {
		this.females = females;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getMales() {
		return males;
	}

	public void setMales(Integer males) {
		this.males = males;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "PopulationAgeGroup [females=" + females + ", country=" + country + ", age=" + age + ", males=" + males
				+ ", year=" + year + ", total=" + total + "]";
	}

}
