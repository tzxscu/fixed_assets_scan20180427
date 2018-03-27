package com.tzxscu.cdfy.dbutils;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Administrator on 2018/3/22.
 */

public class dbcreate extends SQLiteOpenHelper {

    private String db_name = "asset_db";
    private String table_name ="exp_dict";

    public dbcreate(Context context){
        super(context,"asset_db",null,2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       String drop_sql = "DROP TABLE IF EXISTS exp_dict";
        String create_sql = "CREATE TABLE exp_dict (" +
                "  exp_code varchar(10) NOT NULL primary key," +
                "  exp_name varchar(255) DEFAULT NULL," +
                "  exp_sepc varchar(255) NOT NULL," +
                "  firm_id varchar(255) DEFAULT NULL," +
                "  unit varchar(255) DEFAULT NULL," +
                "  location varchar(255) DEFAULT NULL," +
                "  use_dept varchar(255) DEFAULT NULL," +
                "  import_date varchar(255) DEFAULT NULL," +
                "  use_flag int(1) NOT NULL" +
                ") ";

       db.execSQL(drop_sql);
       Log.d("ed","drop_sql");
        db.execSQL(create_sql);
        Log.d("ed","create_sql");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
