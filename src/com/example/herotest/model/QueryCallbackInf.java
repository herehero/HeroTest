package com.example.herotest.model;

public interface QueryCallbackInf {
public final static int CALLBACK_GET_PROVINCE_SUCESS=1; 
public final static int CALLBACK_GET_PROVINCE_FAILED=2; 
public abstract void onEvent(int type,Object obj);
}
