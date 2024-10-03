package example.product;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductApplicationTests {
	@Autowired
	TestRestTemplate testRestTemplate;
	
	@Test
	void shouldReturnAProduct() {
		ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("/products/15", String.class);
		
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	void contextLoads() {
	}

}
