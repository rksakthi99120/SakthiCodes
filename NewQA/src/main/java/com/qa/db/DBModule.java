package com.qa.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

public class DBModule {
	
	public JSONObject runQuery(String query,HostInfo hostInfo) throws ClassNotFoundException, SQLException {
		Connection connection=getConnection(hostInfo);
		Statement statement=connection.createStatement();
		ResultSet resultSet=statement.executeQuery(query);
		JSONObject jsonResult = resultInJson(resultSet);
		connection.close();
		return jsonResult;
	}
	
	public int updateQuery(String query,HostInfo hostInfo) throws ClassNotFoundException, SQLException {
		Connection connection=getConnection(hostInfo);
		Statement statement=connection.createStatement();
		return statement.executeUpdate(query);
	}
	
	public Connection getConnection(HostInfo hostInfo) throws ClassNotFoundException, SQLException {
		Class.forName(getDbDriver(hostInfo.getDatabase()));
		Connection con = DriverManager.getConnection(hostInfo.getHostUrl(), 
				hostInfo.getUsername(), hostInfo.getPassword());
		return con;
	}
	
	public String getDbDriver(String dbName) {
		String driver = null;
		switch (dbName) {
		case "MySQL":
			driver = "com.mysql.cj.jdbc.Driver";
			break;
		case "SqlServer":
			driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			break;
		case "SqlServerJtds":
			driver = "net.sourceforge.jtds.jdbc.Driver";
			break;

		case "Oracle":
			driver = "";
			break;
		}
		//System.out.println(driver);
		return driver;
	}
	
	public JSONObject resultInJson(ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnsCount = rsmd.getColumnCount();
		String columnNames[] = new String[columnsCount];
		JSONObject jsonResult = new JSONObject();
		JSONArray jsonResultArray = new JSONArray();
		for (int i = 1; i <= columnsCount; i++) {
			columnNames[i - 1] = rsmd.getColumnName(i);
		}

		while (rs.next()) {
			JSONObject json2 = new JSONObject();
			for (String column : columnNames) {
				if(rs.getString(column)!=null) {
				json2.put(column, rs.getString(column));
				}
				else {
					json2.put(column, "");
				}
			}

			jsonResultArray.put(json2);

		}
		jsonResult.put("Result", jsonResultArray);
		return jsonResult;
	}


}
