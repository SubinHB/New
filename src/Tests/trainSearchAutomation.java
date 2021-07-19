package Tests;

import Pages.irctcBookingPage;
import Utilities.LoadProperties;

public class trainSearchAutomation extends irctcBookingPage {
	
	static String Details="Resources//Details.properties"; //Location of properties file is stored in this string ie., 'Details' is used 
	static int count = 1; //File number for screenshots
	
	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		irctcBookingPage trains = new irctcBookingPage();
		
		irctcBookingPage.getWebDriver();
		trains.maximizeWindow();
		trains.loadUrl(LoadProperties.loadProperties(Details, "Url"));
		trains.waits();
		trains.verifyPageTitle();
		trains.acceptAlertMessage();
		trains.travellingFrom(LoadProperties.loadProperties(Details, "From"));
		trains.travellingTo(LoadProperties.loadProperties(Details, "To"));
		trains.travellingDate(Integer.parseInt(LoadProperties.loadProperties(Details, "DaysAfterBooking")));
		trains.travelClass();
		trains.checkBox();
		trains.searchTrain();
		trains.searchResults();
		trains.verifyResuls();
		trains.takeScreenshot(count++);
		trains.closeDriver();
	}

}

