package com.ethicalhackingblog.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BlogNotFoundException extends RuntimeException{
    private static final long serialVersionId = 1L;
    private String resouceName;
    private String fieldName;
    private Object fieldValue;

    public BlogNotFoundException(String resouceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : %s", resouceName, fieldName, fieldValue));
        this.resouceName = resouceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getResouceName() {
        return resouceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
