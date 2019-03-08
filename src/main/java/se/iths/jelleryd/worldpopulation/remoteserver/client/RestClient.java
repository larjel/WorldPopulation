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

/**
 * All calls to the remote Rest Service is handled in this class. The service
 * used can be found here: http://api.population.io
 * 
 * @author Lars Jelleryd
 */
public class RestClient {

	/**
	 * The base URL to the remote Rest Service.
	 */
	private static final String REST_SERVER_BASE_URL = "http://api.population.io/1.0/";

	/**
	 * Handles all the JSON response parsing
	 */
	private static final Gson GSON = new Gson();

	/**
	 * Replace all spaces in the Rest URLs with %20. Spaces will normally only be
	 * found in the 'country' part of the URL (eg. United Kingdom).
	 * 
	 * @param url URL to process
	 * @return URL with spaces replaced with %20
	 */
	private static String encodeUrlSpaces(String url) {
		return url.replace(" ", "%20");
	}

	/**
	 * Perform a HTTP GET using given URI. The URL used will be comprised by the
	 * base URL (REST_SERVER_BASE_URL) appended by the given URI. JSON will be the
	 * requested response format.
	 * 
	 * @param uri URI to append to REST_SERVER_BASE_URL
	 * @return HTTP response body (should be JSON format)
	 * @throws IOException On error
	 */
	private static String performGet(String uri) throws IOException {
		URL url = new URL(encodeUrlSpaces(REST_SERVER_BASE_URL + uri));
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

	/**
	 * Get list of countries from remote Rest Service using URI "countries".
	 * 
	 * @return List of countries
	 * @throws IOException         on IO error
	 * @throws JsonSyntaxException if bad/unknown JSON response
	 */
	public static List<String> getCountryList() throws IOException, JsonSyntaxException {
		String uri = "countries";

		String jsonResponse = performGet(uri);

		Countries countries = GSON.fromJson(jsonResponse, Countries.class);

		return countries.getCountries();
	}

	/**
	 * Get total life expectancy from remote Rest Service based on given input. URI
	 * example: "life-expectancy/total/male/Sweden/1952-03-11/".
	 * 
	 * @param gender      "male" or "female"
	 * @param country     The country to get data for (as retrieved by the
	 *                    "countries" URI)
	 * @param dateOfBirth Date of birth of the person to do calculation for
	 * @return Total life expectancy in years with one decimal
	 * @throws IOException         on IO error
	 * @throws JsonSyntaxException if bad/unknown JSON response
	 */
	public static String getTotalLifeExpectancy(String gender, String country, Date dateOfBirth)
			throws IOException, JsonSyntaxException {
		String dateOfBirthFormatted = new SimpleDateFormat("yyyy-MM-dd").format(dateOfBirth);

		String uri = "life-expectancy/total/" + gender + "/" + country + "/" + dateOfBirthFormatted;

		String jsonResponse = performGet(uri);

		TotalLifeExpectancy totalLifeExpectancy = GSON.fromJson(jsonResponse, TotalLifeExpectancy.class);

		return String.format("%.1f", totalLifeExpectancy.getTotalLifeExpectancy());
	}

	/**
	 * Get population table for a specific age group in the given year and country
	 * from remote Rest Service. Used URI example: "population/1980/Brazil/18/".
	 * 
	 * @param country The country to get data for (as retrieved by the "countries"
	 *                URI)
	 * @param year    Year to get data for
	 * @param age     Age of population to get data for
	 * @return Retrieved population data
	 * @throws IOException         on IO error
	 * @throws JsonSyntaxException if bad/unknown JSON response
	 */
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
