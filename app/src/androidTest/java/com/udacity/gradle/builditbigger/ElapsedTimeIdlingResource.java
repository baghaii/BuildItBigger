package com.udacity.gradle.builditbigger;

import android.support.test.espresso.IdlingResource;

// This looks like Chiu-Ki's example because I am clinging onto her example for dear life.
// https://github.com/chiuki/espresso-samples/blob/master/idling-resource-elapsed-time/app/src/androidTest/java/com/sqisland/espresso/idling_resource/elapsed_time/ElapsedTimeIdlingResource.java


public class ElapsedTimeIdlingResource implements IdlingResource {

  private final long startingTime;
  private final long waitingTime;
  private ResourceCallback resourceCallback;

  public ElapsedTimeIdlingResource(long waitingTime) {
    this.startingTime = System.currentTimeMillis();
    this.waitingTime = waitingTime;

  }

  @Override
  public String getName() {
    return ElapsedTimeIdlingResource.class.getSimpleName();
  }

  @Override
  public boolean isIdleNow() {
    long elapsed = System.currentTimeMillis() - startingTime;
    boolean idle = (elapsed >= waitingTime);
    if (idle) {
      resourceCallback.onTransitionToIdle();
    }
    return idle;
  }

  @Override
  public void registerIdleTransitionCallback(ResourceCallback callback) {
    this.resourceCallback = callback;
  }
}
