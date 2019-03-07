package se.iths.jelleryd.worldpopulation.backingbeans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import se.iths.jelleryd.worldpopulation.remoteserver.client.RestClient;
import se.iths.jelleryd.worldpopulation.remoteserver.json.PopulationAgeGroup;

@ManagedBean(name = "population")
public class PopulationBean {

	private static final int MIN_YEAR = 1950;
	private static final int MAX_YEAR = 2100;
	private static final int MIN_AGE = 0;
	private static final int MAX_AGE = 100;

	private String country;
	private int age = MIN_AGE;
	private int year = MIN_YEAR;

	private int females;
	private int males;
	private int total;

	private int[] availableYears = new int[MAX_YEAR - MIN_YEAR + 1];
	private int[] availableAges = new int[MAX_AGE - MIN_AGE + 1];

	@PostConstruct
	private void populateArrays() {
		for (int index = 0, year = MIN_YEAR; year <= MAX_YEAR; index++, year++) {
			availableYears[index] = year;
		}
		for (int index = 0, age = MIN_AGE; age <= MAX_AGE; index++, age++) {
			availableAges[index] = age;
		}
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getFemales() {
		if (country != null) {
			try {
				PopulationAgeGroup populationAgeGroup = RestClient.getPopulationAgeGroup(country, year, age);
				females = populationAgeGroup.getFemales();
				males = populationAgeGroup.getMales();
				total = populationAgeGroup.getTotal();
			} catch (Exception e) {
			}
		}
		return females;
	}

	public int getMales() {
		return males;
	}

	public int getTotal() {
		return total;
	}

	public int[] getAvailableYears() {
		return availableYears;
	}

	public int[] getAvailableAges() {
		return availableAges;
	}

}
