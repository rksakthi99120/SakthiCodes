package com.test.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CheckboxPage {
	
	WebDriver driver;
	
	@FindBy(how=How.ID,using = "submit")
	WebElement submitButton;
	
	public CheckboxPage(WebDriver webdriver) {
		this.driver=webdriver;
	}
	
}
