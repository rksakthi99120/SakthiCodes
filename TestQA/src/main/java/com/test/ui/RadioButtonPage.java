package com.test.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RadioButtonPage {
WebDriver driver;
	
	@FindBy(how=How.XPATH,using = "//*[@id=\"app\"]//label[contains(text(),'Yes')]")
	WebElement yesRadioButton;
	@FindBy(how=How.XPATH,using = "//*[@id=\"app\"]//label[contains(text(),'Impressive')]")
	WebElement impressiveRadioButton;
	
	@FindBy(how=How.XPATH,using = "//*[@id=\"app\"]//p")
	WebElement labelOutput;
	
	public RadioButtonPage(WebDriver webdriver) {
		this.driver=webdriver;
	}

	public void clickYesRadioButton() {
		yesRadioButton.click();
	}
	public void clickImpressiveRadioButton() {
		impressiveRadioButton.click();
	}
	
	public String getOutputText() {
		return labelOutput.getText();
	}
	
	//*[@id="app"]/div/div/div[2]/div[2]/div[2]/p/text()
	//*[@id="app"]/div/div/div[2]/div[2]/div[2]/p/span
}
