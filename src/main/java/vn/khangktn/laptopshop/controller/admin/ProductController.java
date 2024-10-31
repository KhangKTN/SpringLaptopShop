package vn.khangktn.laptopshop.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.khangktn.laptopshop.domain.Product;
import vn.khangktn.laptopshop.service.ProductService;
import vn.khangktn.laptopshop.util.PagingUtil;



@Controller
@RequestMapping("admin/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("")
    public String getListProduct(Model model, @RequestParam(name = "page", required = false) String pageNumber) {
        int page = PagingUtil.getPageNumberFromString(pageNumber);
        if(page == 1) {
            return "redirect:/admin/product";
        }
        if(page == 0) {
            page = 1;
        }

        Page<Product> pageProduct = productService.getAllProduct(page);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage", pageProduct.getTotalPages());
        model.addAttribute("productList", pageProduct.getContent());

        return "admin/product/list";
    }

    @GetMapping("create")
    public String getCreateProductPage(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }

    @PostMapping("create")
    public String postMethodName(@ModelAttribute() Product product, @RequestParam("productImg") MultipartFile file, Model model) {
        productService.createProduct(product, file);
        return "redirect:/admin/product/create";
    }

    @GetMapping("update/{id}")
    public String getPageUpdateProduct(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productService.getById(id));
        return "admin/product/update";
    }

    @PostMapping("update")
    public String updatProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, 
        @RequestParam("productImg") MultipartFile file, Model model
    ) {
        if(bindingResult.hasErrors()){
            return "admin/product/update";
        }

        productService.updateProduct(product, file);
        return "redirect:/admin/product/list";
    }

}
