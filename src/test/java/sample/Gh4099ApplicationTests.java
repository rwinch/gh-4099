package sample;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

import javax.servlet.Filter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Gh4099Application.class)
@WebAppConfiguration
public class Gh4099ApplicationTests {
	@Autowired
	WebApplicationContext wac;
	@Autowired
	Filter springSecurityFilterChain;

	MockMvc mockMvc;

	@Test
	public void hsts() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac)
				.addFilters(springSecurityFilterChain)
				.build();

		mockMvc.perform(get("/").secure(true))
			.andExpect(header().doesNotExist("Strict-Transport-Security"));
	}

}
