package com.thevotes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thevotes.domain.Product;
import com.thevotes.domain.User;

public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByUser(User user);
}
