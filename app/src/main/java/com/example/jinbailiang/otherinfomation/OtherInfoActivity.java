package com.example.jinbailiang.otherinfomation;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Space;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.jinbailiang.demos_jinbai.R;

public class OtherInfoActivity extends Activity implements View.OnClickListener {

    private WebView webView_otherInfo;
    private Spinner spinner_otherInfo;
    private EditText editText_input;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_info);
        initViews();
        setDatas();
    }

    private void setDatas() {
        webView_otherInfo.getSettings().setJavaScriptEnabled(true);
        webView_otherInfo.getSettings().setBuiltInZoomControls(true);
        webView_otherInfo.getSettings().setSupportZoom(true);
        webView_otherInfo.loadUrl("https://www.baidu.com");
        showWebView();
    }

    private void initViews() {
        webView_otherInfo = (WebView) findViewById(R.id.webView_otherInfo);
        spinner_otherInfo = (Spinner) findViewById(R.id.spinner_otherInfo);
        editText_input = (EditText) findViewById(R.id.editText_input);
        editText_input.setOnClickListener(this);
        setPopupWindowsView();
        editText_input.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (editText_input.hasFocus() == false) {
                    if (popupWindow!=null&&popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.editText_input:
                if(popupWindow!=null&&popupWindow.isShowing()){
                    popupWindow.dismiss();
                }else{
                    popupWindow.showAsDropDown(v, 0, 5);
                }
                break;
        }
    }

    private void setPopupWindowsView() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(5, 5, 5, 5);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setBackgroundColor(Color.parseColor("#55F5F5DC"));
        linearLayout.setLayoutParams(params);
        //提交button
        Button button = new Button(this);
        button.setPaintFlags(0);
        button.setText("进入");
        button.setLayoutParams(params);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView_otherInfo.loadUrl(editText_input.getText().toString());
                showWebView();
            }
        });
        linearLayout.addView(button);
        //item00
        final TextView textView01 = new TextView(this);
        textView01.setPaintFlags(1);
        textView01.setText("http://baike.baidu.com/link?url=L2BnL14U0mrrsgxY0jtEOvtrYo_7QYOW6bFneAjvi4rSykoZlse2jNVRgMAEfeB9uWdjQtD2AYvs2TKiLxfbhq");//3des
        textView01.setLayoutParams(params);
        textView01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView_otherInfo.loadUrl(textView01.getText().toString());
                showWebView();
            }
        });
        linearLayout.addView(textView01);
        //item01
        final TextView textView02 = new TextView(this);
        textView02.setPaintFlags(2);
        textView02.setText("http://www.cnblogs.com/meng72ndsc/archive/2010/12/19/1910881.html");//3des
        textView02.setLayoutParams(params);
        textView02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView_otherInfo.loadUrl(textView02.getText().toString());
                showWebView();
            }
        });
        linearLayout.addView(textView02);

        android.util.AttributeSet attrs = null;
        popupWindow = new PopupWindow(linearLayout,  1000,2000);

    }

    private void showWebView() {
        webView_otherInfo.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        editText_input.setText(webView_otherInfo.getUrl());
        popupWindow.dismiss();
    }


}
