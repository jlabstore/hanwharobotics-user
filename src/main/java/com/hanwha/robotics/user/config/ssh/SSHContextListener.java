package com.hanwha.robotics.user.config.ssh;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@WebListener
@Configuration
@PropertySource({"classpath:application-${spring.profiles.active}.properties"})
public class SSHContextListener implements ServletContextListener {

  private SSHConnector sshConnector;

  @Autowired
  Environment environment;
  @Override
  public void contextInitialized(ServletContextEvent sce) {
    try {
      String env = environment.getProperty("intheseom.env");
      if("LOCAL".equals(env)){
        sshConnector = new SSHConnector(environment.getProperty("ssh.url"), environment.getProperty("ssh.username"), environment.getProperty("ssh.password"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    if(sshConnector != null){
      sshConnector.close();
    }
  }
}

