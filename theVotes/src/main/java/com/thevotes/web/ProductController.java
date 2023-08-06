package com.thevotes.web;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.thevotes.domain.Product;
import com.thevotes.domain.User;
import com.thevotes.repositories.ProductRepository;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ProductController {

	@Autowired
	private ProductRepository productRepo;
	

	@GetMapping("products/{productId}")
	public String getProduct(@AuthenticationPrincipal User user, @PathVariable Long productId, ModelMap model, HttpServletResponse response) throws IOException{
		Optional<Product> productOpt = productRepo.findById(productId);

		if(productOpt.isPresent()) {
			Product product = productOpt.get();
			if(user.getId() == product.getUser().getId()) {
				model.put("product", product);
			}else {
				response.sendError(HttpStatus.NOT_FOUND.value(), "You are not authorized to access this page");
				return "products";
			}
		}else {
			response.sendError(HttpStatus.NOT_FOUND.value(), "Product with id "+productId + " was not found");
			return "products";
		}

		return "products";
	}

	@PostMapping("/products/{productId}")
	public String saveProduct(@AuthenticationPrincipal User user, @PathVariable long productId, Product product,  HttpServletResponse response) throws IOException {
		

		System.out.println(product);
		
		if(user.getId() == product.getUser().getId()) {
			product = productRepo.save(product);
		}else {
			response.sendError(HttpStatus.NOT_FOUND.value(), "You are not authorized to perform this action");
			return "products";
		}
		return "redirect:/products/"+product.getId() ;
	}

	@PostMapping("/products")
	public String postProducts(@AuthenticationPrincipal User user) {
		Product product = new Product();

		product.setPublished(false);
		product.setUser(user);

		product = productRepo.save(product);


		return "redirect:/products/"+product.getId();
	}
}
