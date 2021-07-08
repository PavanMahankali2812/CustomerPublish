package co.pkglobal.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import com.pkglobal.converter.DefaultMessageRequestConverter;
import com.pkglobal.converter.DefaultMessageRequestMaskConverter;
import com.pkglobal.model.Address;
import com.pkglobal.model.CustomerStatusEnum;
import com.pkglobal.model.MessageRequest;
import com.pkglobal.model.MessageResponse;
import com.pkglobal.service.DefaultCustomerPublishService;

@ExtendWith(MockitoExtension.class)
class DefaultCustomerPublishServiceTest {

	@InjectMocks
	private DefaultCustomerPublishService defaultPublishService;

	@Mock
	private KafkaTemplate<String, Object> kafkaTemplate;

	@Mock
	private DefaultMessageRequestConverter messageRequestConverter;

	@Mock
	private DefaultMessageRequestMaskConverter messageRequestMaskConverter;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testPublishMessageWithSuccess() {
		MessageRequest messageRequest = buildMessageRequestObject();
		MessageResponse result = defaultPublishService.publishMessage(messageRequest);
		assertNotNull(result);
		assertEquals(buildMessageResponse(), result);
	}

	private MessageRequest buildMessageRequestObject() {
		MessageRequest messageRequest = new MessageRequest();
		Address address = buildAddressObject();
		messageRequest.setAddress(address);
		messageRequest.setCountry("India");
		messageRequest.setCountryCode("IN");
		messageRequest.setCustomerNumber("CUST123456");
		messageRequest.setCustomerStatus(CustomerStatusEnum.OPEN);
		messageRequest.setEmail("Pavan@gmail.com");
		messageRequest.setFirstName("pavan pavan");
		messageRequest.setLastName("kumar kumar");
		messageRequest.setBirthDate("12-02-2020");
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

	private MessageResponse buildMessageResponse() {
		MessageResponse response = new MessageResponse();
		response.setData("Published Message sucessfully");
		response.setStatus("success");
		return response;
	}
}
