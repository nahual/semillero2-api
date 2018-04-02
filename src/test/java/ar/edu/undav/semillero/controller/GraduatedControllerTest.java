package ar.edu.undav.semillero.controller;

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
public class GraduatedControllerTest {

	@Autowired
	protected WebApplicationContext wac;

	protected MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	// POST Tests
	@Test
	public void saveGraduatedNotPost() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.put("/graduated"))
				.andExpect(MockMvcResultMatchers.status().isMethodNotAllowed());
	}

	@Test
	public void saveGraduatedPost() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/graduated"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void saveGraduatedPostParams() throws Exception {
		String name = "Juan";
		String nodeid = "1001";
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/graduated").param("name", name).param("node", nodeid))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andReturn();

		String responseBodyContent = result.getResponse().getContentAsString();

		System.out.println(responseBodyContent);

		// Assert.assertEquals("{\"id\":1,\"name\":\"Juan\",\"deleted\":false,\"interviews\":[],\"node\":null}",
		// responseBodyContent);

	}

	// GET Tests

	@Test
	public void getAllGraduated() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/graduated"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}

	@Test
	public void getGraduated() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/graduated").param("id", "1"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}



}