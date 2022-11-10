package com.qa.report;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.annotations.Test;

/**
 * @author skaliyaperumal
 *
 */
public class ReportUtil {
	
	public static void WriteToFile(String fileContent, String fileName) throws IOException {
		String projectPath = System.getProperty("user.dir");
		String tempFile = projectPath +File.separator+fileName;
		System.out.println("tempFile:"+tempFile);
		File file = new File(tempFile);
		
		OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());
		Writer writer=new OutputStreamWriter(outputStream);
		writer.write(fileContent);
		writer.close();

	}
	@Test
	public String milliSecondsToTime(long time) {
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");


		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		return formatter.format(calendar.getTime());
	}

}
