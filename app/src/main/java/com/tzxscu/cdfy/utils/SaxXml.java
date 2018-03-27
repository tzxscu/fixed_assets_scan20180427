package com.tzxscu.cdfy.utils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import com.tzxscu.cdfy.beans.exp_dict_bean;

/**
 * Created by Administrator on 2018/3/25.
 */

;import java.util.ArrayList;
import java.util.List;

public class SaxXml extends DefaultHandler {
    private List<exp_dict_bean> mlsit = null;
    private exp_dict_bean mexp_dict_bean = null;
    private StringBuffer stringBuffer;
    private String tag=null;

    public String getXmlHeader()
    {
        return stringBuffer.toString();
    }

    public List<exp_dict_bean> getexp(){
        return mlsit;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        mlsit = new ArrayList<exp_dict_bean>();
        stringBuffer = new StringBuffer();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if ("exp_infor".equals(localName)){
            mexp_dict_bean = new exp_dict_bean();
        }
        tag = localName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if (null != tag){
            String value = new String(ch,start,length);
            System.out.print("value="+value);
            if ("exp_code".equals(tag)){
                mexp_dict_bean.setExp_code(value);
            }else if ("exp_name".equals(tag)){
                mexp_dict_bean.setExp_name(value);
            } else if ("exp_spec".equals(tag)){
                mexp_dict_bean.setExp_spec(value);
            }else if ("firm_id".equals(tag)){
                mexp_dict_bean.setFirm_id(value);
            }else if ("unit".equals(tag)){
                mexp_dict_bean.setUnit(value);
            }else if ("location".equals(tag)){
                mexp_dict_bean.setLocation(value);
            } else if ("import_date".equals(tag)){
                mexp_dict_bean.setImport_date(value);
            }else if ("use_flag".equals(tag)){
                mexp_dict_bean.setUse_flag(Integer.parseInt(value));
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("exp_infor".equals(qName)){
            mlsit.add(mexp_dict_bean);
            mexp_dict_bean = null;
        }
        tag = null;
        super.endElement(uri, localName, qName);
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
