package com.nakulsiwach.ecommproj.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String field;
    String fieldName;
    Long fieldId;

    public ResourceNotFoundException(String resourceName, String field, String fieldName) {
        super(String.format("%s not found with %s",resourceName,fieldName,field));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldName = fieldName;
    }

    public ResourceNotFoundException( String resourceName,String field,Long fieldId) {
        super(String.format("%s not found with %s",resourceName,fieldId,field));
        this.fieldId = fieldId;
        this.field = field;
        this.resourceName = resourceName;
    }
}
