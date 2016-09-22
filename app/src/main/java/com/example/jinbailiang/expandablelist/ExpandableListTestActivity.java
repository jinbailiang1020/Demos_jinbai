package com.example.jinbailiang.expandablelist;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.jinbailiang.demos_jinbai.R;

import java.util.ArrayList;
import java.util.Objects;

/**
 * 二维数组  局部刷新
 */
public class ExpandableListTestActivity extends Activity {

    private ExpandableListView expandListView;
    private MyExpandableListAdapter adapter;
    private Object[][] childs;
    private Object groups[];
    private int groupPosition;
    private int childPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_test);
        setViews();
//        Fresco
    }

    private void setViews() {
        groupPosition = -1;
        childPosition = -1;
        expandListView = (ExpandableListView) findViewById(R.id.expandListView);
        String chlid01[] = new String[]{"小明", "优秀"};
        String chlid02[] = new String[]{"小红", "优秀"};

        String chlid11[] = new String[]{"小方", "良好"};
        String chlid12[] = new String[]{"小为", "一般"};
        String chlid13[] = new String[]{"小萌", "优秀"};
          groups = new Object[] {"A班", "B班", "C班"};
        childs = new Object[][]{{chlid01, chlid02}, {chlid01, chlid02, chlid11, chlid12, chlid13, chlid01, chlid02, chlid11, chlid01, chlid02, chlid11, chlid01, chlid02, chlid11}, {chlid01, chlid02, chlid01, chlid02, chlid01, chlid02, chlid01, chlid02, chlid01, chlid02}};

        adapter = new MyExpandableListAdapter(groups, childs, this);
        expandListView.setAdapter(adapter);
        expandListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                ExpandableListTestActivity.this.groupPosition = groupPosition;
                ExpandableListTestActivity.this.childPosition = childPosition;
                Intent intent = new Intent(ExpandableListTestActivity.this, ItemShowActivity.class);
                String[] arrayChild = (String[]) childs[groupPosition][childPosition];
                intent.putExtra("text", arrayChild[0] + "--" + arrayChild[1]);
                intent.putExtra("strArray", (String[]) childs[groupPosition][childPosition]);
//                intent.putExtra("groupPosition",groupPosition);
//                intent.putExtra("childPosition",childPosition);
                startActivity(intent);
                return false;
            }
        });

        ////
        expandListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                Toast.makeText(ExpandableListTestActivity.this,"group", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(groupPosition != -1) {
            ((String[]) childs[groupPosition][childPosition])[1] = "点击修改了";
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}
