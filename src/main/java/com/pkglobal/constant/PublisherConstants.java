package com.pkglobal.constant;

public enum PublisherConstants {

    ERROR("error"), ERROR_STRING("Errors occured during Published message and exception is {}");

    private String value;

    PublisherConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
