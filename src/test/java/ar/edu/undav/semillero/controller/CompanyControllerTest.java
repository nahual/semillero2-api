package ar.edu.undav.semillero.controller;

import ar.edu.undav.semillero.domain.entity.Company;
import ar.edu.undav.semillero.service.CompanyService;
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

import java.util.Collections;
import java.util.Optional;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CompanyController.class)
public class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CompanyService companyService;

    @After
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(companyService);
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
        Company company = new Company(name, contact);
        ReflectionTestUtils.setField(company, "id", 1L);
        Mockito.when(companyService.save(Mockito.anyString(), Mockito.anyString())).thenReturn(company);
        mockMvc.perform(MockMvcRequestBuilders.post("/company").param("name", name).param("contact", contact))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.notNullValue(Number.class)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is(name)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.contact", Matchers.is(contact)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.interviews", Matchers.empty()));
        Mockito.verify(companyService).save(Mockito.eq(name), Mockito.eq(contact));
    }

    // GET Tests

    @Test
    public void getAllCompanies() throws Exception {
        Mockito.when(companyService.findAll()).thenReturn(Collections.singletonList(new Company()));
        mockMvc.perform(MockMvcRequestBuilders.get("/company"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        Mockito.verify(companyService).findAll();
    }

    @Test
    public void getCompany() throws Exception {
        Mockito.when(companyService.findByName(Mockito.anyString())).thenReturn(Collections.singletonList(new Company()));
        mockMvc.perform(MockMvcRequestBuilders.get("/company").param("name", "ECORP"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        Mockito.verify(companyService).findByName(Mockito.eq("ECORP"));
    }

    @Test
    public void getCompanyWrongParam() throws Exception {
        Mockito.when(companyService.findByName(Mockito.anyString())).thenReturn(Collections.emptyList());
        mockMvc.perform(MockMvcRequestBuilders.get("/company").param("name", "dfghertyafdgaert"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string(""));
        Mockito.verify(companyService).findByName(Mockito.eq("dfghertyafdgaert"));
    }

    @Test
    public void getCompanyById() throws Exception {
        Mockito.when(companyService.findById(Mockito.anyLong())).thenReturn(Optional.of(new Company()));
        mockMvc.perform(MockMvcRequestBuilders.get("/company/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(companyService).findById(Mockito.eq(1L));
    }

    @Test
    public void getCompanyByIdNotFound() throws Exception {
        Mockito.when(companyService.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders.get("/company/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        Mockito.verify(companyService).findById(Mockito.eq(1L));
    }
}
