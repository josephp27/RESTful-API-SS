package com.solstice.api;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public class SolsticeApiApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void IllegalGetTest() {
		String body = this.restTemplate.getForObject("/", String.class);
		assertThat(body).contains("Not Found");
	}

	@Test
	public void IllegalUpdateTest() {
		String body = this.restTemplate.getForObject("/update?name=Joseph%20Procopio&email=josephp27@live.com"
				+ "&work=+1(630)935-3735&personal=6309353735&company=walmart&bd=11/27/1992&street=48+S+3rd"
						+ "&state=IL&city=Chicago", String.class);
		assertThat(body).contains("Cannot update a record that does not exist!");
	}

	@Test
	public void DoubleSameAddReturnError() {
		String body = this.restTemplate.getForObject("/add?name=Joseph%20Procopio&email=josephp27@live.com&work=+1(630)935-3735&personal=6309353735&company=walmart&bd=11/27/1992&street=48+S+3rd&state=IL&city=Chicago", String.class);
		body = this.restTemplate.getForObject("/add?name=Joseph%20Procopio&email=josephp27@live.com&work=+1(630)935-3735&personal=6309353735&company=walmart&bd=11/27/1992&street=48+S+3rd&state=IL&city=Chicago", String.class);
		assertThat(body).contains("That email is already registered to a user!");
	}

	@Test
	public void DoubleAddSameEmailDifferentParametersReturnsError() {
		String body = this.restTemplate.getForObject("/add?name=Joseph%20Procopio&email=josephp27@live.com&work=+1(630)935-3735&personal=6309353735&company=walmart&bd=11/27/1992&street=48+S+3rd&state=IL&city=Chicago", String.class);
		body = this.restTemplate.getForObject("/add?name=Johnny%20Price&email=josephp27@live.com&work=+1(630)935-0235", String.class);
		assertThat(body).contains("That email is already registered to a user!");
	}

	@Test
	public void AddingAnEmailThenDeletingAnEmailRemovesFromDatabase(){
		this.restTemplate.getForObject("/add?name=Joseph%20Procopio&email=josephp27@live.com&work=+1(630)935-3735&personal=6309353735&company=walmart&bd=11/27/1992&street=48+S+3rd&state=IL&city=Chicago", String.class);
		this.restTemplate.getForObject("/delete?email=josephp27@live.com", String.class);
		String body = this.restTemplate.getForObject("/get", String.class);
		assertThat(body).doesNotContain("josephp27@live.com");

	}

	@Test
	public void GetUserBasedOnEmail(){
		this.restTemplate.getForObject("/add?name=Joseph%20Procopio&email=josephp27@live.com&work=+1(630)935-3735&personal=6309353735&company=walmart&bd=11/27/1992&street=48+S+3rd&state=IL&city=Chicago", String.class);
		String body = this.restTemplate.getForObject("/get?email=josephp27@live.com", String.class);
		assertThat(body).contains("josephp27@live.com");
	}

	@Test
	public void GetUserBasedOnEmailReturnsErrorWhenNoUserInDatabase(){
		String body = this.restTemplate.getForObject("/get?email=josephp27@live.com", String.class);
		assertThat(body).contains("That email is not registered to anyone");
	}

	@Test
	public void GetUserBasedOnWorkPhone(){
		this.restTemplate.getForObject("/add?name=Joseph%20Procopio&email=josephp27@live.com&work=+1(630)935-3735&personal=6309353735&company=walmart&bd=11/27/1992&street=48+S+3rd&state=IL&city=Chicago", String.class);
		String body = this.restTemplate.getForObject("/get?work_number=16309353735", String.class);
		assertThat(body).contains("16309353735");
	}

	@Test
	public void GetUserBasedOnWorkPhoneFailsWhenNotInDatabase(){
		String body = this.restTemplate.getForObject("/get?work_number=16309353735", String.class);
		assertThat(body).contains("That number is not registered to anyone!");

	}

}