package ru.inv1ct0.persist;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByproductName(String productName);

    @Query("from Product p where p.productName = :productName")
    List<Product> filterProductByName(@Param("productName") String productName);

    Page<Product> findAllByPriceBetween(Integer min, Integer max, Pageable pageable);

    Page<Product> findAllByPriceGreaterThanEqual(Integer min, Pageable pageable);

    Page<Product> findAllByPriceLessThanEqual(Integer max, Pageable pageable);
}
