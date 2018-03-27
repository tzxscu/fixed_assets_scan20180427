package com.tzxscu.cdfy.utils;

import android.util.Xml;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xml.sax.InputSource;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.tzxscu.cdfy.beans.exp_dict_bean;


/**
 * Created by Administrator on 2018/3/25.
 */

public class xmlconvert {
//    public List<Map<String,Object>> xmlconvert(String xmlstring){
//        List<Map<String,Object>> arrlist = new ArrayList<>();
//        Map<String,Object> mmap = new HashMap<String, Object>();
//        try {
//            StringReader reader = new StringReader(xmlstring);
//            // 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
//            InputSource source = new InputSource(reader);
//            SAXBuilder saxBuilder = new SAXBuilder();
//            Document document = (Document)saxBuilder.build(source);
//            Element root = document.getRootElement();
//
//
//        }catch (Exception e){
//
//        }
//
//
//
//
//        return arrlist;
//    }

    public List<exp_dict_bean> xmlcdata(String xmlstring) throws XmlPullParserException, IOException {

        List<exp_dict_bean> list = new ArrayList<exp_dict_bean>();
        exp_dict_bean mexp_dict_bean = null;
        XmlPullParser pullParser = Xml.newPullParser();
        pullParser.setInput(new StringReader(xmlstring));

        while (pullParser.next() != XmlPullParser.END_DOCUMENT){
            if (pullParser.getEventType() == XmlPullParser.START_TAG){
                String name = pullParser.getName();

                if ("exp_infor".equals(name)){
                    mexp_dict_bean = new exp_dict_bean();
                    list.add(mexp_dict_bean);
                }else if ("exp_code".equals(name)){
                    mexp_dict_bean.setExp_code(pullParser.nextText());
                }else if ("exp_name".equals(name)){
                    mexp_dict_bean.setExp_name(pullParser.nextText());
                }else if ("exp_spec".equals(name)){
                    mexp_dict_bean.setExp_code(pullParser.nextText());
                }else if ("firm_id".equals(name)){
                    mexp_dict_bean.setExp_name(pullParser.nextText());
                }else if ("unit".equals(name)){
                    mexp_dict_bean.setExp_code(pullParser.nextText());
                }else if ("location".equals(name)){
                    mexp_dict_bean.setExp_name(pullParser.nextText());
                } else if ("import_date".equals(name)){
                    mexp_dict_bean.setExp_code(pullParser.nextText());
                }else if ("use_flag".equals(name)){
                    mexp_dict_bean.setExp_name(pullParser.nextText());
                }
            }

        }

        return list;
    }

}
