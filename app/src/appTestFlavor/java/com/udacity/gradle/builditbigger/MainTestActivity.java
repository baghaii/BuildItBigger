package com.udacity.gradle.builditbigger;


// https://medium.com/@v.danylo/simple-way-to-test-asynchronous-actions-in-android-service-asynctask-thread-rxjava-etc-d43b0402e005
public class MainTestActivity extends MainActivity {

  private JokeTestCallback mCallback;

  public void setJokeCallback(JokeTestCallback jokeTestCallback) {
    mCallback = jokeTestCallback;
  }

  public interface JokeTestCallback{
    void onHandleResponseCalled(JokeResponse joke);
  }

  @Override
  public void handleJokeResponse(JokeResponse joke) {
    mCallback.onHandleResponseCalled(joke);
  }
}
