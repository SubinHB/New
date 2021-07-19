package Pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.common.io.Files;

import Utilities.BrowserSelection;
import Utilities.Verification;

public class irctcBookingPage extends BrowserSelection {

	public static WebDriver driver;
	public String travelDate;
	public String resultDate;

	// To launch the required browser
	public static WebDriver getWebDriver() {

		System.out.println("Choose the browser to perform the automation.\nEnter '1' for Chrome \nEnter '2' for FireFox");
		Scanner input = new Scanner(System.in);
		int choice = input.nextInt();
		input.close();
		BrowserSelection browser = new BrowserSelection();
		driver = browser.selectDriver(choice);
		return driver;
	}

	// To maximize the browser window
	public void maximizeWindow() {
		driver.manage().window().maximize();
	}

	// To open the website
	public void loadUrl(String Url) {
		driver.get(Url);
	}

	// Allowing the driver to wait until all the locators are loaded
	public void waits() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	// Verify whether the website is opened correctly
	public void verifyPageTitle() {
		String title = "IRCTC Next Generation eTicketing System";
		String pageTitle = driver.getTitle();
		if (pageTitle.equalsIgnoreCase(title)) {
			System.out.println("IRCTC website is launched correctly");
		} else {
			System.out.println("Launched website is incorrect");
		}
		return;
	}

	// Check whether the Alert message box is present or not. If present accept the alert message.
	public void acceptAlertMessage() {
		WebElement alertMsg = driver.findElement(By.xpath("//app-header/p-dialog[2]/div[1]/div[1]/div[1]"));
		if (alertMsg.isDisplayed()) {
			driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
		}
	}

