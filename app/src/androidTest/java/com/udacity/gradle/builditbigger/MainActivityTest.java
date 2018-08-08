package com.udacity.gradle.builditbigger;

import android.support.test.espresso.IdlingPolicies;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.text.format.DateUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


//https://medium.com/@v.danylo/simple-way-to-test-asynchronous-actions-in-android-service-asynctask-thread-rxjava-etc-d43b0402e005

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

  @Rule
  public ActivityTestRule<MainTestActivity> mActivityRule = new ActivityTestRule<>(MainTestActivity.class);

  @Before
  public void resetTimeout() {
    IdlingPolicies.setMasterPolicyTimeout(60, TimeUnit.SECONDS);
    IdlingPolicies.setIdlingResourceTimeout(26, TimeUnit.SECONDS);
  }

  @Test
  public void testGoodJokeResponse() throws Exception {

    long waitingTime = 30 * DateUtils.SECOND_IN_MILLIS;

    onView(withId(R.id.joke_button)).perform(click());

    //Be sure Espresso doesn't time out.
    IdlingPolicies.setMasterPolicyTimeout(waitingTime * 2, TimeUnit.MILLISECONDS);
    IdlingPolicies.setIdlingResourceTimeout(waitingTime * 2, TimeUnit.MILLISECONDS);

    IdlingResource idlingResource = new ElapsedTimeIdlingResource(waitingTime);
    IdlingRegistry.getInstance().register(idlingResource);

    //TODO - check content of textview if it exists.
    //TODO - check content of error text if it exists

    try {
      onView(withId(R.id.joke_textview)).check(matches(isDisplayed()));
    } catch (NoMatchingViewException e) {
      onView(withId(R.id.error_textview)).check(matches(isDisplayed()));
    }

    IdlingRegistry.getInstance().unregister(idlingResource);


  /*  final MainTestActivity mainTestActivity = mActivityRule.getActivity();


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
 */
  }

}