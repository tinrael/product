package example.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
	@GetMapping("/{id}")
	private ResponseEntity<String> findById() {
		return ResponseEntity.ok("{}");
	}
}
