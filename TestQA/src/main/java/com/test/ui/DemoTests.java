package com.test.ui;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.report.Assertion;

import dev.failsafe.internal.util.Assert;

public class DemoTests {

	WebDriver driver=null;
	TextBoxPage textBoxPage;
	RadioButtonPage radioButtonPage;
	ButtonsPage buttonsPage;
	
	@BeforeTest(groups = "UI")
	@Parameters({ "browser" })
	public void setup(String browser) {
		//String browser = System.getProperty("browser");
		if(browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Test(priority=0,groups = "UI")
	public void testLoginHomepage() throws InterruptedException {
		driver.get("https://demoqa.com/");
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.clickElementsPage();

	}

	@Test(priority=1,groups = "UI")
	public void testVerifyTextBoxPage() throws InterruptedException {
		ElementsPage elementsPage = PageFactory.initElements(driver, ElementsPage.class);
		elementsPage.clickTextBoxPage();
		textBoxPage = PageFactory.initElements(driver, TextBoxPage.class);
		// Enter values in text boxes
		textBoxPage.setFullname("Test Fullname");
		textBoxPage.setEmail("test@test.com");
		textBoxPage.setCurrentAddress("Current Address1");
		textBoxPage.setPermanentAddress("Permanent Address1");
		textBoxPage.clickSubmitButton();
		// Validate entered texts in output
		Assertion.assertEquals(
				"Name:Test Fullname\n" + "Email:test@test.com\n" + "Current Address :Current Address1\n"
						+ "Permananet Address :Permanent Address1",
				textBoxPage.getOutputText(), "Output texts are not equal to given texts", "Test to verify Text Box page");

	}

	@Test(priority=2,groups = "UI")
	public void testVerifyRadioButtonPageYes() throws InterruptedException {
		textBoxPage.clickRadioButtonPage();
		radioButtonPage = PageFactory.initElements(driver, RadioButtonPage.class);
		//Click Yes radio button
		radioButtonPage.clickYesRadioButton();

		Assertion.assertEquals("You have selected Yes", radioButtonPage.getOutputText(), "Yes radio button is not selected",
				"Test to verify Radio Button page Yes button");
	}
	
	@Test(priority=3,groups = "UI")
	public void testVerifyRadioButtonPageImpressive() throws InterruptedException {
		//Click Impressive radio button
		radioButtonPage.clickImpressiveRadioButton();
		Assertion.assertEquals("You have selected Impressive", radioButtonPage.getOutputText(), 
				"Impressive radio button is not selected",
				"Test to verify Radio Button page Impressive button");
	}
	
	@Test(priority=4,groups = "UI")
	public void testVerifyButtonPageClickMe() throws InterruptedException {
		radioButtonPage.clickButtonsPage();
		buttonsPage=PageFactory.initElements(driver, ButtonsPage.class);
		//Click Yes radio button
		buttonsPage.clickClickMeButton();

		Assertion.assertEquals("You have done a dynamic click", buttonsPage.getOutputText(), "Click me button is not selected",
				"Test to verify Button page Click me button");
	}

	@AfterTest(groups = "UI")
	public void teardown() {
		driver.close();
		driver.quit();
	}

}
