package com.test.DB;

import java.sql.SQLException;

import com.qa.db.DBModule;
import com.qa.db.Database;
import com.qa.db.HostInfo;

public class TestDBModule {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		DBModule dbModule=new DBModule();
		HostInfo hostInfo=new HostInfo();
		hostInfo.setDatabase(Database.MySQL.name());
		hostInfo.setHostUrl("jdbc:mysql://localhost:3306/hackathon");
		hostInfo.setUsername("root");
		hostInfo.setPassword("Yamuna!123");
		System.out.println(dbModule.runQuery("select top 5 * from servers", hostInfo));
	}

}

