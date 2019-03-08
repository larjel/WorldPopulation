package se.iths.jelleryd.worldpopulation;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.JsonSyntaxException;

import se.iths.jelleryd.worldpopulation.remoteserver.client.RestClient;
import se.iths.jelleryd.worldpopulation.remoteserver.json.PopulationAgeGroup;

/**
 * Unit test for the RestClient.
 */
public class RestClientTest {
	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testGetCountryList() {
		try {
			List<String> countries = RestClient.getCountryList();
			assertNotNull("null pointer returned from getCountryList()", countries);
			assertTrue("Returned country list does not contain \"Sweden\"?", countries.contains("Sweden"));
		} catch (JsonSyntaxException | IOException e) {
			fail("Unexpected exception. Internet available?");
		}
	}

	@Test
	public void testGetTotalLifeExpectancy() {
		String totalLifeExpectancy = "";
		try {
			Date dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(("1952-03-11"));
			totalLifeExpectancy = RestClient.getTotalLifeExpectancy("male", "United Kingdom", dateOfBirth);
			assertNotNull("null pointer returned from getTotalLifeExpectancy()", totalLifeExpectancy);
			assertTrue("Empty string returned from getTotalLifeExpectancy()", !totalLifeExpectancy.isEmpty());
			// Depending on locale, the string may contain a decimal comma separator, if so
			// replace with decimal point instead so we may parse it
			totalLifeExpectancy = totalLifeExpectancy.replace(',', '.');
			// Try to parse to verify it contains a double value
			Double.parseDouble(totalLifeExpectancy);
		} catch (NumberFormatException e) {
			fail("Could not parse totalLifeExpectancy, it contained the value: " + totalLifeExpectancy);
		} catch (IOException | ParseException e) {
			fail("Unexpected exception. Internet available?");
		}
	}

	@Test
	public void testGetPopulationAgeGroup() {
		try {
			PopulationAgeGroup populationAgeGroup = RestClient.getPopulationAgeGroup("Sweden", 1980, 20);
			assertNotNull("null pointer returned from getPopulationAgeGroup()", populationAgeGroup);
		} catch (JsonSyntaxException | IOException e) {
			fail("Unexpected exception. Internet available?");
		}
	}
}
