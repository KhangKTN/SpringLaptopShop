package vn.khangktn.laptopshop.service;

import java.util.List;
import java.util.Random;

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

    Random rand = new Random();

    public Product getById(Long id){
        return productRepository.findById(id).orElse(null);
    }

    public Product getBySlug(String slug) {
        return productRepository.findBySlug(slug);
    }

    public int generateQuantity() {
        // Obtain a number between [0 - 49].
        return rand.nextInt(50) + 1;
    }

    public boolean createProduct(Product product, MultipartFile file){
        if(!file.isEmpty()){
            product.setImage(uploadService.handleSaveUploadFile(file, "product"));
        }
        product.setQuantity(generateQuantity());
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
        Pageable pageable = PageRequest.of(0, 20, sort);

        String productName = productSearchDTO.getName();
        Specification<Product> specCur = null;
        if (productName != null) {
            specCur = productSpecification.productNameContain(productName);
            spec = spec.and(specCur);
        }
        if (productSearchDTO.getTarget() != null) {
            specCur = productSpecification.productTargetIn(productSearchDTO.getTarget());
            spec = spec.and(specCur);
        }
        if (productSearchDTO.getFactory() != null) {
            specCur = productSpecification.productBrandIn(productSearchDTO.getFactory());
            spec = spec.and(specCur);
        }
        if (productSearchDTO.getPrice() != null) {
            List<String> priceList = productSearchDTO.getPrice();
            specCur = Specification.where(null);
            for (String price : priceList) {
                specCur = specCur.or(productSpecification.productPriceRange(price));
            }   
            spec = spec.and(specCur);
        }
        if (productSearchDTO.getCpu() != null) {
            specCur = productSpecification.productCpuIn(productSearchDTO.getCpu());
            spec = spec.and(specCur);
        }
        if (productSearchDTO.getVga() != null) {
            specCur = productSpecification.productVgaIn(productSearchDTO.getVga());
            spec = spec.and(specCur);
        }
        if (productSearchDTO.getRam() != null) {
            specCur = productSpecification.productRam(productSearchDTO.getRam());
            spec = spec.and(specCur);
        }
        if (productSearchDTO.getScreen() != null) {
            List<String> screenList = productSearchDTO.getScreen();
            specCur = Specification.where(null);
            for (String screen : screenList) {
                specCur = specCur.or(productSpecification.productScreen(screen));
            }   
            spec = spec.and(specCur);
        }
        
        return productRepository.findAll(spec, pageable);
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
