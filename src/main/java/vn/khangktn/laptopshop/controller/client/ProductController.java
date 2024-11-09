package vn.khangktn.laptopshop.controller.client;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import vn.khangktn.laptopshop.domain.Product;
import vn.khangktn.laptopshop.domain.dto.ProductSearchDTO;
import vn.khangktn.laptopshop.service.ProductService;

@Controller(value = "clientProduct")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/product/{slugId}")
    public String getPageDetalProduct(@PathVariable("slugId") String slug, Model model) throws Exception {
        Product product = productService.getBySlug(slug);
        if (product == null) {
            throw new Exception("");
        }
        model.addAttribute("product", product);
        return "client/product/detail";
    }

    @GetMapping("/product/all-products")
    public String showAllProduct(Model model, ProductSearchDTO productSearch) {
        Page<Product> pageProduct = productService.getAllProductCondition(productSearch);
        model.addAttribute("productList", pageProduct.getContent().size() > 0 ? pageProduct.getContent() : new ArrayList<Product>());

        return "client/product/list";
    }
}
