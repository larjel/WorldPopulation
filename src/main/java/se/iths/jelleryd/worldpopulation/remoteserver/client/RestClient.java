package se.iths.jelleryd.worldpopulation.remoteserver.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import se.iths.jelleryd.worldpopulation.remoteserver.json.Countries;
import se.iths.jelleryd.worldpopulation.remoteserver.json.PopulationAgeGroup;
import se.iths.jelleryd.worldpopulation.remoteserver.json.TotalLifeExpectancy;

public class RestClient {

	private static final String REST_SERVER_BASE_URL = "http://api.population.io/1.0/";

	private static final Gson GSON = new Gson();

	// -------------------------------------------------------------------------
	private static String performGet(String uri) throws IOException {
		URL url = new URL(REST_SERVER_BASE_URL + uri);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		con.setRequestMethod("GET");
		con.setRequestProperty("Accept", "application/json");

		String content = "";
		if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
			try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					content += inputLine;
				}
			}
		}
		return content;
	}

	// -------------------------------------------------------------------------
	public static List<String> getCountryList() throws IOException, JsonSyntaxException {
		String uri = "countries";

		String jsonResponse = performGet(uri);

		Countries countries = GSON.fromJson(jsonResponse, Countries.class);

		return countries.getCountries();
	}

	// -------------------------------------------------------------------------
	public static String getTotalLifeExpectancy(String gender, String country, Date dateOfBirth)
			throws IOException, JsonSyntaxException {
		String dateOfBirthFormatted = new SimpleDateFormat("yyyy-MM-dd").format(dateOfBirth);

		String uri = "life-expectancy/total/" + gender + "/" + country + "/" + dateOfBirthFormatted;

		String jsonResponse = performGet(uri);

		TotalLifeExpectancy totalLifeExpectancy = GSON.fromJson(jsonResponse, TotalLifeExpectancy.class);

		return String.format("%.1f", totalLifeExpectancy.getTotalLifeExpectancy());
	}

	// -------------------------------------------------------------------------
	public static PopulationAgeGroup getPopulationAgeGroup(String country, int year, int age)
			throws IOException, JsonSyntaxException {

		String uri = "population/" + year + "/" + country + "/" + age;

		String jsonResponse = performGet(uri);

		// Must parse as array since the JSON is returned as an array with a single
		// element [{..content..}]
		PopulationAgeGroup[] populationAgeGroup = GSON.fromJson(jsonResponse, PopulationAgeGroup[].class);

		return populationAgeGroup[0];
	}

}
