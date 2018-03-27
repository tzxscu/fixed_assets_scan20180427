package com.tzxscu.cdfy.dbutils;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.List;
import com.tzxscu.cdfy.beans.exp_dict_bean;
import com.tzxscu.cdfy.dbutils.dbcreate;

/**
 * Created by Administrator on 2018/3/12.
 */

public class dbclass {

    private dbcreate openHelper;

    public boolean updatedb(){


        return true;
    }

    public boolean inert_data( List<exp_dict_bean> mlist){

        if (null==mlist || mlist.size()<=0)
        {
            return false;
        }

        SQLiteDatabase db = null;
        try {

            db = openHelper.getWritableDatabase();
            String sql = "insert exp_dict(exp_code,exp_name,exp_spec,firm_id,unit,location,import_date,use_flag) into"+
                    "?,?,?,?,?,?,?,?";
            SQLiteStatement stat = db.compileStatement(sql);
            db.beginTransaction();
            for (exp_dict_bean edb : mlist){
                stat.bindString(1,edb.getExp_name());
                stat.bindString(2,edb.getExp_code());
                stat.bindString(3,edb.getExp_spec());
                stat.bindString(4,edb.getFirm_id());
                stat.bindString(5,edb.getUnit());
                stat.bindString(6,edb.getLocation());
                stat.bindString(7,edb.getImport_date());
                stat.bindLong(8,edb.getUse_flag());
                if (stat.executeInsert()<0){
                    return false;
                }
            }
            db.setTransactionSuccessful();
        }catch (Exception e){
            return false;
        }
        finally {
            db.endTransaction();
            db.close();
        }



        return  true;
    }



}
