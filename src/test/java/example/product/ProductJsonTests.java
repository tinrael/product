package example.product;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

@JsonTest
public class ProductJsonTests {
	@Autowired
	private JacksonTester<Product> jacksonTester;
	
	@Test
	void serializationTest() throws IOException {
		Product product = new Product(15L, "Pineapple");
		JsonContent<Product> jsonContent = jacksonTester.write(product);
		
		assertThat(jsonContent).isStrictlyEqualToJson("expected.json");
		
		assertThat(jsonContent).hasJsonPathNumberValue("@.id");
		assertThat(jsonContent).extractingJsonPathNumberValue("@.id").isEqualTo(15);
		
		assertThat(jsonContent).hasJsonPathStringValue("@.name");
		assertThat(jsonContent).extractingJsonPathStringValue("@.name").isEqualTo("Pineapple");
	}
	
	@Test
	void deserializationTest() throws IOException {
		String jsonString = """
				{
					"id": 15,
					"name": "Pineapple"
				}
				""";
		Product product = jacksonTester.parseObject(jsonString);
		
		assertThat(product).isEqualTo(new Product(15L, "Pineapple"));
	}
}
