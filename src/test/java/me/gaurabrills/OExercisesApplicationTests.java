package me.gaurabrills;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import me.gaurabrills.order.OrderDetailsProcessor;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OExercisesApplicationTests {
	private static final String SUBSCRIBE_ENDPOINT = "/subscribe?{orderDetail}";
	@Autowired
	private MockMvc mvc;
	@Autowired
	private OrderDetailsProcessor processor;
	
	@Test
	/*
	 * truncate("123456789012345678901234567890", 25) Result:
	 * "12 ... (truncated) ... 90"
	 * 
	 */
	public void webtest() throws Exception {
		this.mvc.perform(get(SUBSCRIBE_ENDPOINT, "123456789012345678901234567890").//
				accept(MediaType.TEXT_PLAIN)).//
				andExpect(status().isOk()).//
				andExpect(content().string(equalTo("123456789012345678901234567890"))).//
				andDo(print());
	}

	@Test
	/*
	 * truncate("123456789012345678901234567890", 25) Result:
	 * "12 ... (truncated) ... 90"
	 * 
	 */
	public void issueMethodCallTest1() throws Exception {
		assertThat(processor.truncate("123456789012345678901234567890", 25), is(equalTo("12 ... (truncated) ... 90")));
		assertThat(processor.truncate("123456789012345678901234567890", 25).length(),
				is(equalTo(25)));
	}

	@Test
	/*
	 * truncate("1234567890", 5) Result: "1234567890"
	 * 
	 */
	public void issueMethodCallTest2() throws Exception {
		assertThat(processor.truncate("1234567890", 5), is(equalTo("1234567890")));
	}

	@Test
	/*
	 * truncate("123456789012345678901234567890", 31) Result:
	 * "123456789012345678901234567890"
	 * 
	 */
	public void issueMethodCallTest3() throws Exception {
		assertThat(processor.truncate("123456789012345678901234567890", 31),
				is(equalTo("123456789012345678901234567890")));
	}
	
	@Test
	public void issueMethodCallTest4() throws Exception {
		assertThat(processor.truncate("12", 1),
				is(equalTo("12")));
	}
	@Test
	public void issueMethodCallTest5() throws Exception {
		assertThat(processor.truncate("1234", 12),
				is(equalTo("1234")));
		assertThat(processor.truncate("1234", 12).length(),
				is(equalTo(4)));
	}
	
	@Test
	public void issueMethodCallTest6() throws Exception {
		assertThat(processor.truncate("123456789012345678901234567890", 23),
				is(equalTo("1 ... (truncated) ... 0")));
		assertThat(processor.truncate("123456789012345678901234567890", 23).length(),
				is(equalTo(23)));
	}
	
	@Test
	public void edgeScenario1() throws Exception {
		assertThat(processor.truncate("123456789012345678901234567890", 22),
				is(equalTo("1 ... (truncated) ... 0")));
		assertThat(processor.truncate("123456789012345678901234567890", 22).length(),
				is(equalTo(22)));
	}
	
	@Test
	public void edgeScenario2() throws Exception {
		assertThat(processor.truncate("123456789012345678901234567890", 24),
				is(equalTo("1 ... (truncated) ... 0")));
		assertThat(processor.truncate("123456789012345678901234567890", 24).length(),
				is(equalTo(24)));
	}
}
