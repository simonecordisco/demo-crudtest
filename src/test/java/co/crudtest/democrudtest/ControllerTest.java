package co.crudtest.democrudtest;

import co.crudtest.democrudtest.controller.StudentController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;


import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.containsString;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) /*fa girare ogni volta il programma su una porta random */
@ActiveProfiles(value ="test")
@AutoConfigureMockMvc /* MockMvc motore che gestisce i test, testare intero giro */
class ControllerTest {
	@LocalServerPort/* springboot va a iniettare all'interno della variabile sotto il numero della port */
	private int port;
	@Autowired
	private MockMvc mvc;
	@Autowired
	private StudentController controller;
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoad() {
		assertThat(controller).isNotNull();
	}



    @Test
	void restTemplateTest(){
	String output = this.restTemplate.getForObject("http://127.0.0.1:" + port + "/check",String.class);
	assertThat(output).contains("checkTest");

		}

	@Test
	public void testMvc() throws Exception {
		this.mvc.perform(get("/check"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("checkTest")));
	}





	}



