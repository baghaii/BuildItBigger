package com.udacity.gradle.builditbigger;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


//https://medium.com/@v.danylo/simple-way-to-test-asynchronous-actions-in-android-service-asynctask-thread-rxjava-etc-d43b0402e005

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

  @Rule
  public ActivityTestRule<MainTestActivity> mActivityRule = new ActivityTestRule<>(MainTestActivity.class);

  @Test
  public void testGoodJokeResponse() throws Exception {

    final Object syncObject = new Object();

    final MainTestActivity mainTestActivity = mActivityRule.getActivity();


    mainTestActivity.setJokeCallback(new MainTestActivity.JokeTestCallback() {


      @Override
      public void onHandleResponseCalled(JokeResponse joke) {
        Assert.assertNotNull(joke);
        if (joke.isOk()) {
          Assert.assertTrue(!joke.getResponseMessage().contentEquals(""));
        } else {
          Assert.assertFalse(joke.isOk());
        }
      }
    });

    onView(withId(R.id.joke_button)).perform(click());

  }

}