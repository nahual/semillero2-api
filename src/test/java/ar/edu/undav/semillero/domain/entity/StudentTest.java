package ar.edu.undav.semillero.domain.entity;

import org.junit.Assert;
import org.junit.Test;

public class StudentTest {

	@Test
	public void testCreation() {
		String nombre = "nombre";
		Node node = new Node();
        Student graduated = new Student(nombre, node); // Actualizar
																		// Assert
		Assert.assertEquals(nombre, graduated.getName());
		Assert.assertEquals(false, graduated.isDeleted());
	}

	@Test
	public void testAddInterview() {
		Student graduated = new Student();
		Assert.assertTrue(graduated.getInterviews().isEmpty());

		graduated.addInterview(new Interview());
		Assert.assertEquals(1, graduated.getInterviews().size());
	}
}
