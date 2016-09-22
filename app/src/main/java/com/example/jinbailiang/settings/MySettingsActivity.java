package com.example.jinbailiang.settings;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.jinbailiang.demos_jinbai.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Arrays;

import static android.support.v4.app.Fragment.instantiate;

public class MySettingsActivity extends Activity {

    private ScrollView scrollView;
    private LinearLayout mLinearLayout;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_settings);
//        Fragment f = Fragment.instantiate(this,fName,bundle);
//        ActivityManager am = getSystemService(Action.)
        try {
            PackageManager pa = getPackageManager();
            PackageInfo a = getPackageManager().getPackageInfo("com.example.jinbailiang.demos_jinbai", PackageManager.GET_ACTIVITIES);
            String strA = Arrays.toString(a.activities);
            ApplicationInfo b = a.applicationInfo;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

   /*     int size_or_name= 0;
        if(size_or_name == 0){
            //执行字母  nnnnn();
            size_or_name = 1;
        }else{
            //执行size  ssss();
            size_or_name = 0;
        }*/

        initViews();


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initViews() {
        mLinearLayout = (LinearLayout) findViewById(R.id.mLinearLayout);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MySettings Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.jinbailiang.settings/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MySettings Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.jinbailiang.settings/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
