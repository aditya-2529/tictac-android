package com.environmental.tic;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;

import com.adcolony.sdk.AdColony;
import com.adcolony.sdk.AdColonyAdOptions;
import com.adcolony.sdk.AdColonyAdSize;
import com.adcolony.sdk.AdColonyAdView;
import com.adcolony.sdk.AdColonyAdViewListener;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    public static int counter = 0;

    public void again(View view){
        gameReset(view);
    }

    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if (gameState[tappedImage] == 2) {
            counter++;
            if (counter == 9) {
                gameActive = false;
            }
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn");
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        int flag = 0;
        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2) {
                flag = 1;
                String winnerStr;
                gameActive = false;
                if (gameState[winPosition[0]] == 0) {
                    winnerStr = "X has won";
                    for(int i = 0 ; i < 9 ; i++)
                        if(gameState[i]==2) gameState[i] = 0;
                } else {
                    winnerStr = "O has won";
                    for(int i = 0 ; i < 9 ; i++)
                        if(gameState[i]==2) gameState[i] = 1;
                }
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
            }
        }
        if (flag == 0) {
            TextView status = findViewById(R.id.status);
            status.setText("Match Draw");
        }
    }

    // reset the game
    public void gameReset(View view) {
        gameActive = true;
        activePlayer = 0;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        // remove all the images from the boxes inside the grid
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X's Turn - Tap to play");
    }
    private final String APP_ID = "app56001ca34c0246ae81";
    private final String BANNER_ZONE_ID = "vz83e0b802f93942e59e";
    private AdColonyAdOptions adOptions;
    private AdColonyAdView adView;
    private RelativeLayout adContainer;
    AdColonyAdViewListener listener = new AdColonyAdViewListener(){
        @Override
        public void onRequestFilled(AdColonyAdView ad){
            adContainer = findViewById(R.id.ad_cont);
            adContainer.addView(ad);
            adView = ad;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AdColony.configure(this,APP_ID);
        AdColony.requestAdView(BANNER_ZONE_ID,listener,AdColonyAdSize.BANNER,adOptions);

//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//            }
//        });
//        mAdView = findViewById(R.id.ad_cont);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
//        mAdView.setAdListener(new AdListener() {
//            @Override
//            public void onAdClicked() {
//                // Code to be executed when the user clicks on an ad.
//            }
//
//            @Override
//            public void onAdClosed() {
//                // Code to be executed when the user is about to return
//                // to the app after tapping on an ad.
//            }
//
//            @Override
//            public void onAdFailedToLoad(LoadAdError adError) {
//                // Code to be executed when an ad request fails.
//            }
//
//            @Override
//            public void onAdImpression() {
//                // Code to be executed when an impression is recorded
//                // for an ad.
//            }
//
//            @Override
//            public void onAdLoaded() {
//                // Code to be executed when an ad finishes loading.
//            }
//
//            @Override
//            public void onAdOpened() {
//                // Code to be executed when an ad opens an overlay that
//                // covers the screen.
//            }
//        });
    }

}