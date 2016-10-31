package ar.edu.undav.semillero.domain.entity;

import org.junit.Assert;
import org.junit.Test;


public class CompanyTest {

    @Test
    public void testCreation() {
        String nombre = "nombre";
        String contacto = "contacto";
        Company company = new Company(nombre,contacto);
        Assert.assertEquals(nombre, company.getName());
        Assert.assertEquals(contacto, company.getContact());
    }


    @Test
    public void testAddInterview() {
    	Company company = new Company();
        Assert.assertTrue(company.getInterviews().isEmpty());

        company.addInterview(new Interview());
        Assert.assertEquals(1, company.getInterviews().size());
    }


}
