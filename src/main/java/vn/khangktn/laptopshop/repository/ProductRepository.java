package vn.khangktn.laptopshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import vn.khangktn.laptopshop.domain.Product;
import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    Product findBySlug(String slug);

    @NonNull
    Page<Product> findAll(@NonNull Pageable pageable);

    @NonNull
    Page<Product> findAll(@NonNull Specification<Product> specs, @NonNull Pageable pageable);
}
