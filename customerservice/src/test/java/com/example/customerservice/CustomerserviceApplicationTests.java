package com.example.customerservice;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.eatza.customerservice.controller.CustomerController;
import com.eatza.customerservice.model.Customer;
import com.eatza.customerservice.repository.CustomerRepository;
import com.eatza.customerservice.service.customerservice.CustomerService;
import com.eatza.customerservice.service.customerservice.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
@AutoConfigureMockMvc
@WebAppConfiguration
@ComponentScan(basePackages = " com.eatza.customerservice")
//@ContextConfiguration(value  = "com.example.customerservice.CustomerController")
class CustomerserviceApplicationTests {
	final String api = "/customer/api/v1/";
	@InjectMocks
	private CustomerController controller=new CustomerController();
	private MockMvc mockMvc;
	@InjectMocks
	private CustomerService customerService;
	
	@MockBean
	private CustomerRepository custRepository;

	private ObjectMapper objmapper=new ObjectMapper();

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
  @BeforeEach
  public void init() {
      mockMvc = MockMvcBuilders.standaloneSetup(this.controller).build();
  }
  
    @Test
    public void contextLoads() throws Exception {
   	 mockMvc.perform(get("/customer/api/v1/get-customer-by-id?id=12"));
	}
    @Test
	@DisplayName("Get Customer By Id")
	public void getCustomerById() throws Exception {
		when(customerService.getCustomerById(anyLong())).thenReturn(cust);

//		RequestBuilder req = MockMvcRequestBuilders.get("/customer/api/v1/get-customer-by-id?id=12")
//				.content(objmapper.writeValueAsString(cust));
		//mockMvc.perform(req).andExpect(status().is(200)).andReturn();
	 mockMvc.perform(get("/customer/api/v1/get-customer-by-id?id=12").accept(MediaType.APPLICATION_JSON).content(objmapper.writeValueAsString(cust)))
		 .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		 .andDo(print())
	        .andExpect(status().isOk())
	       .andReturn();
		
	}
}
