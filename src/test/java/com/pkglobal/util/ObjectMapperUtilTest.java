package com.pkglobal.util;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pkglobal.model.Address;
import com.pkglobal.model.CustomerStatusEnum;
import com.pkglobal.model.MessageRequest;


class ObjectMapperUtilTest {

    @Mock
    private ObjectMapper mapper;

    @Test
    void testObjectMapperUtilWhenDomainObjectPassed() throws Exception {
        String expected =
                "{\"customerNumber\":\"CUST123456\",\"firstName\":\"pavan pavan\",\"lastName\":\"kumar kumar\",\"birthDate\":null,\"country\":\"India\",\"countryCode\":\"IN\",\"mobileNumber\":\"9912101210\",\"email\":\"pavan@gmail.com\",\"customerStatus\":\"OPEN\",\"address\":{\"addressLine1\":\"lakkavaram\",\"addressLine2 \":\"west\",\"street\":\"west\",\"postalCode\":\"534451\"}}";
        MessageRequest messageRequest = buildMessageRequestObject();
        String result = ObjectMapperUtil.returnJsonFromObject(messageRequest);
        Assertions.assertEquals(expected, result);
    }

    private MessageRequest buildMessageRequestObject() {
        MessageRequest messageRequest = new MessageRequest();
        Address address = buildAddressObject();
        messageRequest.setAddress(address);
        messageRequest.setCountry("India");
        messageRequest.setCountryCode("IN");
        messageRequest.setCustomerNumber("CUST123456");
        messageRequest.setCustomerStatus(CustomerStatusEnum.OPEN);
        messageRequest.setEmail("pavan@gmail.com");
        messageRequest.setFirstName("pavan pavan");
        messageRequest.setLastName("kumar kumar");
        messageRequest.setMobileNumber("9912101210");
        return messageRequest;
    }

    private Address buildAddressObject() {
        Address address = new Address();
        address.setAddressLine1("lakkavaram");
        address.setAddressLine2("west");
        address.setPostalCode("534451");
        address.setStreet("west");
        return address;
    }

}
