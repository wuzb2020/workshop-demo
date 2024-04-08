//package com.educandoweb.course.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//
//import com.educandoweb.course.mapper.OrderRepository;
//import com.educandoweb.course.mapper.UserRepository;
//
//@Configuration
//@Profile("test")
//public class TestConfig implements CommandLineRunner {
//
//	@Autowired
//	private UserRepository userRepository;
//
//	@Autowired
//	private OrderRepository orderRepository;
//
//	@Override
//	public void run(String... args) throws Exception {
//
////		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
////		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
////
////		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, 1L);
////		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, 1L);
////		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, 1L);
////
////		userRepository.saveAll(Arrays.asList(u1, u2));
////		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
//	}
//}
