package com.example.jinbailiang.expandablelist;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.jinbailiang.demos_jinbai.R;

public class ItemShowActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_show);

        ((TextView) findViewById(R.id.itemshow_textview)).setText(getIntent().getStringExtra("text"));
        findViewById(R.id.itemshow_textview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
getIntent().getStringArrayExtra("strArray")[1] ="修改了" ;
//                int childPosition = getIntent().getIntExtra("childPosition", 0);
//                int groupPosition = getIntent().getIntExtra("groupPosition", 0);
//              String[] childs00 =  (String[]) childs[groupPosition];
//                childs[groupPosition][childPosition] = "修改了";
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
