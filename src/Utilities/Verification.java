package Utilities;


import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Verification {

	// To verify the results of available trains matches the same date and city
	public void verifyTrainDetails(String Verifydate, WebDriver driver) {

		//Storing the train details (name,date and city) in a list
		List<WebElement> trainDetails = driver.findElements(By.xpath("//div[@class='col-xs-5 hidden-xs']"));
		
		System.out.println("\n Booking Date : " + Verifydate);
		
		//Verifying the details of the train from the list
		for (WebElement i : trainDetails) {
			String traindetail = i.getText();
			System.out.println("***************" + i.getText());
			//Verifying the city location
			if (traindetail.contains("HYDERABAD") || traindetail.contains("SECUNDERABAD")) {

				//Verifying the date
				if (traindetail.contains(Verifydate)) {
					System.out.println("Date and city of the train are verified");
				} else {
					System.out.println("Date of the train is incorrect");
				}
				
			} else {
				System.out.println("City of the train is incorrect");
			}
		}

	}

}
