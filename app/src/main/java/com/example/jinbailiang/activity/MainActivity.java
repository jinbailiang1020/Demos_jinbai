package com.example.jinbailiang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.jinbailiang.adapter.MyGridAdapter;
import com.example.jinbailiang.demos_jinbai.R;
import com.example.jinbailiang.expandablelist.ExpandableListTestActivity;
import com.example.jinbailiang.otherinfomation.OtherInfoActivity;
import com.example.jinbailiang.photocropupload.PhotoCropUploadActivity;
import com.example.jinbailiang.richeditor.RichEditorActicity;
import com.example.jinbailiang.settings.MySettingsActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private GridView gv_Main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setAdapter();
        setListeners();

        Fresco.initialize(this);
    }

    private void setListeners() {
        gv_Main.setOnItemClickListener(this);
    }

    private void setAdapter() {
         ArrayList<String> mNameList = new ArrayList<String>();
        mNameList.add("拍照剪切上传");
        mNameList.add("富文本");
        mNameList.add("二维数组");
        mNameList.add("浏览器");
        mNameList.add("pakageInfo");
        mNameList.add("demo06");
        mNameList.add("demo07");
        mNameList.add("demo08");
        mNameList.add("demo09");
        mNameList.add("demo10");
         ArrayList<Integer> mDrawableList = new ArrayList<Integer>();
            mDrawableList.add(R.mipmap.ic_launcher);
        mDrawableList.add(R.mipmap.ic_launcher);
        mDrawableList.add(R.mipmap.ic_launcher);
        mDrawableList.add(R.mipmap.ic_launcher);
        mDrawableList.add(R.mipmap.ic_launcher);
        mDrawableList.add(R.mipmap.ic_launcher);
        mDrawableList.add(R.mipmap.ic_launcher);
        mDrawableList.add(R.mipmap.ic_launcher);
        mDrawableList.add(R.mipmap.ic_launcher);
        mDrawableList.add(R.mipmap.ic_launcher);

        gv_Main.setAdapter(new MyGridAdapter(this, mNameList, mDrawableList));
    }

    private void initViews() {
        gv_Main = (GridView)findViewById(R.id.gv_Main);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
    startActivity(new Intent(MainActivity.this,PhotoCropUploadActivity.class));
                break;
            case 1:
                startActivity(new Intent(MainActivity.this, RichEditorActicity.class));
                break;
            case 2:
                startActivity(new Intent(MainActivity.this,ExpandableListTestActivity.class));
                break;
            case 3:
                startActivity(new Intent(MainActivity.this,OtherInfoActivity.class));
                break;
            case 4:
                startActivity(new Intent(MainActivity.this,MySettingsActivity.class));
                break;
        }
    }
}
