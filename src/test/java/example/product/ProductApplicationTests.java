package example.product;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductApplicationTests {
	@Autowired
	TestRestTemplate testRestTemplate;
	
	@Test
	void shouldReturnAProduct() {
		ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("/products/15", String.class);
		
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		
		DocumentContext documentContext = JsonPath.parse(responseEntity.getBody());
		Number id = documentContext.read("@.id");
		String name = documentContext.read("@.name");
		
		assertThat(id).isEqualTo(15);
		assertThat(name).isEqualTo("15 name");
	}
	
	@Test
	void contextLoads() {
	}

}
