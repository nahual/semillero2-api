package ar.edu.undav.semillero.domain.entity;

import org.junit.Assert;
import org.junit.Test;

/**
 * Juan Lagostena on 24/10/16
 * jlagostena@bitsense.com.ar
 * .
 */
public class GraduatedTest {

    @Test
    public void testCreation() {
        String nombre = "nombre";
        Graduated graduated = new Graduated(nombre);
        Assert.assertEquals(nombre, graduated.getName());
    }


    @Test
    public void testAddInterview() {
        Graduated graduated = new Graduated();
        Assert.assertTrue(graduated.getInterviews().isEmpty());

        graduated.addInterview(new Interview());
        Assert.assertEquals(1, graduated.getInterviews().size());
    }


}
