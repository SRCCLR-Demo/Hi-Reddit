package com.mobileproto.hireddit.hireddit.visuals;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.mobileproto.hireddit.hireddit.R;
import com.mobileproto.hireddit.hireddit.speech.WordToSpeech;
import com.mobileproto.hireddit.hireddit.visuals.SpeechFragment.OnFragmentInteractionListener;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener,
        InfoFragment.NumberCommentsToSearchCallback {

    private WordToSpeech speech;
    private int commentsToSearch;

    private static final String DEBUG_TAG = "MainActivity Debug";

    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager = getSupportFragmentManager();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        switchFragment(SpeechFragment.newInstance(this));

        commentsToSearch = 5;

        speech = new WordToSpeech(this);
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

    public void switchFragment(Fragment f) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.container, f);
        transaction.commit();
    }

    // We overload the switchFragment function to allow the user to customize the
    // transition between two fragments on a switch if they want. The two functions
    // have identical behavior outside of the animation.
    public void switchFragment(Fragment f, int customAnimationIn, int customAnimationOut) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(customAnimationIn, customAnimationOut);
        transaction.addToBackStack(null);
        transaction.replace(R.id.container, f);
        transaction.commit();
    }

    @Override
    public void speak(String words) {
        speech.speak(words);
    }

    @Override
    public void stopSpeaking() {
        speech.stop();
    }

    @Override
    public void flipMute() {
        speech.flipMute();
    }

    @Override
    public void onPause() {
        super.onPause();
        speech.stop();
    }

    @Override
    public void onStop() {
        super.onStop();
        speech.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        speech.destroy();
    }

    @Override
    public int getCommentsToSearch() {
        return commentsToSearch;
    }

    @Override
    public void setCommentsToSearch(int c) {
        Log.d(DEBUG_TAG, "Setting comments to search to :" + Integer.toString(c));
        this.commentsToSearch = c;
    }

}
