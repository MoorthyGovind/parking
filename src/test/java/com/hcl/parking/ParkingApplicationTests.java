package com.hcl.parking;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class ParkingApplicationTests {

	@Test
    public void shouldLoadApplicationContext() {
    }
	
	 @Test
     public void applicationTest() {
		 ParkingApplication.main(new String[] {});
     }
}
