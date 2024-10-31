package vn.khangktn.laptopshop.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import vn.khangktn.laptopshop.domain.Product;
import vn.khangktn.laptopshop.service.ProductService;
import vn.khangktn.laptopshop.util.PagingUtil;

@Controller(value = "clientProduct")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/product/{id}")
    public String getPageDetalProduct(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getById(id));
        return "client/product/detail";
    }

    @GetMapping("/product/all-products")
    public String showAllProduct(Model model,
        @RequestParam(value = "page", required = false) String pageString,
        @RequestParam(value = "name", required = false) String name,
        @RequestParam(value = "brand", required = false) String brand,
        @RequestParam(value = "price", required = false) String price
    ) {
        int page = PagingUtil.getPageNumberFromString(pageString);
        if(page == 0) {
            page = 1;
        }
        Page<Product> pageProduct = productService.getAllProductCondition(page, brand);
        model.addAttribute("productList", pageProduct.getContent());

        return "client/product/list";
    }
}
