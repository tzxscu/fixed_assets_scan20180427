package com.tzxscu.cdfy.utils;

/**
 * Created by Administrator on 2018/3/22.
 * 接口用于async返回值
 */

public interface DbrecieveListen {
    public void onDataSuccessfully(Object data);
    public void onDataFailed();
}
