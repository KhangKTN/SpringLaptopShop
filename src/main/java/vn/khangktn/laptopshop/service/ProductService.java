package vn.khangktn.laptopshop.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import vn.khangktn.laptopshop.domain.Product;
import vn.khangktn.laptopshop.repository.ProductRepository;
import vn.khangktn.laptopshop.service.specification.ProductSpecification;

@Service
public class ProductService {
    @Autowired ProductRepository productRepository;
    @Autowired UploadService uploadService;

    public boolean createProduct(Product product, MultipartFile file){
        if(!file.isEmpty()){
            product.setImage(uploadService.handleSaveUploadFile(file, "product"));
        }
        return productRepository.save(product).getId() > 0;
    }

    public Page<Product> getAllProduct(int pageNumber){
        if(pageNumber == -1) pageNumber = 1;
        int pageSize = 10;
        // Sort sort = Sort.by(Direction.fromString("asc"), "factory");
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);

        return productRepository.findAll(pageable);
    }

    public Page<Product> getAllProductCondition(int pageNumber, String factoryString) {
        if(pageNumber == -1) pageNumber = 1;
        int pageSize = 10;
        // Sort sort = Sort.by(Direction.fromString("asc"), "factory");
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);

        // if (name == null) {
        //     name = "";
        // }
        // long minPrice = 0;
        // long maxPrice = 0;
        // if(priceRange == null) {
        //     maxPrice = 99999999999L;
        // } else {
        //     if("10-toi-15-trieu".equals(priceRange)) {
        //         minPrice = 10;
        //         maxPrice = 15;
        //     } else if("15-toi-20-trieu".equals(priceRange)) {
        //         minPrice = 15;
        //         maxPrice = 20;
        //     } else {
        //         minPrice = maxPrice = 0;
        //     }

        //     minPrice *= 1000000;
        //     maxPrice *= 1000000;
        // }

        List<String> listFactory = null;
        if(factoryString != null) {
            listFactory = Arrays.asList(factoryString.split(","));
        }

        // Specification<Product> defaultSpec = (root, query, builder) -> builder.disjunction();
        return productRepository.findAll(ProductSpecification.productFactoryIn(listFactory), pageable);
        // return productRepository.findAll(pageable);
    }

    public Product getById(Long id){
        return productRepository.findById(id).orElse(null);
    }

    public boolean updateProduct(Product product, MultipartFile file){
        Product currentProduct = getById(product.getId());
        if(currentProduct == null) return false;

        if(!file.isEmpty()){
            product.setImage(uploadService.handleSaveUploadFile(file, "product"));
        }
        productRepository.save(product);
        return true;
    }
}
