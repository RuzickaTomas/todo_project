package todo_project;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"**/WebMVCConfiguration.java"})
public class SetupTest {


	@Test
	public void helloTest() {
		Assert.notNull("Hello World", "String is not null");
	}

}
