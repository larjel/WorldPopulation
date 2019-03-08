package se.iths.jelleryd.worldpopulation.remoteserver.json;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * JSON object mapping for the "countries" Rest Service.
 * 
 * @author Lars Jelleryd
 */
public class Countries {

	@SerializedName("countries")
	@Expose
	private List<String> countries = null;

	public List<String> getCountries() {
		return countries;
	}

	public void setCountries(List<String> countries) {
		this.countries = countries;
	}

	@Override
	public String toString() {
		return "Countries{" + "countries=" + countries + '}';
	}

}
