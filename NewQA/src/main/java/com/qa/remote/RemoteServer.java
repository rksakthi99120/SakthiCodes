package com.qa.remote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.qa.db.HostInfo;

public class RemoteServer {
	Session session = null;
	ChannelSftp sftpChannel = null;
	Channel channel = null;

	public Session connectServer(HostInfo hostInfo) {
		JSch jsch = new JSch();
		try {
			session = jsch.getSession(hostInfo.getUsername(), hostInfo.getHostUrl(), 22);
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(hostInfo.getPassword());
			session.connect();
			System.out.println("Server connected : " + session.isConnected());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean isFolderExist(Session session, String inputFolder) throws JSchException, SftpException {
		String files = null;
		boolean status = false;
		channel = session.openChannel("sftp");
		channel.connect();
		ChannelSftp channelSftp = (ChannelSftp) channel;
		// channelSftp.cd("../../");
		try {
			files = channelSftp.ls(inputFolder).toString();
		} catch (Exception e) {
			System.out.println(inputFolder + " not found");
		}
		if (files != null) {
			status = true;
		}
		channel.disconnect();
		return status;
	}

	public List<String> getFiles(Session session, String inputFolder) throws JSchException, SftpException {
		channel = session.openChannel("sftp");
		channel.connect();
		ChannelSftp channelSftp = (ChannelSftp) channel;
		channelSftp.cd("../../" + inputFolder);
		List<String> listFiles = new ArrayList<String>();
		Vector<ChannelSftp.LsEntry> list = channelSftp.ls("*.*");
		for (ChannelSftp.LsEntry entry : list) {
			listFiles.add(entry.getFilename());
		}
		channel.disconnect();
		// session.disconnect();
		return listFiles;
	}

	public String executeCommand(Session session, String command) {
		String result = "";
		StringBuilder sb = new StringBuilder();
		try {
			System.out.println(command);
			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(command);

			channel.setInputStream(null);

			((ChannelExec) channel).setErrStream(System.err);

			InputStream in = channel.getInputStream();

			channel.connect();

			byte[] tmp = new byte[1024];
			while (true) {
				while (in.available() > 0) {
					int i = in.read(tmp, 0, 1024);
					if (i < 0)
						break;
					// System.out.print(new String(tmp, 0, i));
					sb.append(new String(tmp, 0, i));
				}
				if (channel.isClosed()) {
					if (in.available() > 0)
						continue;
					System.out.println("exit-status: " + channel.getExitStatus());

					break;
				}
				try {
					Thread.sleep(1000);
				} catch (Exception ee) {
				}
			}
			channel.disconnect();
		}

		catch (Exception e) {
			System.out.println(e);
		}
		return sb.toString();
	}

	public String getCurrentDate(Session session) throws JSchException, IOException {
		ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
		InputStream in = channelExec.getInputStream();
		channelExec.setCommand("date"); // Date format could be changed to your desired format
		channelExec.connect();

		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line, date = "";
		int index = 0;
		while ((line = reader.readLine()) != null) {
			date = line;
			System.out.println(++index + " : " + line);
		}

		channelExec.disconnect();
		return date;
	}

	public String readReomoteServerFileContent(Session session, String inputFolder) throws JSchException, SftpException {
		Channel channel = session.openChannel("sftp");
		String line, output = "";
		channel.connect();
		ChannelSftp sftpChannel = (ChannelSftp) channel;
		InputStream stream = sftpChannel.get(inputFolder);
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(stream));

			while ((line = br.readLine()) != null) {
				output = output + line;
			}

		} catch (IOException io) {
			System.out.println("Exception occurred during reading file from SFTP server due to " + io.getMessage());
			io.getMessage();

		} catch (Exception e) {
			System.out.println("Exception occurred during reading file from SFTP server due to " + e.getMessage());
			e.getMessage();

		}
		sftpChannel.exit();
		return output;
	}

}
