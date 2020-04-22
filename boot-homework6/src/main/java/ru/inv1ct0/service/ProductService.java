package ru.inv1ct0.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.inv1ct0.persist.Product;
import ru.inv1ct0.persist.ProductRepository;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public void save(Product product) {
        productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Page<Product> findAll(Pageable pageable) {
       return productRepository.findAll(pageable);
    }

    public Page<Product> findAllByPriceBetween(Optional<Integer> min, Optional<Integer> max, Pageable pageable) {
        if (min.isPresent() && max.isPresent()) {
            return productRepository.findAllByPriceBetween(min.get(), max.get(), pageable);
        }
        if (min.isPresent()) {
            return productRepository.findAllByPriceGreaterThanEqual(min.get(), pageable);
        }
        if (max.isPresent()) {
            return productRepository.findAllByPriceLessThanEqual(max.get(), pageable);
        }
        return productRepository.findAll(pageable);
    }
}
