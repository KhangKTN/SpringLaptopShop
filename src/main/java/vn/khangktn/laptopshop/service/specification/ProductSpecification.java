package vn.khangktn.laptopshop.service.specification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

    public static final Map<String, float[]> SCREEN_LIST = new HashMap<>(){{
        put("duoi-14-inch", new float[]{0, 13.9f});
        put("14-15-inch", new float[]{14, 15});
        put("15-17-inch", new float[]{15, 17});
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

        return (root, query, builder) -> builder.between(root.get(Product_.PRICE), minPrice, maxPrice);
    }

    public Specification<Product> productCpuIn(List<String> cpuList) {
        return (root, query, builder) -> builder.in(root.get(Product_.CPU_TYPE)).value(cpuList);
    }

    public Specification<Product> productVgaIn(List<String> vgaList) {
        return (root, query, builder) -> builder.in(root.get(Product_.VGA_TYPE)).value(vgaList);
    }

    public Specification<Product> productRam(List<String> ramList) {
        return (root, query, builder) -> builder.in(root.get(Product_.RAM)).value(getListRamInt(ramList));
    }

    public Specification<Product> productScreen(String screenUrl) {
        float[] screenArr = getScreenSizeRange(screenUrl);
        return (root, query, builder) -> builder.between(root.get(Product_.SCREEN), screenArr[0], screenArr[1]);
    }

    private long[] getPriceRange(String priceUrl) {
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

    private List<Integer> getListRamInt(List<String> ramList) {
        List<Integer> ramListInt = new ArrayList<>();
        for (String ram : ramList) {
            String ramString = ram.split("-")[0];
            try {
                ramListInt.add(Integer.parseInt(ramString));
            } catch (Exception e) {
                ramListInt.add(0);
            }
        }
        return ramListInt;
    }

    private float[] getScreenSizeRange(String screen) {
        float[] screenArr = new float[]{0, 20};
        if (SCREEN_LIST.containsKey(screen)) {
            screenArr = SCREEN_LIST.get(screen);
        }
        return screenArr;
    }
}
