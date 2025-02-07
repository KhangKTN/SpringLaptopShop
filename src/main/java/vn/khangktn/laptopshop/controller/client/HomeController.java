package vn.khangktn.laptopshop.controller.client;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import vn.khangktn.laptopshop.domain.Product;
import vn.khangktn.laptopshop.domain.User;
import vn.khangktn.laptopshop.domain.dto.RegisterDTO;
import vn.khangktn.laptopshop.service.ProductService;
import vn.khangktn.laptopshop.service.UserService;


@Controller
public class HomeController {
    static final Integer PRODUCT_HOMEPAGE_NUMBER = 10;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String getHomepageClient(Model model) {
        Page<Product> pageProduct = productService.getAllProduct(1);
        model.addAttribute("productList", pageProduct.getContent());
        model.addAttribute("dataList", productService.getProductByFactories());
        return "client/home";
    }

    @GetMapping("/register")
    public String getPageRegister(Model model) {
        model.addAttribute("model", new RegisterDTO());
        return "client/auth/register";
    }

    @PostMapping("/register")
    public String postMethodName(@ModelAttribute("model") @Valid RegisterDTO registerDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "client/auth/register";
        }
        User user = userService.registerDTOToUser(registerDTO);
        userService.saveUser(user, Optional.empty());
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        return "client/auth/login";
    }

    @GetMapping("/access-denied")
    public String getMethodName() {
        return "client/error/403";
    }
}
