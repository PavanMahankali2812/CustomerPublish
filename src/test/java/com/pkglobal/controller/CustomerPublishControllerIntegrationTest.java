package com.pkglobal.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.common.util.JacksonJsonParser;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.pkglobal.model.Address;
import com.pkglobal.model.CustomerStatusEnum;
import com.pkglobal.model.MessageRequest;
import com.pkglobal.util.*;

@Tag("integration")
@SpringBootTest
 class CustomerPublishControllerIntegrationTest {
	private MockMvc mockMvc;
	@Autowired
	private FilterChainProxy springSecurityFilterChain;
	@Autowired
	private WebApplicationContext webApplicationContext;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.apply(springSecurity(springSecurityFilterChain)).build();

	}

	@Test
	 void testAuthorizationWhenPassingValidToken() throws Exception {
		String accessToken = obtainAccessToken("client", "secret");
		mockMvc.perform(post("/customer-publish/v1/publish-message").contentType(MediaType.APPLICATION_JSON)
				.header("Activity-Id", "demo").header("Authorization", "Bearer " + accessToken)
				.header("Transaction-Id", "unit")
				.content(ObjectMapperUtil.returnJsonFromObject(buildCustomerRequest()))).andExpect(status().isOk());
	}

	@Test
	 void testAuthorizationWhenPassingInValidToken() throws Exception {
		String accessToken = obtainAccessToken("client", "secret");
		mockMvc.perform(post("/customer-publish/v1/publish-message").contentType(MediaType.APPLICATION_JSON)
				.header("Activity-Id", "demo").header("Authorization", "Bearer " + accessToken + " invalid token")
				.header("Transaction-Id", "unit")
				.content(ObjectMapperUtil.returnJsonFromObject(buildCustomerRequest())))
				.andExpect(status().isUnauthorized());
	}

	private String obtainAccessToken(String username, String password) throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "password");
		params.add("username", username);
		params.add("password", password);
		ResultActions result = mockMvc.perform(post("/oauth/token").params(params).with(httpBasic(username, password))
				.accept("application/json;charset=UTF-8")).andExpect(status().isOk());
		String resultString = result.andReturn().getResponse().getContentAsString();

		JacksonJsonParser jsonParser = new JacksonJsonParser();
		return jsonParser.parseMap(resultString).get("access_token").toString();
	}

	private MessageRequest buildCustomerRequest() {
		MessageRequest customerRequest = new MessageRequest();
		customerRequest.setCustomerNumber("UBC2312");
		customerRequest.setFirstName("unnnnnitesting");
		customerRequest.setLastName("demoeeeexample");
		customerRequest.setEmail("abcdefghi@gmail.com");
		customerRequest.setCustomerStatus(CustomerStatusEnum.OPEN);
		customerRequest.setCountry("india");
		customerRequest.setBirthDate("12-02-2020");
		customerRequest.setCountryCode("IN");
		customerRequest.mobileNumber("9999999999");
		Address customerAddress = new Address();
		customerAddress.setAddressLine1("Demoline1");
		customerAddress.setAddressLine2("Demo AddressLine2");
		customerAddress.setStreet("James Street");
		customerAddress.setPostalCode("50049");
		customerRequest.setAddress(customerAddress);
		return customerRequest;
	}
}
