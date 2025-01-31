package com.hanwha.robotics.user.config.ssh;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SSHConnector {
  private static JSch jsch = new JSch();

  private Session session;


  public SSHConnector(String url, String username, String password) {
    try {
      session =  jsch.getSession(username,url, 22);
      session.setPassword(password);
      java.util.Properties config = new java.util.Properties();
      config.put("StrictHostKeyChecking", "no");
      session.setConfig(config);
      session.connect();
      session.setPortForwardingL(3301, "127.0.0.1", 3306);
      System.out.println("연결성공");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public void close() {
    if (session != null && session.isConnected()) {
      session.disconnect();
      System.out.println("연결종료");
    }
  }
}
