package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.sepidehmiller.jokedisplay.JokeDisplayActivity;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;
import java.net.SocketTimeoutException;


public class MainActivity extends AppCompatActivity {

    ProgressBar mProgressBar;
    TextView mErrorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = findViewById(R.id.progress_bar);
        mErrorView = findViewById(R.id.error_textview);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {

      mErrorView.setVisibility(View.GONE);
      new EndpointAsyncTask().execute();

    }

  // https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/77e9910911d5412e5efede5fa681ec105a0f02ad/HelloEndpoints#2-connecting-your-android-app-to-the-backend
  public class EndpointAsyncTask extends AsyncTask<Void, Void, JokeResponse> {
    private MyApi sMyApiService = null;

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      showProgressBar();
    }

    @Override
    protected JokeResponse doInBackground(Void... params) {
      if(sMyApiService == null) {  // Only do this once
        MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
            new AndroidJsonFactory(), null)
            // options for running against local devappserver
            // - 10.0.2.2 is localhost's IP address in Android emulator
            // - turn off compression when running against local devappserver
            .setRootUrl("http://10.0.2.2:8080/_ah/api/")
            .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
              @Override
              public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                abstractGoogleClientRequest.setDisableGZipContent(true);
              }
            });
        // end options for devappserver

        sMyApiService = builder.build();
      }
      try {
        JokeResponse response = new JokeResponse(true,
            sMyApiService.getJoke().execute().getData());
        return response;
      } catch (SocketTimeoutException se) {
        JokeResponse response = new JokeResponse(false,
            se.getMessage());
        return response;
      } catch (IOException e) {
        JokeResponse response = new JokeResponse(false, e.getMessage());
        return response;
      }
    }

    @Override
    protected void onPostExecute(JokeResponse joke) {
      super.onPostExecute(joke);
      handleJokeResponse(joke);
      Intent intent = new Intent(MainActivity.this, JokeDisplayActivity.class);
      //Set up activity caught by intent filter.
      //https://developer.android.com/guide/components/intents-filters
      if (joke.isOk()) {
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, joke.getResponseMessage());
        startActivity(intent);
        hideProgressBar();
      } else {
        mErrorView.setVisibility(View.VISIBLE);
        mErrorView.setText(joke.getResponseMessage());
      }
    }

  }

  //https://medium.com/@v.danylo/simple-way-to-test-asynchronous-actions-in-android-service-asynctask-thread-rxjava-etc-d43b0402e005

  @VisibleForTesting
  public void handleJokeResponse(JokeResponse joke) {

  }

  public void showProgressBar() {
      if (mProgressBar != null) {
        mProgressBar.setVisibility(View.VISIBLE);
      }
  }

  public void hideProgressBar() {
      if (mProgressBar != null) {
        mProgressBar.setVisibility(View.GONE);
      }
  }

}
