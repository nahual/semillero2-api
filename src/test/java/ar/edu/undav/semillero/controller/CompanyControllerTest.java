package ar.edu.undav.semillero.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application.test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompanyControllerTest {

	@Autowired
	protected WebApplicationContext wac;

	protected MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	// POST Tests
	@Test
	public void saveCompanyNotPost() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.put("/company"))
				.andExpect(MockMvcResultMatchers.status().isMethodNotAllowed());
	}

	@Test
	public void saveCompanyPost() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/company"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void saveCompanyPostParams() throws Exception {
		String name = "ECORP";
		String contact = "Carlos";
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/company").param("name", name).param("contact", contact))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andReturn();

		String responseBodyContent = result.getResponse().getContentAsString();

		System.out.println(responseBodyContent);

		Assert.assertEquals("{\"id\":1,\"name\":\"ECORP\",\"contact\":\"Carlos\",\"interviews\":[]}",
				responseBodyContent);
	}

	// GET Tests

	@Test
	public void getAllCompanies() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/company"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}

	@Test
	public void getCompany() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/company").param("name", "ECORP"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}

	@Test
	public void getCompanyById() throws Exception {
		String id = "1";
		mockMvc.perform(MockMvcRequestBuilders.get("/company/" + id))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}

}