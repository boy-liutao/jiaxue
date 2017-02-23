package com.xuema.controller;

import java.util.Date;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.xuema.controller.binder.BooleanEditor;
import com.xuema.controller.binder.DateEditor;
import com.xuema.controller.binder.IntegerEditor;

public class BaseController {
    @InitBinder    
    protected void initBinder(WebDataBinder binder) {    
        binder.registerCustomEditor(boolean.class, new BooleanEditor());    
        binder.registerCustomEditor(int.class, new IntegerEditor());    
        binder.registerCustomEditor(Date.class, new DateEditor());    
    }   
}
