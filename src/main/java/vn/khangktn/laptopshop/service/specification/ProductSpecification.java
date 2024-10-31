package vn.khangktn.laptopshop.service.specification;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import vn.khangktn.laptopshop.domain.Product_;
import vn.khangktn.laptopshop.domain.Product;

public class ProductSpecification {
    public static Specification<Product> productNameContain(String name) {
        return (root, query, builder) -> builder.like(root.get(Product_.NAME), "%" + name + "%");
    }

    public static Specification<Product> productFactoryIn(List<String> factoryList) {
        return (root, query, builder) -> builder.in(root.get(Product_.FACTORY)).value(factoryList);
    }

    public static Specification<Product> productPriceRange(long minPrice, long maxPrice) {
        return (root, query, builder) -> builder.between(root.get(Product_.PRICE), minPrice, maxPrice);
    }
}
