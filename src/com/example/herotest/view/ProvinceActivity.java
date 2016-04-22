package com.example.herotest.view;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.herotest.R;
import com.example.herotest.model.QueryCallbackInf;
import com.example.herotest.model.location.City;
import com.example.herotest.model.location.Province;
import com.example.herotest.model.service.ServiceQuery;
import com.example.herotest.utils.LogMgr;

public class ProvinceActivity extends BaseActivity implements OnItemClickListener {
    private ListView lv_province;
    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> ls_province;
    ServiceQuery mServiceQuery = ServiceQuery.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_province);
        lv_province = (ListView) findViewById(R.id.lv_province);
        initData();
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ls_province);
        lv_province.setAdapter(mAdapter);
        lv_province.setOnItemClickListener(this);
    }

    private void initData() {
        showProgressDialog("正在加载");
        ls_province = new ArrayList<String>();
        mServiceQuery.queryProvince(new QueryCallbackInf() {

            @Override
            public void onEvent(int type, Object obj) {
                dismissProgressDialog();
                switch (type) {
                case QueryCallbackInf.CALLBACK_GET_PROVINCE_SUCESS:
                    ArrayList<Province> provinces = (ArrayList<Province>) obj;
                    for (Province province : provinces) {
                        ls_province.add(province.getProvinceName());
                    }
                    refreshData(ls_province);
                    break;

                case QueryCallbackInf.CALLBACK_GET_PROVINCE_FAILED:
                    LogMgr.e("get Province failed");
                    break;
                }

            }
        });
    }

    private void refreshData(ArrayList<String> lsProvince) {
        this.ls_province = lsProvince;
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String provinceName=ls_province.get(position);
        Intent intent=new Intent(ProvinceActivity.this, City.class);
        intent.putExtra("provinceName", provinceName);
        startActivity(intent);
    }
}
