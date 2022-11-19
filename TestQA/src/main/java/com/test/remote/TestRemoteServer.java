package com.test.remote;

import java.io.IOException;

import org.testng.annotations.Test;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.qa.db.HostInfo;
import com.qa.remote.RemoteServer;

public class TestRemoteServer {

	@Test
	public void testRemoteConnection() throws JSchException, IOException, SftpException {
		HostInfo hostInfo=new HostInfo();
		hostInfo.setHostUrl("tul1mdq1rrm01");
		hostInfo.setUsername("skaliyaperumal");
		hostInfo.setPassword("Yamuna$456");
		RemoteServer remoteServer=new RemoteServer();
		Session session=remoteServer.connectRemoteServer(hostInfo);
		System.out.println(remoteServer.getFiles(session, "../../mnt/data/ingest20/outfiles/dm_Common_Sch/11/14/"));
		session.disconnect();
	}
}
