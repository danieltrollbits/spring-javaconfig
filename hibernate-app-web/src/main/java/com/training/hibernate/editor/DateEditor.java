package com.training.hibernate.editor;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.lang.NumberFormatException;
import java.beans.PropertyEditorSupport;

public class DateEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) {
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        Date date = null;
        try{
            date = df.parse(text);
        }catch(ParseException|NumberFormatException e){
            e.printStackTrace();
        }
        this.setValue(date);
    }

}