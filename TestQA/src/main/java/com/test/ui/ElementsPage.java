package com.test.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ElementsPage {
	
	WebDriver driver;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"item-0\"]//span[contains(text(),'Text Box')]")
	WebElement pageTextBox;
	
	public ElementsPage(WebDriver webdriver) {
		this.driver=webdriver;
	}
	
	public void clickTextBoxPage() {
		pageTextBox.click();
	}

}

