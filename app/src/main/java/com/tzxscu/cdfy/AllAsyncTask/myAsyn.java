package com.tzxscu.cdfy.AllAsyncTask;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import com.tzxscu.cdfy.dbutils.webserviceutil;
import com.mingle.widget.ShapeLoadingDialog;
import com.tzxscu.cdfy.fixed_assets_scan.R;
import com.tzxscu.cdfy.utils.DbrecieveListen;

import org.w3c.dom.Text;


/**
 * Created by Administrator on 2018/3/16.
 */

public class myAsyn extends AsyncTask {

    private Activity mactivity;
    private ShapeLoadingDialog shapeLoadingDialog;
    private String mexp_code;
    DbrecieveListen dbrecieveListen;


    public myAsyn(Activity activity,String exp_code){
        super();
        mactivity = activity;
        mexp_code = exp_code;
    }

    public void setDbrecieveListen(DbrecieveListen dbrecieveListen){
        this.dbrecieveListen = dbrecieveListen;
    }


    @Override
    protected Object doInBackground(Object[] objects) {

        String ret="";
        String exp_code="exp_0001";

        webserviceutil webserviceutil = new webserviceutil();
        try {
           ret= webserviceutil.dbupdate_by_exp_code(mexp_code);
          //  ret= webserviceutil.dbupdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPreExecute() {
        shapeLoadingDialog = new ShapeLoadingDialog.Builder(mactivity,this).loadText("加载中").build();
        shapeLoadingDialog.show();
    }

    @Override
    protected void onPostExecute(Object o) {
        shapeLoadingDialog.dismiss();
        dbrecieveListen.onDataSuccessfully(o);
  //      Toast.makeText(mactivity,o.toString(),Toast.LENGTH_SHORT).show();
        super.onPostExecute(o);
    }
}
