package com.example.customerservice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.eatza.customerservice.controller.CustomerController;
import com.eatza.customerservice.model.Customer;
import com.eatza.customerservice.service.customerservice.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
//@ExtendWith(MockitoExtension.class)
@WebMvcTest(value = CustomerController.class)
//@ExtendWith(SpringExtension.class)
//@AutoConfigureMockMvc
//@SpringBootTest(classes = CustomerController.class)
public class CustomerControllerTest {
	// Customer customer = mock(Customer.class);

	final String api = "/customer/api/v1/";
//	@InjectMocks
//	private CustomerController controller=new CustomerController();
	@Autowired
	private MockMvc mockMvc;
//    @BeforeEach
//    public void init() {
//        mockMvc = MockMvcBuilders.standaloneSetup(this.controller).build();
//    }
    
	@MockBean
	private CustomerService customerService;

	@Autowired
	private ObjectMapper objmapper;
	
	private static Customer cust = null;

	@BeforeAll
	public static void getCustomerOBj() {
		cust = new Customer();
		cust.setId(12L);
		cust.setEmail("abc@gmail.com");
		cust.setName("Xyz");
		cust.setPhoneNumber("457963155");
		cust.setPassword("1234");
	}

	@Test
	//@DisplayName("Add Customer")
	public void Customer() throws Exception {
		when(customerService.addCustomer(any(Customer.class))).thenReturn(cust);

		RequestBuilder req = MockMvcRequestBuilders.post("/customer/api/v1/add-customer")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objmapper.writeValueAsString(cust));
				

		 mockMvc.perform(req).andExpect(status().is(200)).andReturn();
		//System.err.println(response.toString());
	}

//	@Test
//	@DisplayName("Get Customer By Id")
//	public void getCustomerById() throws Exception {
//		when(customerService.getCustomerById(anyLong())).thenReturn(cust);
//
//		RequestBuilder req = MockMvcRequestBuilders.get("/customer/api/v1/get-customer-by-id?id=12")
//				.content(objmapper.writeValueAsString(cust));
//		mockMvc.perform(req).andExpect(status().is(200)).andReturn();
//	 mockMvc.perform(get("/customer/api/v1/get-customer-by-id?id=12").accept(MediaType.APPLICATION_JSON).content(objmapper.writeValueAsString(cust)))
//		 .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//		 .andDo(print())
//	        .andExpect(status().isOk())
//	       .andReturn();
//		
//	}
}
