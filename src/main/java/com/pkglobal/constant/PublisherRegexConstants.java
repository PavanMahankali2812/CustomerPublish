package com.pkglobal.constant;

public enum PublisherRegexConstants {

    CUSTOMER_NUMBER_REGEX_EXPRESSION("\\w(?<=\\w{7})"), BIRTH_DATE_REGEX_EXPRESSION(
            "\\d(?=[^-]*?-)"), EMAIL_ID_REGEX_EXPRESSION(".(?<!.{5})"), MASK_CONSTANTS("*");

    private String regexExpression;

    PublisherRegexConstants(String regexExpression) {
        this.regexExpression = regexExpression;
    }

    public String getRegexExpression() {
        return regexExpression;
    }

}
