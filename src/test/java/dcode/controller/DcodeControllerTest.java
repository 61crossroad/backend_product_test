package dcode.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment=WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class DcodeControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testGetProducts() throws Exception {
		mockMvc.perform(get("/dcode/products"))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testGetProductDetail() throws Exception {
		mockMvc.perform(get("/dcode/products/4l3l2l"))
				.andExpect(status().isOk());
	}
}
