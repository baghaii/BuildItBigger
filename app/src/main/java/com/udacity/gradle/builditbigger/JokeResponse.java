package com.udacity.gradle.builditbigger;

public class JokeResponse {

  private boolean mResponseOk;
  private String mResponseMessage;

  public JokeResponse(boolean responseOk, String responseMessage) {
    mResponseOk = responseOk;
    mResponseMessage = responseMessage;
  }

  public boolean isOk() {
    return mResponseOk;
  }

  public String getResponseMessage() {
    return mResponseMessage;
  }
}
