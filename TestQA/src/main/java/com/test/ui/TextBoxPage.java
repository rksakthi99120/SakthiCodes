package com.test.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class TextBoxPage {
	
	WebDriver driver;
	
	@FindBy(how=How.ID,using = "userName")
	WebElement userNameTextBox;
	
	@FindBy(how=How.ID,using = "userEmail")
	WebElement emailTextBox;

	@FindBy(how=How.ID,using = "currentAddress")
	WebElement currentAddressTextBox;
	
	@FindBy(how=How.ID,using = "permanentAddress")
	WebElement permanentAddressTextBox;
	
	@FindBy(how=How.ID,using = "output")
	WebElement labelOutput;
	
	@FindBy(how=How.ID,using = "submit")
	WebElement submitButton;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"item-2\"]//span[contains(text(),'Radio Button')]")
	WebElement pageRadioButton;
	
	
	public TextBoxPage(WebDriver webdriver) {
		this.driver=webdriver;
	}
	
	public void setFullname(String fullName) {
		userNameTextBox.sendKeys(fullName);
	}
	
	public void setEmail(String email) {
		emailTextBox.sendKeys(email);
	}
	
	public void setCurrentAddress(String currentAddress) {
		currentAddressTextBox.sendKeys(currentAddress);
	}
	
	public void setPermanentAddress(String permanentAddress) {
		permanentAddressTextBox.sendKeys(permanentAddress);
	}
	
	public String getOutputText() {
		return labelOutput.getText();
	}
	
	public void clickSubmitButton() {
		submitButton.click();
	}
	
	public void clickRadioButtonPage() {
		pageRadioButton.click();
	}


}
