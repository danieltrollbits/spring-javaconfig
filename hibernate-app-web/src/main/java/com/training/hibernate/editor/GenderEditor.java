package com.training.hibernate.editor;

import java.util.Date;
import java.beans.PropertyEditorSupport;
import com.training.hibernate.model.Gender;

public class GenderEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) {
        Gender gender = Gender.valueOf(text.toUpperCase());
        this.setValue(gender);
    }

}