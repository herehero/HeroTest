package com.example.herotest.view;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.herotest.R;
import com.example.herotest.model.QueryCallbackInf;
import com.example.herotest.model.location.City;
import com.example.herotest.model.service.ServiceQuery;
import com.example.herotest.utils.LogMgr;

public class CityActivity extends BaseActivity implements OnItemClickListener {
    private ListView lv_city;
    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> ls_city = new ArrayList<String>();
    ServiceQuery mServiceQuery = ServiceQuery.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_city);
        lv_city = (ListView) findViewById(R.id.lv_city);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, ls_city);
        lv_city.setAdapter(mAdapter);
        initData();
        lv_city.setOnItemClickListener(this);
    }

    private void initData() {
        showProgressDialog("正在加载");
        Intent i = getIntent();
        String provinceName = i.getStringExtra("provinceName");
        LogMgr.d("provinceName::" + provinceName);
        mServiceQuery.queryCity(new QueryCallbackInf() {

            @Override
            public void onEvent(int type, Object obj) {
                dismissProgressDialog();
                switch (type) {
                case QueryCallbackInf.CALLBACK_GET_PROVINCE_SUCESS:
                    ArrayList<City> cities = (ArrayList<City>) obj;
                    for (City city : cities) {
                        ls_city.add(city.getCityName());
                    }
                    LogMgr.d("ls_city::" + ls_city);
                    refreshData(ls_city);
                    break;

                case QueryCallbackInf.CALLBACK_GET_PROVINCE_FAILED:
                    LogMgr.e("get City failed");
                    break;
                }
            }

        }, provinceName);

    }

    private void refreshData(ArrayList<String> ls_city) {
        this.ls_city = ls_city;
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String cityName=this.ls_city.get(position);
        Intent i=new Intent(CityActivity.this, DistrictActivity.class);
        i.putExtra("cityName", cityName);
        startActivity(i);
    }
}
