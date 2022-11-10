package com.qa.report;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {

	CreateReport createHtml = new CreateReport();
	Map<String, String[]> mapResultMessages = new HashMap();
	Map<String, String> mapResult = new HashMap();
	Map<String, String> mapMessages = new HashMap();
	Map<String, String> mapExecutiontime = new HashMap();
	Map<String, String> mapExpectedValue = new HashMap();
	Map<String, String> mapDescription = new HashMap();
	Map<String, String> mapActualValue = new HashMap();
	int passedCount = 0, failedCount = 0, skippedCount = 0;

	public void onFinish(ITestContext context) {
		ReportUtil reportUtil = new ReportUtil();
		System.out.println("==================================");
		System.out.println(context.getCurrentXmlTest().getParameter("env"));
		System.out.println("===================================");

		Set<ITestResult> allResults = new HashSet<ITestResult>(context.getPassedTests().getAllResults());
		allResults.addAll(context.getFailedTests().getAllResults());
		allResults.addAll(context.getSkippedTests().getAllResults());

		for (ITestResult method : allResults) {
			String testName = method.getMethod().getMethodName();
			if (method.getStatus() == 3) {
				System.out.println("I am here");
				mapResult.put(testName, "Skip");
				mapExecutiontime.put(testName, "N/A");
				mapExpectedValue.put(testName, "N/A");
				mapActualValue.put(testName, "N/A");
				mapDescription.put(testName, "N/A");
				mapMessages.put(testName, "N/A");
				skippedCount++;
			}
			mapExecutiontime.put(testName, reportUtil.milliSecondsToTime(method.getStartMillis()));
			List<Assertion.AssertionInfo> assertions = (List<Assertion.AssertionInfo>) method
					.getAttribute(Assertion.ASSERTIONS);
			if (assertions == null || assertions.isEmpty()) {
				continue;
			}
			System.err.println("Printing assertions for " + method.getMethod().getMethodName() + "()");

			// Setting result for each test
			if (method.getStatus() == 1) {
				mapResult.put(testName, "Pass");
				passedCount++;
			} else if (method.getStatus() == 2) {
				mapResult.put(testName, "Fail");
				failedCount++;
			}
			System.out.println("skippedCount:" + skippedCount);
			for (Assertion.AssertionInfo assertion : assertions) {

				/*
				 * String msg =
				 * String.format("Expected: [%s], Actual[%s], Message: [%s], Description: [%s]",
				 * assertion.getExpected().toString(), assertion.getActual(),
				 * assertion.getMessage(), assertion.getDescription());
				 * System.err.println("Assertions ===>" + msg);
				 */
				mapExpectedValue.put(testName, assertion.getExpected()+"");
				mapActualValue.put(testName, assertion.getActual() + "");
				if (assertion.getDescription() != null) {
					mapDescription.put(testName, assertion.getDescription().toString());
				} else {
					mapDescription.put(testName, "N/A");
				}
				if (assertion.getMessage() != null) {
					if (method.getStatus() == 2) {
						mapMessages.put(testName, assertion.getMessage().toString());
					} else {
						mapMessages.put(testName, "N/A");
					}
				} else {
					mapMessages.put(testName, "N/A");
				}
			}
		}
		System.out.println("mapResult:" + mapResult);
		try {
			createHtml.createReport(mapExecutiontime, mapResult, mapExpectedValue, mapActualValue, mapDescription,
					mapMessages, passedCount, failedCount, skippedCount);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	public void onTestStart(ITestResult result) {
	}


	public void onTestSuccess(ITestResult result) {
	}


	public void onTestFailure(ITestResult result) {
	}


	public void onTestSkipped(ITestResult result) {
	}


	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}


	public void onStart(ITestContext context) {
	}

	
}
