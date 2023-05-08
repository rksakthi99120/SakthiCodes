package com.test.report;

import org.testng.annotations.Test;

import com.qa.report.Assertion;

public class TestReport {
	
	@Test(groups={"Report","All"})
	public void testPass() {
		Assertion.assertEquals(true, true);
	}
	
	@Test(groups={"Report","All"})
	public void testFail() {
		Assertion.assertEquals(true, false);
	}
	
	@Test(groups={"Report","All"})
	public void testFailNumber() {
		Assertion.assertEquals(1, 2,"Both are not equal");
	}
	
	@Test(groups={"Report","All"})
	public void testFailString() {
		Assertion.assertEquals("Sakthi", "Yamuna","Both are not equal","Testing string with fail");
	}
	
	@Test(groups={"Report","All"})
	public void testPassString() {
		Assertion.assertEquals("Sakthi", "Sakthi","Both are not equal","Testing string with fail");
	}

}
