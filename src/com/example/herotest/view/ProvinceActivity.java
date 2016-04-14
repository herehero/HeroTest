package com.example.herotest.view;

import java.util.ArrayList;
import java.util.List;

import com.example.herotest.R;
import com.example.herotest.model.QueryCallbackInf;
import com.example.herotest.model.QueryService;

import android.app.DownloadManager.Query;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ProvinceActivity extends BaseActivity {
    private ListView lv_province;
    private Adapter mAdapter;
    private ArrayList<String> ls_province;
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
        QueryService.queryProvince(new QueryCallbackInf(){

            @Override
            public void CallbackSucess(Object obj) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void CallbackFailed() {
                // TODO Auto-generated method stub
                
            }
            
        });
    }

}
