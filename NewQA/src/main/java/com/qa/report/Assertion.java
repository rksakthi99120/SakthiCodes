package com.qa.report;

import java.util.LinkedList;
import java.util.List;

import org.testng.Assert;
import org.testng.Reporter;

public class Assertion {
    public static final String ASSERTIONS = "assertions";

    public static void assertTrue(boolean condition, String message) {
        AssertionInfo info = new AssertionInfo(true, condition, message);
        try {
            Assert.assertTrue(condition, message);
        } finally {
            List<AssertionInfo> assertions =
                (List<AssertionInfo>) Reporter.getCurrentTestResult().getAttribute(ASSERTIONS);
            if (assertions == null) {
                assertions = new LinkedList<AssertionInfo>();
            }
            assertions.add(info);
            Reporter.getCurrentTestResult().setAttribute(ASSERTIONS, assertions);
        }
    }
    public static void assertTrue(boolean condition, String message,String description) {
        AssertionInfo info = new AssertionInfo(true, condition, message,description);
        try {
            Assert.assertTrue(condition, message);
        } finally {
            List<AssertionInfo> assertions =
                (List<AssertionInfo>) Reporter.getCurrentTestResult().getAttribute(ASSERTIONS);
            if (assertions == null) {
                assertions = new LinkedList<AssertionInfo>();
            }
            assertions.add(info);
            Reporter.getCurrentTestResult().setAttribute(ASSERTIONS, assertions);
        }
    }
    
    public static void assertEquals(boolean expected,boolean actual, String message,String description) {
        AssertionInfo info = new AssertionInfo(expected, actual, message,description);
        try {
            Assert.assertEquals(expected,actual, message);
        } finally {
            List<AssertionInfo> assertions =
                (List<AssertionInfo>) Reporter.getCurrentTestResult().getAttribute(ASSERTIONS);
            if (assertions == null) {
                assertions = new LinkedList<AssertionInfo>();
            }
            assertions.add(info);
            Reporter.getCurrentTestResult().setAttribute(ASSERTIONS, assertions);
        }
    }
    
    public static void assertEquals(Object expected,Object actual, String message,String description) {
        AssertionInfo info = new AssertionInfo(expected, actual, message,description);
        try {
            Assert.assertEquals(expected,actual, message);
        } finally {
            List<AssertionInfo> assertions =
                (List<AssertionInfo>) Reporter.getCurrentTestResult().getAttribute(ASSERTIONS);
            if (assertions == null) {
                assertions = new LinkedList<AssertionInfo>();
            }
            assertions.add(info);
            Reporter.getCurrentTestResult().setAttribute(ASSERTIONS, assertions);
        }
    }
    public static void assertEquals(Object expected,Object actual,String message) {
        AssertionInfo info = new AssertionInfo(expected, actual,message);
        try {
            Assert.assertEquals(expected,actual);
        } finally {
            List<AssertionInfo> assertions =
                (List<AssertionInfo>) Reporter.getCurrentTestResult().getAttribute(ASSERTIONS);
            if (assertions == null) {
                assertions = new LinkedList<AssertionInfo>();
            }
            assertions.add(info);
            Reporter.getCurrentTestResult().setAttribute(ASSERTIONS, assertions);
        }
    }
    public static void assertEquals(Object expected,Object actual) {
        AssertionInfo info = new AssertionInfo(expected, actual);
        try {
            Assert.assertEquals(expected,actual);
        } finally {
            List<AssertionInfo> assertions =
                (List<AssertionInfo>) Reporter.getCurrentTestResult().getAttribute(ASSERTIONS);
            if (assertions == null) {
                assertions = new LinkedList<AssertionInfo>();
            }
            assertions.add(info);
            Reporter.getCurrentTestResult().setAttribute(ASSERTIONS, assertions);
        }
    }

    public static class AssertionInfo {
        private Object expected;
        private Object actual;
        private String message;
        private String description;

        public Object getExpected() {
            return expected;
        }

        public Object getActual() {
            return actual;
        }

        public String getMessage() {
            return message;
        }
        
        public String getDescription() {
            return description;
        }
        public AssertionInfo(Object expected, Object actual, String message,String description) {
            this.expected = expected;
            this.actual = actual;
            this.message = message;
            this.description = description;
        }
        public AssertionInfo(Object expected, Object actual, String message) {
            this.expected = expected;
            this.actual = actual;
            this.message = message;
        }
        public AssertionInfo(Object expected, Object actual) {
            this.expected = expected;
            this.actual = actual;
        }
    }

}
