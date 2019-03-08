package se.iths.jelleryd.worldpopulation.backingbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import se.iths.jelleryd.worldpopulation.remoteserver.client.RestClient;

/**
 * This bean holds the list of countries that are available for fetching
 * statistics. It is Application Scoped so the list is not constantly reloaded
 * (a caching timeout would be used for this in practice so it would be reloaded
 * once in a while). It will try to get the list from the Rest Service
 * immediately after creation, on failure it will try to reload it every time
 * the list is requested.
 * 
 * @author Lars Jelleryd
 */
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
