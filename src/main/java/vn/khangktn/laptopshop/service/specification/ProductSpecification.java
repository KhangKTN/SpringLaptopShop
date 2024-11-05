package vn.khangktn.laptopshop.service.specification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import vn.khangktn.laptopshop.domain.Product;
import vn.khangktn.laptopshop.domain.Product_;

@Service
public class ProductSpecification {
    private final Map<String, long[]> PRICE_LIST = new HashMap<>() {{
        put("duoi-10-trieu", new long[]{0, 10});
        put("10-15-trieu", new long[]{10, 15});
        put("15-20-trieu", new long[]{15, 20});
        put("tren-20-trieu", new long[]{20, 9999});
    }};

    private final int DEFAULT_MILION = 1000000;

    public Specification<Product> productNameContain(String name) {
        return (root, query, builder) -> builder.like(root.get(Product_.NAME), "%" + name + "%");
    }

    public Specification<Product> productBrandIn(List<String> factoryList) {
        return (root, query, builder) -> builder.in(root.get(Product_.FACTORY)).value(factoryList);
    }

    public Specification<Product> productTargetIn(List<String> targetList) {
        return (root, query, builder) -> builder.in(root.get(Product_.TARGET)).value(targetList);
    }

    public Specification<Product> productPriceRange(String priceUrl) {
        long[] priceRange = getPriceRange(priceUrl);
        long minPrice = priceRange[0] * DEFAULT_MILION;
        long maxPrice = priceRange[1] * DEFAULT_MILION;
        System.out.println("MIN_PRICE: " + minPrice);

        return (root, query, builder) -> builder.between(root.get(Product_.PRICE), minPrice, maxPrice);
    }

    public long[] getPriceRange(String priceUrl) {
        long[] priceRange = new long[2];
        int minPriceIdx = 0;
        int maxPriceIdx = 1;

        if (PRICE_LIST.containsKey(priceUrl)) {
            long[] priceValue = PRICE_LIST.get(priceUrl);
            priceRange[minPriceIdx] = priceValue[minPriceIdx];
            priceRange[maxPriceIdx] = priceValue[maxPriceIdx];
        }
        return priceRange;
    }
}
