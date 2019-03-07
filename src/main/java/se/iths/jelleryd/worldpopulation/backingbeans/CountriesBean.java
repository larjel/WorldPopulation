package se.iths.jelleryd.worldpopulation.backingbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import se.iths.jelleryd.worldpopulation.remoteserver.client.RestClient;

@ManagedBean(name = "countries")
@ApplicationScoped
public class CountriesBean {

	private List<String> countries;

	public CountriesBean() {
	}

	@PostConstruct
	private void updateCountryList() {
		try {
			this.countries = RestClient.getCountryList();
		} catch (Exception e) {
			System.err.println("Error! Could not populate Country list. Rest service unavailable?");
		}
	}

	public List<String> getCountries() {
		if (countries == null) {
			updateCountryList();
		}
		return countries;
	}

}
