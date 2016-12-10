package ar.edu.undav.semillero.domain.entity;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class InterviewTest {

	@Test
	public void testCreation() {
		Graduated graduated = new Graduated();
		Company company = new Company();
		Date date = new Date();
		String comments = "ok";
		Interview interview = new Interview(graduated, company, date, comments);
		Assert.assertEquals(graduated, interview.getGraduated());
		Assert.assertEquals(company, interview.getCompany());
		Assert.assertEquals(date, interview.getDate());
		Assert.assertEquals(comments, interview.getComments());
	}

}
