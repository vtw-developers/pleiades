package com.vtw.portal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.vtw.portal.api.sample.Customer;
import com.vtw.portal.api.sample.CustomerRepository;

@SpringBootApplication
public class PortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortalApplication.class, args);
	}

    @Bean
    CommandLineRunner init(CustomerRepository userRepository) {
        return args -> {
            Customer user = new Customer("우태진", "vtw", "책임", "taejin.woo@vtw.co.kr", "010-7181-2602");
            userRepository.save(user);
            user = new Customer("홍길동", "행정안전부", "주무관", "hong@korea.kr", "010-1234-6666");
            userRepository.save(user);
            user = new Customer("유관순", "메타빌드", "수석", "31yoo@meta.co.kr", "010-7777-8888");
            userRepository.save(user);
            userRepository.findAll().forEach(System.out::println);
        };
    }
	
}
