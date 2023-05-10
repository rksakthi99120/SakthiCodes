package com.test.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ButtonsPage {
	WebDriver driver;
	
	@FindBy(how=How.XPATH,using = "//*[contains(@class, 'btn btn-primary') and text() = 'Click Me']")
	WebElement clickMeButton;

	
	@FindBy(how=How.ID,using = "dynamicClickMessage")
	WebElement labelOutput;
	
	public ButtonsPage(WebDriver webdriver) {
		this.driver=webdriver;
	}
	
	public void clickClickMeButton() {
		clickMeButton.click();
	}
	
	public String getOutputText() {
		return labelOutput.getText();
	}
	
	//*[@id="Fo3nO"]

}
