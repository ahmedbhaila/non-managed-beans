package demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mycompany.app.NonManagedBeansApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NonManagedBeansApplication.class)
public class NonManagedBeansApplicationTests {

	@Test
	public void contextLoads() {
	}

}
