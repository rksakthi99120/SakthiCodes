package com.test.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage {
	
	WebDriver driver;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"app\"]//h5[contains(text(),'Elements')]")
	WebElement pageElements;
	
	public HomePage(WebDriver webdriver) {
		this.driver=webdriver;
	}
	
	public void clickElementsPage() {
		pageElements.click();
	}

}
