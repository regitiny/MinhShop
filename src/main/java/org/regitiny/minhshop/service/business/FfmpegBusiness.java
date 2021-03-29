package org.regitiny.minhshop.service.business;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Service
@Log4j2
public class FfmpegBusiness
{
  @Value("${yuvytung.server.ssh-ffmpeg.host}")
  private String host;
  @Value("${yuvytung.server.ssh-ffmpeg.username}")
  private String username;
  @Value("${yuvytung.server.ssh-ffmpeg.password}")
  private String password;
  @Value("${yuvytung.server.ssh-ffmpeg.port}")
  private int port;

  public String runCommand(String command)
  {
    try
    {
      Session session = new JSch().getSession(username, host, port);
      session.setPassword(password);
      Properties config = new Properties();
      config.put("StrictHostKeyChecking", "no");
      session.setConfig(config);
      session.connect();
      ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
      InputStream resultStream = channelExec.getInputStream();
      channelExec.setCommand(command);
      channelExec.connect();
      String result = new String(resultStream.readAllBytes(), StandardCharsets.UTF_8);
      channelExec.disconnect();
      return result;
    }
    catch (JSchException | IOException e)
    {
      e.printStackTrace();
      return null;
    }
  }

  @Autowired
  private void ffmpegServerStatus()
  {
    log.info(runCommand("echo 'server ffmpeg is running!'"));
  }

}
