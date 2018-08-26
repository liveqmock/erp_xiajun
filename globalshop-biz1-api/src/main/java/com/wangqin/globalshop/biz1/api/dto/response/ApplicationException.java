package com.wangqin.globalshop.biz1.api.dto.response;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;

@Getter
public class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = -2765274790055355530L;

    private BaseReturnType type;

    private Object[]       args;

    public ApplicationException(BaseReturnType type, Object... args) {
        super(MessageFormat.format(StringUtils.stripToEmpty(type.getMessage()), args));
        this.type = type;
        this.args = args;
    }
}
