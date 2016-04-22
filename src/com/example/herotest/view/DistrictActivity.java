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
import com.example.herotest.model.location.District;
import com.example.herotest.model.service.ServiceQuery;
import com.example.herotest.utils.LogMgr;

public class DistrictActivity extends BaseActivity implements OnItemClickListener{
    private ListView lv_district;
    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> ls_district = new ArrayList<String>();
    ServiceQuery mServiceQuery = ServiceQuery.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_district);
        lv_district = (ListView) findViewById(R.id.lv_district);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, ls_district);
        lv_district.setAdapter(mAdapter);
        initData();
        lv_district.setOnItemClickListener(this);
    }

    private void initData() {
        showProgressDialog("正在加载");
        Intent i = getIntent();
        String cityName = i.getStringExtra("cityName");
        LogMgr.d("cityName::" + cityName);
        mServiceQuery.queryDistrict(new QueryCallbackInf() {

            @Override
            public void onEvent(int type, Object obj) {
                dismissProgressDialog();
                switch (type) {
                case QueryCallbackInf.CALLBACK_GET_PROVINCE_SUCESS:
                    ArrayList<District> districts = (ArrayList<District>) obj;
                    for (District district : districts) {
                        ls_district.add(district.getDistrict());
                    }
                    LogMgr.d("ls_district::" + ls_district);
                    refreshData(ls_district);
                    break;

                case QueryCallbackInf.CALLBACK_GET_PROVINCE_FAILED:
                    LogMgr.e("get City failed");
                    break;
                }
            }

        }, cityName);

    }

    private void refreshData(ArrayList<String> ls_district) {
        this.ls_district = ls_district;
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String districtName=this.ls_district.get(position);
        Intent i=new Intent(DistrictActivity.this, MainActivity.class);
        i.putExtra("districtName", districtName);
        startActivity(i);
    }
}
