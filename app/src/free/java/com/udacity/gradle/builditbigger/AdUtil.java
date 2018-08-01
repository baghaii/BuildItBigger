package com.udacity.gradle.builditbigger;

import android.view.View;

import com.google.android.gms.ads.AdView;

public class AdUtil {
  public static AdView getAdView(View rootView) {
    AdView adView = rootView.findViewById(R.id.adView);
    return adView;
  }
}
