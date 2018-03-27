package com.tzxscu.cdfy.fixed_assets_scan;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.mingle.widget.ShapeLoadingDialog;
import com.tzxscu.cdfy.AllAsyncTask.myAsyn;
import com.tzxscu.cdfy.utils.DbrecieveListen;
import com.tzxscu.cdfy.dbutils.dbcreate;
import com.tzxscu.cdfy.utils.SaxXml;
import com.tzxscu.cdfy.utils.xmlconvert;
import com.tzxscu.cdfy.beans.exp_dict_bean;
import com.tzxscu.cdfy.dbutils.dbclass;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {


    private myAsyn mysyn;
    private ShapeLoadingDialog sa;
    private TextView textView;
    private TextView textView2;
    private List<exp_dict_bean> mlist;
    private ListView mylistview;
    private SimpleAdapter simp_ada;
    private ArrayList<Map<String, Object>> arr_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.main_page_text);
        textView2 = findViewById(R.id.main_page_text2);
        Button button = findViewById(R.id.asset_scan_btn);
        mylistview = (ListView)findViewById(R.id.main_listview);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,code_scan.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.about:
                getupdate("exp_0001");
                break;
            case R.id.update:
                Toast.makeText(this,"update",Toast.LENGTH_SHORT).show();
                break;
            case R.id.create_db:
                dbcreate mdbcreate = new dbcreate(this);
                mdbcreate.getWritableDatabase();
            default:
                break;
        }
        return true;
    }

    private boolean getupdate(String exp_code){

        arr_data = new ArrayList<Map<String, Object>>();


        mysyn = new myAsyn(this,exp_code);
        mysyn.execute();
        mysyn.setDbrecieveListen(new DbrecieveListen() {
            @Override
            public void onDataSuccessfully(Object data)  {
              //  textView.setText(data.toString());
//                xmlconvert mxmlconvert = new xmlconvert();
//                try {
//                    mlist = mxmlconvert.xmlcdata(data.toString());
//                } catch (XmlPullParserException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                InputStream is = new ByteArrayInputStream(data.toString().getBytes());
                SAXParserFactory factory = SAXParserFactory.newInstance();
                try {
                    SAXParser saxParser = factory.newSAXParser();
                    SaxXml handler = new SaxXml();
                    try {
                        saxParser.parse(is,handler);
                        List<exp_dict_bean> mmlist = handler.getexp();
 //                     dbclass mdbclass = new dbclass();

//                       if (mdbclass.inert_data(mmlist)){
//                           Toast.makeText(MainActivity.this,"插入成功",Toast.LENGTH_SHORT).show();
//                       }
                        String tet = null;
                        for (exp_dict_bean edb : mmlist) {
                            tet += edb.getExp_name() + edb.getExp_code() + edb.getLocation() + edb.getExp_spec();
                        }
                        textView2.setText(tet);

                        for (int i=0;i<mmlist.size();i++){
                            Map map = new HashMap<String,Object>();
                            map.put("exp_code",mmlist.get(i).getExp_code());
                            map.put("exp_name",mmlist.get(i).getExp_name());
                            map.put("exp_spec",mmlist.get(i).getExp_spec());
                            map.put("exp_firm_id",mmlist.get(i).getFirm_id());
                            map.put("exp_unit",mmlist.get(i).getUnit());
                            arr_data.add(map);
                        }

                        String[] from = {"exp_code","exp_name","exp_spec","exp_unit","exp_firm_id"};
                        int[] to = {R.id.list_view_xml_exp_code,R.id.list_view_xml_exp_name,R.id.list_view_xml_exp_spec,R.id.list_view_xml_exp_unit,R.id.list_view_xml_exp_firm};
                        simp_ada = new SimpleAdapter(MainActivity.this,arr_data,R.layout.list_v_xml,from,to);
                        mylistview.setAdapter(simp_ada);




                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void onDataFailed() {

            }
        });

        return  true;
    }

}
