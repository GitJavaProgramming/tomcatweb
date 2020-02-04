package org.pp.tomcat.ch03;

import org.pp.tomcat.ch03.connector.http.HttpRequest;
import org.pp.tomcat.ch03.connector.http.HttpResponse;

import java.io.IOException;

public class StaticResourceProcessor {

  public void process(HttpRequest request, HttpResponse response) {
    try {
      response.sendStaticResource();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

}
