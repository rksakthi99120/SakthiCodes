package com.qa.report;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.Test;

public class CreateReport {
	@Test
	public void createReport(Map<String, String> mapExecutionTime, Map<String, String> mapResult,
			Map<String, String> mapExpectedValue,Map<String, String> mapActualValue,Map<String, String> mapDescription,
			Map<String, String> mapMessages,int passCount, int failCount, int skipCount) throws IOException {
		String reportContent = createReportContent(mapExpectedValue,mapActualValue,mapDescription,mapMessages, mapResult, 
				mapExecutionTime, passCount, failCount,skipCount);
		ReportUtil reportUtil = new ReportUtil();
		reportUtil.WriteToFile(reportContent, "result.html");
	}

	public String createReportContent(Map<String, String> mapExpectedValue,Map<String, String> mapActualValue,Map<String, String> mapDescription,
			Map<String, String> mapMessages,Map<String, String> mapResult,
			Map<String, String> mapExecutionTime, int passCount, int failCount, int skipCount) {
		int index = 0;
		String[] Tests = new String[mapResult.size()];
		for (String key : mapResult.keySet()) {
			Tests[index] = key;
			index++;
		}
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("<html lang=\"en\">");
		strBuilder.append("<head>");
		strBuilder.append("<style>");
		strBuilder.append(".chart_box {");
		strBuilder.append("width: 700;");
		strBuilder.append("margin: 0 auto;");
		strBuilder.append("}");
		strBuilder.append("table td {border:solid 1px #fab; width:100px; word-wrap:break-word;}");
		strBuilder.append("h1, h3 {");
		strBuilder.append("text-align: center;");
		strBuilder.append("}");
		strBuilder.append("table {");
		strBuilder.append("border-spacing: 0px;");
		strBuilder.append("table-layout: fixed;");
		strBuilder.append("margin-left:auto;");
		strBuilder.append("margin-right:auto;");
		strBuilder.append("}");
		strBuilder.append("th {");
		strBuilder.append("color: green;");
		strBuilder.append("border: 1px solid black;");
		strBuilder.append("}");
		strBuilder.append("td {");
		strBuilder.append("border: 1px solid black;");
		strBuilder.append("}");
		strBuilder.append("</style>");
		strBuilder.append("<title>Automation Report</title>");
		strBuilder.append("<meta charset=\"utf-8\">");
		strBuilder.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		strBuilder.append(
				"<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">");
		strBuilder.append("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>");
		strBuilder.append(
				"<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>");

		strBuilder.append("<link rel=\"stylesheet\" href=\"https://cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css\" />");
	    
		strBuilder.append("<script type=\"text/javascript\" src=\"https://code.jquery.com/jquery-3.5.1.js\"></script>");
	    
		strBuilder.append("<script type=\"text/javascript\" src=\"https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js\"></script>");
		strBuilder.append("<script type=\"text/javascript\"");
		strBuilder.append("src=\"https://www.gstatic.com/charts/loader.js\"></script>");
		strBuilder.append("<script type=\"text/javascript\">");
			   
		strBuilder.append("google.charts.load('current', {'packages':['corechart']});");
		strBuilder.append("google.charts.setOnLoadCallback(drawChart);");

		strBuilder.append("function drawChart() {");

		strBuilder.append("var data = google.visualization.arrayToDataTable([");
		strBuilder.append("['Status', 'Percentage'],");
		strBuilder.append("['Passed',     "+passCount+"],");
		strBuilder.append("['Failed',     "+failCount+"],");
		strBuilder.append("['Skipped',     "+skipCount+"]");
		strBuilder.append("]);");

		strBuilder.append("var options = {");
		strBuilder.append("title:'Last Run','width':600, 'height':275");
			         
		strBuilder.append(" };");

		strBuilder.append("var chart = new google.visualization.PieChart(document.getElementById('piechart1'));");

		strBuilder.append("chart.draw(data, options);");
		strBuilder.append("}");
		strBuilder.append("</script>");
		strBuilder.append("</head>");
		strBuilder.append("<body><br><br>");
		// strBuilder.append("<button type=\"button\" class=\"btn btn-info\"
		// data-toggle=\"collapse\" data-target=\"#demo\">Simple
		// collapsible</button></p>");
		strBuilder.append("<div class=\"container\">");

		strBuilder.append("<div class=\"panel panel-primary\"  align=\"center\">");
		strBuilder.append("<div class=\"panel-heading\"><h2>Test Report Summary</h2></div>");
		strBuilder.append("<table class=\"table table-bordered\">");

		strBuilder.append("<thead>");
		strBuilder.append("<tr>");

		strBuilder.append("<th>Total Tests</th>");
		strBuilder.append("<th>Passed</th>");
		strBuilder.append("<th>Failed</th>");
		strBuilder.append("<th>Skipped</th>");
		strBuilder.append("<th>Passed Percentage</th>");
		strBuilder.append("</tr>");
		strBuilder.append("</thead>");
		strBuilder.append("<tbody>");
		strBuilder.append("<tr>");
		strBuilder.append("<td>" + (passCount + failCount + skipCount) + "</td>");
		strBuilder.append("<td>" + passCount + "</td>");
		strBuilder.append("<td>" + failCount + "</td>");
		strBuilder.append("<td>" + skipCount + "</td>");
		float total = passCount + failCount + skipCount;
		float passed = passCount;
		float percentage = (passed / total) * 100;
		strBuilder.append("<td>" + percentage + "</td>");
		strBuilder.append("</tr>");
		strBuilder.append("</tbody>");
		strBuilder.append("</table>");
		strBuilder.append("</div>");
		strBuilder.append("<br><br>");
	//Pie Chart starts
		
	//	strBuilder.append("<div class=\"row\">");
	//	strBuilder.append("<div class=\"col-xs-5 col-xs-offset-4_5\">");
		strBuilder.append("<div class=\"chart_box\">");
		strBuilder.append("<div id=\"piechart1\" style=\"border: 5px solid #0067A5\"></div>");
		strBuilder.append("</div>");
		//strBuilder.append("<div id=\"piechart1\" ></div>");
	//	strBuilder.append("</div>");
	//	strBuilder.append("</div>");
		strBuilder.append("<br><br>");
		

		
			      
		//Pie Chart Ends		      

		strBuilder.append("<div class=\"panel panel-primary\" style=\"width:100%\" align=\"center\">");
		strBuilder.append("<div class=\"panel-heading\"><h2>Test Report Detail</h2></div>");
		strBuilder.append("<table style=\"width:100%\" id=\"result\" class=\"table table-bordered\">");
		// Table head start
		strBuilder.append("<thead>");
		strBuilder.append("<tr>");
		strBuilder.append("</div>");
		strBuilder.append("<th>TestName</th>");
		strBuilder.append("<th>Description</th>");
		strBuilder.append("<th>Status</th>");
		strBuilder.append("<th>Expected result</th>");
		strBuilder.append("<th>Actual result</th>");
		strBuilder.append("<th>Execution message</th>");
		strBuilder.append("<th>Execution time</th>");
		strBuilder.append("</tr>");
		strBuilder.append("</thead>");
		strBuilder.append("<tbody>");
		// Table head End
		String statusclass = null;
		String status = null;
		for (int i = 0; i < Tests.length; i++) {
			// Row start
			
			System.out.println("Testing:"+mapResult.get(Tests[i]));
			if (mapResult.get(Tests[i]).toString().contains("Fail")) {
				statusclass = "danger";
				status = "Fail";
				strBuilder.append(
						"<tr data-toggle=\"collapse\" data-target=\"#demo" + i + "\" class=\"" + statusclass + "\">");
				strBuilder.append("<td style=\"width:70%\">" + Tests[i] + "</td>");
				strBuilder.append("<td>" + mapDescription.get(Tests[i]).toString() + "</td>");
				strBuilder.append("<td>" + status + "</td>");
				String messages[]=mapResult.get(Tests[i]).toString().split("\\$\\@\\$");
				System.out.println("message:"+messages[0]);
				
				strBuilder.append("<td>" + mapExpectedValue.get(Tests[i]).toString()+ "</td>");   //Expected
				strBuilder.append("<td>" + mapActualValue.get(Tests[i]).toString() + "</td>");   //Actual
				strBuilder.append("<td>" + mapMessages.get(Tests[i]).toString() + "</td>");  
				strBuilder.append("<td>" + mapExecutionTime.get(Tests[i]) + "</td>");
			} 
			else if (mapResult.get(Tests[i]).toString().contains("Skip")) {
				statusclass = "warning";
				status = "Skipped";
				strBuilder.append(
						"<tr data-toggle=\"collapse\" data-target=\"#demo" + i + "\" class=\"" + statusclass + "\">");
				strBuilder.append("<td>" + Tests[i] + "</td>");
				strBuilder.append("<td>" + mapDescription.get(Tests[i]).toString() + "</td>");
				strBuilder.append("<td>" + status + "</td>");
				String messages[]=mapResult.get(Tests[i]).toString().split("\\$\\@\\$");
				System.out.println("message:"+messages[0]);
				
				strBuilder.append("<td>" + mapExpectedValue.get(Tests[i]).toString()+ "</td>");   //Expected
				strBuilder.append("<td>" + mapActualValue.get(Tests[i]).toString() + "</td>");   //Actual
				strBuilder.append("<td>" + mapMessages.get(Tests[i]).toString() + "</td>");  
				strBuilder.append("<td>" + mapExecutionTime.get(Tests[i]) + "</td>");
			}
			else {
				statusclass = "success";
				status = "Pass";
				strBuilder.append(
						"<tr data-toggle=\"collapse\" data-target=\"#demo" + i + "\" class=\"" + statusclass + "\">");
				strBuilder.append("<td>" + Tests[i] + "</td>");
				strBuilder.append("<td>" + mapDescription.get(Tests[i]).toString() + "</td>");
				strBuilder.append("<td>" + status + "</td>");
				strBuilder.append("<td>" + mapExpectedValue.get(Tests[i])+ "</td>");   //Expected
				strBuilder.append("<td>" + mapActualValue.get(Tests[i]).toString() + "</td>");   //Actual
				strBuilder.append("<td>" + mapMessages.get(Tests[i]).toString() + "</td>");  
				strBuilder.append("<td>" + mapExecutionTime.get(Tests[i]) + "</td>");
			}

			
			
			strBuilder.append("</tr>");

		
			}

		
		strBuilder.append("</tbody>");
		strBuilder.append("</table>");
		strBuilder.append("</div>");
		strBuilder.append("<script>");
		strBuilder.append("$(document).ready(function() {");
		strBuilder.append("$('#result').DataTable({ });");
		strBuilder.append("});");
		strBuilder.append("</script>");
		strBuilder.append("</body>");
		strBuilder.append("</html>");
		return strBuilder.toString();
	}
	
	
}