	// Locate the 'From' element and select 'Hyderabad Decan - HYB' station
	public void travellingFrom(String origin) throws InterruptedException {
		WebElement from = driver.findElement(By.xpath("//*[@id='origin']/span/input"));
		from.sendKeys(origin);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(),'HYDERABAD DECAN - HYB')]")).click();

	}

	// Locate the 'To' element and select 'Pune Jn' station
	public void travellingTo(String destination) throws InterruptedException {
		WebElement to = driver.findElement(By.xpath("//*[@id='destination']/span/input"));
		to.sendKeys(destination);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(),'PUNE JN - PUNE')]")).click();
	}

	// Locate calender element and select a date 4 days after current date
	public void travellingDate(int afterNumOfDays) throws InterruptedException, AWTException {
		Date currentDate = new Date();  //To get the current date for the system
		String day = new SimpleDateFormat("dd").format(currentDate);   //Extract day from the currentDate in 'dd' format
		String month = new SimpleDateFormat("MM").format(currentDate); //Extract month from the currentDate in 'MM' format
		String year = new SimpleDateFormat("yyyy").format(currentDate); //Extract year from the currentDate in 'yyyy' format

		//To find either the month if 'february' or the current month contains 30 or 31 days
		String monthsWith31Days = "1,3,5,7,8,10,12";  
		String monthsWith30Days = "4,6,9,11";
		String february = "2";

		//To convert the day and month to integer format
		int Day = Integer.parseInt(day);
		int Month = Integer.parseInt(month);

		//To find the day and month of travel
		int travelDay = Day + afterNumOfDays;
		if (monthsWith31Days.contains(month)) {
			if (travelDay > 31) {
				travelDay = travelDay % 31;
				Month = Month + 1;
			}
		} else if (monthsWith30Days.contains(month)) {
			if (travelDay > 30) {
				travelDay = travelDay % 30;
				Month = Month + 1;
			}
		} else if (february.contains(month)) {
			if (travelDay > 28) {
				travelDay = travelDay % 28;
				Month = Month + 1;
			}
		}
		
        //Store the new date to travelDate 
		if (Month <= 9) {
			travelDate = Integer.toString(travelDay) + "/0" + Integer.toString(Month) + "/" + year;

		} else {
			travelDate = Integer.toString(travelDay) + "/" + Integer.toString(Month) + "/" + year;
		}

		//locating the calender element and send the 'travelDate'
		WebElement calender = driver.findElement(By.xpath("//*[@id='jDate']/span/input"));
		calender.click();
		Robot rob = new Robot();
		rob.keyPress(KeyEvent.VK_CONTROL);
		rob.keyPress(KeyEvent.VK_A);
		rob.keyRelease(KeyEvent.VK_CONTROL);
		rob.keyRelease(KeyEvent.VK_A);
		calender.sendKeys(travelDate);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='divMain']/descendant::span[7]")).click();
	}

	// Select 'Sleeper' class from 'All Classes
	public void travelClass() throws InterruptedException {
		WebElement classes = driver.findElement(By.xpath("//span[@class='ng-tns-c66-11 ui-dropdown-label ui-inputtext ui-corner-all ng-star-inserted']"));
		classes.click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[contains(text(),'Sleeper (SL)')]")).click();
	}

	// Select passenger type as 'General' (if necessary)
	public void passengerType() throws InterruptedException {
		WebElement type = driver.findElement(By.xpath("//div[@class='ng-tns-c66-12 ui-dropdown ui-widget ui-state-default ui-corner-all']"));
		type.click();
		Thread.sleep(1000);
		WebElement general = driver.findElement(By.xpath("//*[@id='journeyQuota']/div/div[1]/div/ul/p-dropdownitem[4]/li/span"));
		general.click();
	}

	// Select 'Divyaang concession' check box and accept the confrimation message
	public void checkBox() throws InterruptedException {
		WebElement divyaangConcession = driver.findElement(By.xpath("//label[contains(text(),'Divyaang Concession')]"));
		divyaangConcession.click();
		WebElement confirm = driver.findElement(By.xpath("//span[contains(text(),'OK')]"));
		confirm.click();
		Thread.sleep(1000);
	}

	// Click the 'Search' button
	public void searchTrain() {
		WebElement search = driver.findElement(By.xpath("//button[contains(text(),'Search')]"));
		search.click();
	}

	// Get the search results
	public void searchResults() {

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //Allow the driver to wait until the results are displayed
		
		//Store the available trains shown in results as a list of webelements
		List<WebElement> trainsAvailable = driver.findElements(By.xpath("//div[@class='ng-star-inserted']/div/div/strong"));
		
		//Print the total number of trains available on the travelling date
		System.out.println("\n Number of trains available on " + travelDate + ": " + trainsAvailable.size());
		
		//Print the name of the available trains
		for (WebElement trains : trainsAvailable) {
			System.out.println(trains.getText());
		}

	}

	//Check the results obtained from the search and verify it
	public void verifyResuls() throws ParseException {

		SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
		Date dateMonth = sf.parse(travelDate); //Convert the travelDate from String to date format

		//Extracting date and month from dateMonth and convert them to string
		String TxtDay = new SimpleDateFormat("dd").format(dateMonth);
		String TxtMonth = new SimpleDateFormat("MMM").format(dateMonth);

		//Store the day and month in a new string 'resultDate' to verify the date in search results
		resultDate = TxtDay + " " + TxtMonth;
		Verification verify = new Verification(); //Calling the Verification class in the utilities package
		
		//Verifying the trainDetails by calling verifyTrainDetails method
		verify.verifyTrainDetails(resultDate, driver);

	}

	// Taking Screenshot of the results page
	public void takeScreenshot(int num) throws InterruptedException {

		//Tycasting
		TakesScreenshot sc = (TakesScreenshot) driver;
		File source = sc.getScreenshotAs(OutputType.FILE); //Get the screenshot as a source file 

		try {
			File target = new File("D:\\Eclpse Java\\MiniProject\\Screenshots\\screenshot" + num + ".png"); //locating the target folder
			Files.copy(source, target); //Copy the source file to paste in the target location
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	// Closing the webdriver
	public void closeDriver() {
		driver.close();
	}

}
