package com.example.herotest.view;

import java.util.ArrayList;
import java.util.List;

import com.example.herotest.R;
import com.example.herotest.model.QueryCallbackInf;
import com.example.herotest.model.service.ServiceQuery;

import android.app.DownloadManager.Query;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ProvinceActivity extends BaseActivity {
    private ListView lv_province;
    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> ls_province;
    ServiceQuery mServiceQuery=ServiceQuery.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_province);
        lv_province=(ListView)findViewById(R.id.lv_province);
        initData();
        mAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ls_province);
    }
    private void initData() {
        ls_province=new ArrayList<String>();
        mServiceQuery.queryProvince(new QueryCallbackInf(){

            @Override
            public void onEvent(int type, Object param) {
                switch (type) {
                case QueryCallbackInf.CALLBACK_GET_PROVINCE_SUCESS:
                    ArrayList<String> ls_province=(ArrayList<String>) param; 
                    refreshData(ls_province);
                    break;

                default:
                    break;
                }
                
            }

            
        });
    }
    private void refreshData(ArrayList<String> lsProvince){
    this.ls_province=lsProvince;
    mAdapter.notifyDataSetChanged();
}
}
