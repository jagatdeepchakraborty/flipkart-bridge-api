package com.flipkartbridge.flipkartbridgeapi.proxy;

import com.flipkartbridge.flipkartbridgeapi.config.FlipkartProxyProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Configuration
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AccessTokenGenerator {
  private static final Pattern pat = Pattern.compile(".*\"access_token\"\\s*:\\s*\"([^\"]+)\".*");
  private final FlipkartProxyProperties flipkartProxyProperties;

  public String getClientCredentialsAccessToken() {
    String auth = flipkartProxyProperties.getClientId() + ":" +
            flipkartProxyProperties.getClientSecret();
    String authentication = Base64.getEncoder().encodeToString(auth.getBytes());
    BufferedReader reader = null;
    HttpsURLConnection connection = null;
    String returnValue = "";
    String urlWithScope = flipkartProxyProperties.getHost()
            + flipkartProxyProperties.getTokenPath();
    try {
      URL url = new URL(urlWithScope);
      connection = (HttpsURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      connection.setDoOutput(true);
      connection.setRequestProperty("Authorization", "Basic " + authentication);
      connection.setRequestProperty("Accept", "application/json");
      reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String line = null;
      StringWriter out = new StringWriter(connection.getContentLength() > 0 ? connection.getContentLength() : 2048);
      while ((line = reader.readLine()) != null) {
        out.append(line);
      }
      String response = out.toString();
      Matcher matcher = pat.matcher(response);
      if (matcher.matches() && matcher.groupCount() > 0) {
        returnValue = matcher.group(1);
      }
    } catch (Exception e) {
      System.out.println("Error : " + e.getMessage());
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
        }
      }
      connection.disconnect();
    }
    return returnValue;
  }
}
