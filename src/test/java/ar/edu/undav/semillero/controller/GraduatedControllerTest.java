package ar.edu.undav.semillero.controller;

import ar.edu.undav.semillero.domain.entity.Graduated;
import ar.edu.undav.semillero.domain.entity.Node;
import ar.edu.undav.semillero.request.CreateGraduatedRequest;
import ar.edu.undav.semillero.service.GraduatedService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Optional;

@RunWith(SpringRunner.class)
@WebMvcTest(value = GraduatedController.class)
public class GraduatedControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private GraduatedService graduatedService;

    @After
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(graduatedService);
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
        long nodeId = 1001;
        CreateGraduatedRequest request = new CreateGraduatedRequest(name, nodeId, null,null);
        Graduated graduated = new Graduated(name, new Node());
        ReflectionTestUtils.setField(graduated, "id", 1L);
        Mockito.when(graduatedService.save(Mockito.any(CreateGraduatedRequest.class))).thenReturn(graduated);
        mockMvc.perform(MockMvcRequestBuilders.post("/graduated").contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.notNullValue(Number.class)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is(name)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.interviews", Matchers.empty()));
        Mockito.verify(graduatedService).save(Mockito.eq(request));
    }

    @Test
    public void saveGraduatedPostParamsInvalid() throws Exception {
        CreateGraduatedRequest request = new CreateGraduatedRequest("", null,null,null);
        mockMvc.perform(MockMvcRequestBuilders.post("/graduated").contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    // GET Tests

    @Test
    public void getAllGraduated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/graduated"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(graduatedService).findAll();
    }

    @Test
    public void getAllGraduatedByNode() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/graduated").param("node", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(graduatedService).findByNode(Mockito.eq(1L));
    }

    @Test
    public void getAllGraduatedByDate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/graduated").param("when", "2018-04-02"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(graduatedService).findByDate(Mockito.eq(LocalDate.of(2018, 4, 2)));
    }

    @Test
    public void getGraduated() throws Exception {
        Mockito.when(graduatedService.findById(Mockito.anyLong())).thenReturn(Optional.of(new Graduated()));
        mockMvc.perform(MockMvcRequestBuilders.get("/graduated/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(graduatedService).findById(Mockito.eq(1L));
    }
}
