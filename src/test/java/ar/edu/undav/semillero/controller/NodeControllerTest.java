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
public class NodeControllerTest {

	@Autowired
	protected WebApplicationContext wac;

	protected MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	// POST Tests
	@Test
	public void saveNodeNotPost() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.put("/node"))
				.andExpect(MockMvcResultMatchers.status().isMethodNotAllowed());
	}

	@Test
	public void saveNodePost() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/node")).andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void saveNodePostParams() throws Exception {
		String name = "Juan";
		String address = "sacacorcho 123";
		mockMvc.perform(MockMvcRequestBuilders.post("/node").param("name", name).param("address", address))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}

	// GET Tests

	@Test
	public void getAllNodes() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/node"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}
	
	public void getNotExistingNode() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/node/-1"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void getNodeById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/node").param("id", "1"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}

}