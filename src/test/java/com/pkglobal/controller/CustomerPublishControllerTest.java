package com.pkglobal.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.pkglobal.converter.DefaultMessageRequestMaskConverter;
import com.pkglobal.model.Address;
import com.pkglobal.model.AddressProducerRequest;
import com.pkglobal.model.CustomerStatusEnum;
import com.pkglobal.model.MessageProducerRequest;
import com.pkglobal.model.MessageRequest;
import com.pkglobal.model.MessageResponse;
import com.pkglobal.service.DefaultCustomerPublishService;
import com.pkglobal.util.*;




@ExtendWith(MockitoExtension.class)
 class CustomerPublishControllerTest {
	 private static final String RESPONSE_STRING =
	            "{\"status\":\"success\",\"data\":\"Published Message sucessfully\"}";

	    @InjectMocks
	    private CustomerPublishController messageProducerController;

	    @Mock
	    private DefaultCustomerPublishService defaultPublishService;

	    @Mock
	    private DefaultMessageRequestMaskConverter messageRequestMaskConverter;

	    private MockMvc mockMvc;

	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.initMocks(this);
	        mockMvc = MockMvcBuilders.standaloneSetup(messageProducerController).build();
	    }

	    @Test
	    void testPublishServiceWithSuccess() throws Exception {
	        MessageRequest message = buildMessageRequestObject();

	        Mockito.when(defaultPublishService.publishMessage(any()))
	                .thenReturn(buildMessageResponse());

	        MvcResult result = mockMvc
	                .perform(post("/customer-publish/v1/publish-message")
	                        .contentType(MediaType.APPLICATION_JSON).headers(buildHttpHeaders())
	                        .content(ObjectMapperUtil.returnJsonFromObject(message)))
	                .andExpect(status().is(200)).andReturn();
	        String responseString = result.getResponse().getContentAsString();
	        assertNotNull(responseString);
	        assertEquals(RESPONSE_STRING, responseString);
	    }

	    @Test
	    void testPublishServiceWithNullOrEmptyEmailId() throws Exception {
	        MessageRequest message = buildMessageRequestObject();
	        message.setEmail("");
	        MvcResult result = mockMvc
	                .perform(post("/customer-publish/v1/publish-message")
	                        .contentType(MediaType.APPLICATION_JSON).headers(buildHttpHeaders())
	                        .content(ObjectMapperUtil.returnJsonFromObject(message)))
	                .andExpect(status().is(400)).andReturn();

	    }

	    @Test
	    void testPublishServiceWithInvalidEmailId() throws Exception {
	        MessageRequest message = buildMessageRequestObject();
	        message.setEmail("invalid Email");
	        MvcResult result = mockMvc
	                .perform(post("/customer-publish/v1/publish-message")
	                        .contentType(MediaType.APPLICATION_JSON).headers(buildHttpHeaders())
	                        .content(ObjectMapperUtil.returnJsonFromObject(message)))
	                .andExpect(status().is(400)).andReturn();

	    }

	    @Test
	    void testPublishServiceWithNullOrEmptyCustomerNumber() throws Exception {
	        MessageRequest message = buildMessageRequestObject();
	        message.setCustomerNumber("");
	        MvcResult result = mockMvc
	                .perform(post("/customer-publish/v1/publish-message")
	                        .contentType(MediaType.APPLICATION_JSON).headers(buildHttpHeaders())
	                        .content(ObjectMapperUtil.returnJsonFromObject(message)))
	                .andExpect(status().is(400)).andReturn();

	    }

	    @Test
	    void testPublishServiceWithSizeOfCustomerNumberIsGreaterThanTen() throws Exception {
	        MessageRequest message = buildMessageRequestObject();
	        message.setCustomerNumber("12121212121212");
	        MvcResult result = mockMvc
	                .perform(post("/customer-publish/v1/publish-message")
	                        .contentType(MediaType.APPLICATION_JSON).headers(buildHttpHeaders())
	                        .content(ObjectMapperUtil.returnJsonFromObject(message)))
	                .andExpect(status().is(400)).andReturn();

	    }

	    @Test
	    void testPublishServiceWithNullOrEmptyFirstName() throws Exception {
	        MessageRequest message = buildMessageRequestObject();
	        message.setFirstName("");
	        MvcResult result = mockMvc
	                .perform(post("/customer-publish/v1/publish-message")
	                        .contentType(MediaType.APPLICATION_JSON).headers(buildHttpHeaders())
	                        .content(ObjectMapperUtil.returnJsonFromObject(message)))
	                .andExpect(status().is(400)).andReturn();

	    }

	    @Test
	    void testPublishServiceWithSizeOfFirstNameIsLessThanTen() throws Exception {
	        MessageRequest message = buildMessageRequestObject();
	        message.setFirstName("pavan");
	        MvcResult result = mockMvc
	                .perform(post("/customer-publish/v1/publish-message")
	                        .contentType(MediaType.APPLICATION_JSON).headers(buildHttpHeaders())
	                        .content(ObjectMapperUtil.returnJsonFromObject(message)))
	                .andExpect(status().is(400)).andReturn();

	    }

	    @Test
	    void testPublishServiceWithSizeOfFirstNameIsGreaterThanHundred() throws Exception {
	        MessageRequest message = buildMessageRequestObject();
	        message.setLastName(
	                "pavan pavan pavan pavan pavan pavan pavan pavan pavan pavan pavan pavan pavan pavan pavan pavan pavan pavan pavan pavan ");
	        MvcResult result = mockMvc
	                .perform(post("/customer-publish/v1/publish-message")
	                        .contentType(MediaType.APPLICATION_JSON).headers(buildHttpHeaders())
	                        .content(ObjectMapperUtil.returnJsonFromObject(message)))
	                .andExpect(status().is(400)).andReturn();

	    }

	    @Test
	    void testPublishServiceWithNullOrEmptyLastName() throws Exception {
	        MessageRequest message = buildMessageRequestObject();
	        message.setLastName("");
	        MvcResult result = mockMvc
	                .perform(post("/customer-publish/v1/publish-message")
	                        .contentType(MediaType.APPLICATION_JSON).headers(buildHttpHeaders())
	                        .content(ObjectMapperUtil.returnJsonFromObject(message)))
	                .andExpect(status().is(400)).andReturn();

	    }

	    @Test
	    void testPublishServiceWithSizeOfLastNameIsLessThanTen() throws Exception {
	        MessageRequest message = buildMessageRequestObject();
	        message.setLastName("mahankali");
	        MvcResult result = mockMvc
	                .perform(post("/customer-publish/v1/publish-message")
	                        .contentType(MediaType.APPLICATION_JSON).headers(buildHttpHeaders())
	                        .content(ObjectMapperUtil.returnJsonFromObject(message)))
	                .andExpect(status().is(400)).andReturn();

	    }

	    @Test
	    void testPublishServiceWithSizeOfLastNameIsGreaterThanHundred() throws Exception {
	        MessageRequest message = buildMessageRequestObject();
	        message.setLastName(
	                "mahankali mahankali mahankali mahankali mahankali mahankali mahankali mahankali mahankali mahankali mahankali mahankali mahankali mahankali mahankali mahankali mahankali mahankali mahankali mahankali ");
	        MvcResult result = mockMvc
	                .perform(post("/customer-publish/v1/publish-message")
	                        .contentType(MediaType.APPLICATION_JSON).headers(buildHttpHeaders())
	                        .content(ObjectMapperUtil.returnJsonFromObject(message)))
	                .andExpect(status().is(400)).andReturn();

	    }

	    @Test
	    void testPublishServiceWithNullOrEmptyEmail() throws Exception {
	        MessageRequest message = buildMessageRequestObject();
	        message.setEmail("");
	        MvcResult result = mockMvc
	                .perform(post("/customer-publish/v1/publish-message")
	                        .contentType(MediaType.APPLICATION_JSON).headers(buildHttpHeaders())
	                        .content(ObjectMapperUtil.returnJsonFromObject(message)))
	                .andExpect(status().is(400)).andReturn();

	    }

	    @Test
	    void testPublishServiceWithSizeOfEmailIsGreaterThanFifty() throws Exception {
	        MessageRequest message = buildMessageRequestObject();
	        message.setEmail(
	                "mahankalimahankalimahankalimahankalimahankalimahankali@gmail.com ");
	        MvcResult result = mockMvc
	                .perform(post("/customer-publish/v1/publish-message")
	                        .contentType(MediaType.APPLICATION_JSON).headers(buildHttpHeaders())
	                        .content(ObjectMapperUtil.returnJsonFromObject(message)))
	                .andExpect(status().is(400)).andReturn();

	    }

	    @Test
	    void testPublishServiceWithNullOrEmptyCountry() throws Exception {
	        MessageRequest message = buildMessageRequestObject();
	        message.setCountry("");
	        MvcResult result = mockMvc
	                .perform(post("/customer-publish/v1/publish-message")
	                        .contentType(MediaType.APPLICATION_JSON).headers(buildHttpHeaders())
	                        .content(ObjectMapperUtil.returnJsonFromObject(message)))
	                .andExpect(status().is(400)).andReturn();

	    }

	    @Test
	    void testPublishServiceWithNullOrEmptyCountryCode() throws Exception {
	        MessageRequest message = buildMessageRequestObject();
	        message.setCountryCode("");
	        MvcResult result = mockMvc
	                .perform(post("/customer-publish/v1/publish-message")
	                        .contentType(MediaType.APPLICATION_JSON).headers(buildHttpHeaders())
	                        .content(ObjectMapperUtil.returnJsonFromObject(message)))
	                .andExpect(status().is(400)).andReturn();

	    }

	    @Test
	    void testPublishServiceWithSizeOfCountryCodeIsGreaterThanTwo() throws Exception {
	        MessageRequest message = buildMessageRequestObject();
	        message.setCountryCode("Ind");
	        MvcResult result = mockMvc
	                .perform(post("/customer-publish/v1/publish-message")
	                        .contentType(MediaType.APPLICATION_JSON).headers(buildHttpHeaders())
	                        .content(ObjectMapperUtil.returnJsonFromObject(message)))
	                .andExpect(status().is(400)).andReturn();

	    }

	    @Test
	    void testPublishServiceWithNullOrEmptyMobileNumber() throws Exception {
	        MessageRequest message = buildMessageRequestObject();
	        message.setMobileNumber("");
	        MvcResult result = mockMvc
	                .perform(post("/customer-publish/v1/publish-message")
	                        .contentType(MediaType.APPLICATION_JSON).headers(buildHttpHeaders())
	                        .content(ObjectMapperUtil.returnJsonFromObject(message)))
	                .andExpect(status().is(400)).andReturn();

	    }

	    @Test
	    void testPublishServiceWithSizeOfMobileNumberIsGreaterThanTen() throws Exception {
	        MessageRequest message = buildMessageRequestObject();
	        message.setMobileNumber("9603335300");
	        MvcResult result = mockMvc
	                .perform(post("/customer-publish/v1/publish-message")
	                        .contentType(MediaType.APPLICATION_JSON).headers(buildHttpHeaders())
	                        .content(ObjectMapperUtil.returnJsonFromObject(message)))
	                .andExpect(status().is(400)).andReturn();

	    }

	    @Test
	    void testPublishServiceWithNullOrEmptyAddressLine1() throws Exception {
	        MessageRequest message = buildMessageRequestObject();
	        message.getAddress().setAddressLine1("");
	        MvcResult result = mockMvc
	                .perform(post("/customer-publish/v1/publish-message")
	                        .contentType(MediaType.APPLICATION_JSON).headers(buildHttpHeaders())
	                        .content(ObjectMapperUtil.returnJsonFromObject(message)))
	                .andExpect(status().is(400)).andReturn();

	    }

	    @Test
	    void testPublishServiceWithAddressLine1IsGreaterThanFittySize() throws Exception {
	        MessageRequest message = buildMessageRequestObject();
	        message.getAddress().setAddressLine1(
	                "ppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp");
	        MvcResult result = mockMvc
	                .perform(post("/customer-publish/v1/publish-message")
	                        .contentType(MediaType.APPLICATION_JSON).headers(buildHttpHeaders())
	                        .content(ObjectMapperUtil.returnJsonFromObject(message)))
	                .andExpect(status().is(400)).andReturn();

	    }

	    @Test
	    void testPublishServiceWithPostalCodeIsNullOrEmpty() throws Exception {
	        MessageRequest message = buildMessageRequestObject();
	        message.getAddress().setPostalCode(null);
	        MvcResult result = mockMvc
	                .perform(post("/customer-publish/v1/publish-message")
	                        .contentType(MediaType.APPLICATION_JSON).headers(buildHttpHeaders())
	                        .content(ObjectMapperUtil.returnJsonFromObject(message)))
	                .andExpect(status().is(400)).andReturn();

	    }

	    @Test
	    void testPublishServiceWithPostalCodeIsGretaterThanFiveSize() throws Exception {
	        MessageRequest message = buildMessageRequestObject();
	        message.getAddress().setPostalCode("534451");
	        MvcResult result = mockMvc
	                .perform(post("/customer-publish/v1/publish-message")
	                        .contentType(MediaType.APPLICATION_JSON).headers(buildHttpHeaders())
	                        .content(ObjectMapperUtil.returnJsonFromObject(message)))
	                .andExpect(status().is(400)).andReturn();

	    }

	    private HttpHeaders buildHttpHeaders() {
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Content-Type", "application/json");
	        headers.set("Authorization",
	                "Bearer eyJraWQiOiIzd2V5blJZX0VQSmxVdElna2h1ckNmUnVKbDJidGJCdkFq");
	        headers.set("Activity-Id", "Activity-Id");
	        headers.set("Transaction-Id", "Transaction-Id");
	        return headers;
	    }

	    private MessageRequest buildMessageRequestObject() {
	        MessageRequest messageRequest = new MessageRequest();
	        Address address = buildAddressObject();
	        messageRequest.setAddress(address);
	        messageRequest.setCountry("India");
	        messageRequest.setCountryCode("IN");
	        messageRequest.setBirthDate("27-12-1994");
	        messageRequest.setCustomerNumber("C234566667");
	        messageRequest.setCustomerStatus(CustomerStatusEnum.OPEN);
	        messageRequest.setEmail("m.pavan@gmail.com");
	        messageRequest.setFirstName("pavan kumar");
	        messageRequest.setLastName("mahankali");
	        messageRequest.setMobileNumber("9603335300");
	        return messageRequest;
	    }


	    private Address buildAddressObject() {
	        Address address = new Address();
	        address.setAddressLine1("lakkavaram");
	        address.setAddressLine2("west");
	        address.setPostalCode("534451");
	        address.setStreet("jungle");
	        return address;
	    }

	    private MessageProducerRequest buildMessageProducerRequest(MessageRequest messageRequest) {
	        MessageProducerRequest messageProducerRequest = new MessageProducerRequest();
	        messageProducerRequest.setAddress(buildMessageAddressRequest(messageRequest.getAddress()));
	        messageProducerRequest.setBirthDate(messageRequest.getBirthDate());
	        messageProducerRequest.setCountry(messageRequest.getCountry());
	        messageProducerRequest.setCountryCode(messageRequest.getCountryCode());
	        messageProducerRequest.setCustomerNumber(messageRequest.getCustomerNumber());
	        messageProducerRequest.setCustomerStatus(messageRequest.getCustomerStatus());
	        messageProducerRequest.setEmail(messageRequest.getEmail());
	        messageProducerRequest.setFirstName(messageRequest.getEmail());
	        messageProducerRequest.setLastName(messageRequest.getLastName());
	        messageProducerRequest.setMobileNumber(messageRequest.getMobileNumber());
	        return messageProducerRequest;
	    }

	    private AddressProducerRequest buildMessageAddressRequest(Address address) {
	        AddressProducerRequest addressProducerRequest = new AddressProducerRequest();
	        addressProducerRequest.setAddressLine1(address.getAddressLine1());
	        addressProducerRequest.setAddressLine2(address.getAddressLine2());
	        addressProducerRequest.setPostalCode(address.getPostalCode());
	        addressProducerRequest.setStreet(address.getStreet());
	        return addressProducerRequest;
	    }

	    private MessageResponse buildMessageResponse() {
	        MessageResponse response = new MessageResponse();
	        response.setData("Published Message sucessfully");
	        response.setStatus("success");
	        return response;
	    }

}
