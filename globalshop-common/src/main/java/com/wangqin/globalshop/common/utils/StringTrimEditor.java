package com.wangqin.globalshop.common.utils;

import java.beans.PropertyEditorSupport;

public class StringTrimEditor extends PropertyEditorSupport {
	public StringTrimEditor() {}

    @Override
    public String getAsText() {
        Object value = getValue();
        return value != null ? value.toString() : "";
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text == null) {
            setValue(null);
        } else {
            setValue(StringUtils.trimWhitespace(text));
        }
    }
}
