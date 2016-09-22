package com.example.jinbailiang.expandablelist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.jinbailiang.demos_jinbai.R;

import java.util.zip.Inflater;

/**
 * Created by yess on 2016/6/21.
 */
public class MyExpandableListAdapter extends BaseExpandableListAdapter {
    private Object  []groups ;
    private Object [] []childs;
    private Context context;


    public MyExpandableListAdapter(Object[] groups, Object[][] childs, Context context) {
        this.groups = groups;
        this.childs = childs;
        this.context = context;
    }


    @Override
    public int getGroupCount() {
        return childs.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childs[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childs[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater  layoutInflater= LayoutInflater.from(context);
            convertView = layoutInflater.inflate( R.layout.expanablelistview_group_item, null);
        }
        TextView group_name = (TextView) convertView.findViewById(R.id.group_name);
        group_name.setText(groups[groupPosition].toString());
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = View.inflate(context, R.layout.expanablelistview_child_item,null);
        }
        String [] name = (String[]) childs[groupPosition][childPosition];
        String [] flag = (String[]) childs[groupPosition][childPosition];
        TextView item_name = (TextView)convertView.findViewById(R.id.item_name);
        TextView item_flag = (TextView)convertView.findViewById(R.id.item_flag);
        item_name.setText(name[0].toString());
        item_flag.setText(flag[1].toString());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
