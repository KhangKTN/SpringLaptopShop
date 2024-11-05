package vn.khangktn.laptopshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import vn.khangktn.laptopshop.domain.Product;
import vn.khangktn.laptopshop.domain.Product_;
import vn.khangktn.laptopshop.domain.dto.ProductSearchDTO;
import vn.khangktn.laptopshop.repository.ProductRepository;
import vn.khangktn.laptopshop.service.specification.ProductSpecification;

@Service
public class ProductService {
    @Autowired ProductRepository productRepository;
    @Autowired UploadService uploadService;
    @Autowired ProductSpecification productSpecification;

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

    public Page<Product> getAllProductCondition(ProductSearchDTO productSearchDTO) {
        Specification<Product> spec = Specification.where(null);
        Sort sort = Sort.unsorted();
        String sortBy = productSearchDTO.getSortBy();
        if ("gia-giam-dan".equals(sortBy)) {
            sort = Sort.by(Direction.DESC, Product_.PRICE.toString());
        } else if ("gia-tang-dan".equals(sortBy)) {
            sort = Sort.by(Direction.ASC, Product_.PRICE.toString());
        }
        Pageable pageable = PageRequest.of(0, 10, sort);

        String productName = productSearchDTO.getName();
        if (productName != null) {
            Specification<Product> specName = productSpecification.productNameContain(productName);
            spec = spec.and(specName);
        }
        if (productSearchDTO.getTarget() != null) {
            Specification<Product> specTarget = productSpecification.productTargetIn(productSearchDTO.getTarget());
            spec = spec.and(specTarget);
        }
        if (productSearchDTO.getBrand() != null) {
            Specification<Product> specBrand = productSpecification.productBrandIn(productSearchDTO.getBrand());
            spec = spec.and(specBrand);
        }
        if (productSearchDTO.getPrice() != null) {
            List<String> priceList = productSearchDTO.getPrice();
            Specification<Product> specPrice = Specification.where(null);
            for (String price : priceList) {
                specPrice = specPrice.or(productSpecification.productPriceRange(price));
            }   
            spec = spec.and(specPrice);
        }
        
        return productRepository.findAll(spec, pageable);
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
