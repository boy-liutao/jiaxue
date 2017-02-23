package com.xuema.controller.binder;
import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.util.StringUtils;

import com.xuema.util.DatetimeUtil;

public class DateEditor extends  PropertiesEditor {
    @Override    
    public void setAsText(String text) throws IllegalArgumentException {    
        try {
            if (StringUtils.isEmpty(text)){
                setValue(null);
            } else {
                Date d = DatetimeUtil.parseBorn(text);
                setValue(d);    
            }
        } catch (ParseException e) {
            //ignore
            setValue(null);
        }
    }    
    
    @Override    
    public String getAsText() {    
        return getValue().toString();    
    }   
}
