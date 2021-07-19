package Utilities;

import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserSelection {
	static WebDriver driver;
	public WebDriver selectDriver(int choice) 
	{
		/* The automation can be done by two browsers: Chrome & FireFox
		   Select the browser that you want to access
		   For "Chrome" select "1"
		   For "FireFox" select "2" */
		if(choice==1)
		{
			System.setProperty("webdriver.chrome.driver", "Drivers//chromedriver.exe");
			ChromeOptions chromeOptions = new ChromeOptions();
	        chromeOptions.addArguments("--disable-notifications");
	        chromeOptions.setAcceptInsecureCerts(true);
			driver = new ChromeDriver(chromeOptions);
			//OUTPUT PRINT//
	        PrintStream Out;
			try {
				Out = new PrintStream("Output//Result.txt");
				System.setOut(Out);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       
	        //------------//
			System.out.println("Chrome Browser is selected");
		}
		else if(choice==2)
		{
			System.setProperty("webdriver.gecko.driver","Drivers//geckodriver.exe");
			FirefoxOptions ffOptions = new FirefoxOptions();
			ffOptions.addPreference("dom.webnotifications.enabled", false);
			driver = new FirefoxDriver(ffOptions);	
			System.out.println("Fire Fox browser is selected");
		}
		else
		{
			System.out.println("Entered choice for selecting the browser is invalid");
		}
		return driver;
	}

}

