package ar.edu.undav.semillero.domain.entity;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class InterviewTest {

	@Test
	public void testCreation() {
		Student graduated = new Student();
		Company company = new Company();
		String comments = "ok";
        Interview interview = new Interview(graduated, company, comments);
		Assert.assertEquals(graduated, interview.getGraduated());
		Assert.assertEquals(company, interview.getCompany());
        Assert.assertEquals(LocalDate.now(), interview.getDate());
		Assert.assertEquals(comments, interview.getComments());
	}
}
