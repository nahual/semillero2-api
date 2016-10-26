package ar.edu.undav.semillero.controller;

import ar.edu.undav.semillero.domain.repository.GraduatedRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

/**
 * Juan Lagostena on 24/10/16
 * jlagostena@bitsense.com.ar
 * .
 */
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application.test.properties")
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GraduatedControllerTest {

    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void saveGraduatedNotPost() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .put("/graduado")).andExpect(MockMvcResultMatchers.status().isMethodNotAllowed());
    }

    @Test
    public void saveGraduatedPost() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/graduado")).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

}