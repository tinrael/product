package example.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
	private final ProductRepository productRepository;

	private ProductController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@GetMapping("/{id}")
	public synchronized ResponseEntity<Product> findById(@PathVariable Long id) {
		Optional<Product> optional = productRepository.findById(id);

		if (optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		} else {
			Product product = productRepository.save(new Product(id, id + " name"));
			return ResponseEntity.ok(product);
		}
	}
}
