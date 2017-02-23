package com.xuema.controller.binder;
import org.springframework.beans.propertyeditors.PropertiesEditor;

public class BooleanEditor extends  PropertiesEditor {
    @Override    
    public void setAsText(String text) throws IllegalArgumentException {    
        if (text == null || text.equals("")) {    
            text = "false";    
        }
        setValue(Boolean.valueOf(text));    
    }    
    
    @Override    
    public String getAsText() {    
        return getValue().toString();    
    }   
}
