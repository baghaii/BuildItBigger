package com.sepidehmiller.jokedisplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {
  public static final String JOKE_MESSAGE = "JokeMessage";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_joke_display);

    TextView tv = findViewById(R.id.joke_textview);

    Intent intent = getIntent();
    String action = intent.getAction();
    String type = intent.getType();

    if (Intent.ACTION_SEND.equals(action) && type != null) {
      if ("text/plain".equals(type)) {
        String joke = intent.getStringExtra(JOKE_MESSAGE);
        tv.setText(joke);
      }
    }
  }
}
