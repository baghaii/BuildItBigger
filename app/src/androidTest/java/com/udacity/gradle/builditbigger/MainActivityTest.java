package com.udacity.gradle.builditbigger;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


// How do I use a CountingIdlingResource?
// https://medium.com/@wingoku/synchronizing-espresso-with-custom-threads-using-idling-resource-retrofit-70439ad2f07


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

  @Rule
  public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

  @Before
  public void registerResource() {
    IdlingRegistry.getInstance().register(mActivityRule.getActivity().getCountingIdlingResource());
  }

  @Test
  public void testGoodJokeResponse() throws Exception {


    onView(withId(R.id.joke_button)).perform(click());


    CountingIdlingResource countingResource = new CountingIdlingResource("JokeResource");

    try {
      onView(withId(R.id.joke_textview)).check(matches(isDisplayed()));
    } catch (NoMatchingViewException e) {
      onView(withId(R.id.error_textview)).check(matches(isDisplayed()));
    }

  }

  @After
  public void unregisterResource() {
    IdlingRegistry.getInstance().unregister(mActivityRule.getActivity().getCountingIdlingResource());
  }
}