package com.example.jinbailiang.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jinbailiang.demos_jinbai.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yess on 2016/6/15.
 */
public class MyGridAdapter extends BaseAdapter {
private List<String>strList = new ArrayList<>();
    private List<Drawable>drawableList = new ArrayList<>();

    private ArrayList<String> mNameList = new ArrayList<String>();
    private ArrayList<Integer> mDrawableList = new ArrayList<Integer>();
    private LayoutInflater mInflater;
    private Context mContext;
    LinearLayout.LayoutParams params;

    public MyGridAdapter(Context context, ArrayList<String> nameList, ArrayList<Integer> drawableList) {
        mNameList = nameList;
        mDrawableList = drawableList;
        mContext = context;
        mInflater = LayoutInflater.from(context);

        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
    }

    public int getCount() {
        return mNameList.size();
    }

    public Object getItem(int position) {
        return mNameList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ItemViewTag viewTag;

        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.gridview_item, null);

            // construct an item tag
            viewTag = new ItemViewTag((ImageView) convertView.findViewById(R.id.grid_icon), (TextView) convertView.findViewById(R.id.grid_name));
            convertView.setTag(viewTag);
        } else
        {
            viewTag = (ItemViewTag) convertView.getTag();
        }

        // set name
        viewTag.mName.setText(mNameList.get(position));

        // set icon
        viewTag.mIcon.setImageResource(mDrawableList.get(position));
        viewTag.mIcon.setLayoutParams(params);
        return convertView;
    }

    class ItemViewTag
    {
        protected ImageView mIcon;
        protected TextView mName;


        public ItemViewTag(ImageView icon, TextView name)
        {
            this.mName = name;
            this.mIcon = icon;
        }
    }

}
