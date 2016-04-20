package com.example.herotest.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;

public class BaseActivity extends Activity{
    protected ProgressDialog mProgressDialog;
    private Handler mHandler = new Handler(); 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressDialog=new ProgressDialog(this);
    }
    public void showProgressDialog(final String message){
        mHandler.post(new Runnable() {
            
            @Override
            public void run() {
                if (mProgressDialog==null) {
                    return;
                }
                mProgressDialog.setMessage(message);
                mProgressDialog.show();
                
            }
        });

    }
    
    public void dismissProgressDialog(final String message){
        
    }

}
