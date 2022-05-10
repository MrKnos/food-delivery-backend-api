package com.example.delivery;

import com.example.delivery.models.Restaurant;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DeliveryApplicationTests {

	@Test
	void contextLoads() {
		final Restaurant restaurant = Restaurant.fromMock();
		System.out.printf(restaurant.toString());
	}

}
