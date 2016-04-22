package com.example.herotest.view;

import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.herotest.R;

public class MainActivity extends BaseActivity {
    private ImageView iv_change;
    private TextView tv_district;
    private ImageView iv_refresh;
    private TextView tv_time;
    private TextView tv_date;
    private TextView tv_weather;
    private TextView tv_temprature;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        setUpView();
        initData();
    }

    private void setUpView() {
        // TODO Auto-generated method stub
        
    }

    private void initData() {
        // TODO Auto-generated method stub
        
    }

}
