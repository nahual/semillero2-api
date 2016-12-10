package ar.edu.undav.semillero.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application.test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InterviewControllerTest {

	@Autowired
	protected WebApplicationContext wac;

	protected MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	// POST Tests
	@Test
	public void saveInterviewNotPost() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.put("/interview"))
				.andExpect(MockMvcResultMatchers.status().isMethodNotAllowed());
	}

	@Test
	public void saveInterviewPost() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/interview"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void saveInterviewPostParams() throws Exception {
		String graduatedId = "1";
		String companyId = "1";
		mockMvc.perform(MockMvcRequestBuilders.post("/interview").param("graduatedId", graduatedId).param("companyId",
				companyId)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}

	// GET Tests

	@Test
	public void getAllInterviews() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/interview"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}

	@Test
	public void getInterviewById() throws Exception {
		String id = "1";
		mockMvc.perform(MockMvcRequestBuilders.get("/interview/" + id))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

}