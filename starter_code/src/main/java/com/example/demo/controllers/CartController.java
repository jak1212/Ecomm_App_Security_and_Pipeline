package com.example.demo.controllers;

import java.util.Optional;
import java.util.stream.IntStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.AppUser;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.ItemRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.ModifyCartRequest;

@RestController
@RequestMapping("/api/cart")
public class CartController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@PostMapping("/addToCart")
	public ResponseEntity<Cart> addTocart(@RequestBody ModifyCartRequest request) {
		AppUser user = userRepository.findByUsername(request.getUsername());
		if(user == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		Optional<Item> item = itemRepository.findById(request.getItemId());
		if(!item.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		Cart cart = user.getCart();
		IntStream.range(0, request.getQuantity())
			.forEach(i -> cart.addItem(item.get()));
		cartRepository.save(cart);
		return ResponseEntity.ok(cart);
	}
	
	@PostMapping("/removeFromCart")
	public ResponseEntity<Cart> removeFromcart(@RequestBody ModifyCartRequest request) {
		AppUser user = userRepository.findByUsername(request.getUsername());
		if(user == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		Optional<Item> item = itemRepository.findById(request.getItemId());
		if(!item.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		Cart cart = user.getCart();
		IntStream.range(0, request.getQuantity())
			.forEach(i -> cart.removeItem(item.get()));
		cartRepository.save(cart);
		return ResponseEntity.ok(cart);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getCartById(@PathVariable Long id) {
		Optional<Cart> cart = cartRepository.findById(id);
		return ResponseEntity.ok(cart.get());
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<?> getCartByUserId(@PathVariable Long id) {
		Optional<AppUser> user = userRepository.findById(id);
		Cart cart = null;
		if(user.isPresent()) {
			cart = user.get().getCart();
		}
		return ResponseEntity.ok(cart);
	}

	@GetMapping("/list")
	public ResponseEntity<?> getCarts() {
		List<Cart> carts = cartRepository.findAll();
		return ResponseEntity.ok(carts);
	}
		
}
