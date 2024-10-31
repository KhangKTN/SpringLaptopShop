package vn.khangktn.laptopshop.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.khangktn.laptopshop.domain.Cart;
import vn.khangktn.laptopshop.domain.CartDetail;
import vn.khangktn.laptopshop.service.CartService;
import vn.khangktn.laptopshop.service.OrderService;




@Controller
public class CartController {
    @Autowired CartService cartService;
    @Autowired OrderService orderService;

    @PostMapping("/add-to-cart/{id}")
    public String addProductToCart(@PathVariable("id") Long productId, HttpServletRequest httpRequest, HttpSession session){
        HttpSession httpSession = httpRequest.getSession(false);
        String email = (String) httpSession.getAttribute("email");
        cartService.handleAddProductCart(productId, email, session);
        return "redirect:/";
    }

    @GetMapping("/cart")
    public String getCartPage(Model model, HttpServletRequest httpRequest) {
        HttpSession httpSession = httpRequest.getSession(false);
        String email = (String) httpSession.getAttribute("email");

        List<CartDetail> cartDetailList = cartService.getCartDetailList(email);

        double totalPrice = 0;
        for(CartDetail cartItem : cartDetailList){
            totalPrice += cartItem.getPrice() * cartItem.getQuantity();
        }
        Cart cart = new Cart();
        cart.setCartDetails(cartDetailList);
        model.addAttribute("cartDetails", cartDetailList);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("cart", cart);
        return "client/cart/show";
    }
    

    @PostMapping("/delete-cart-product/{id}")
    public String deleteProductCart(@PathVariable() Long id, HttpSession session) {
        cartService.deleteProductCart(id, session);
        return "redirect:/cart";
    }

    @PostMapping("/confirm-checkout")
    public String checkout(@ModelAttribute("cart") Cart cart) {
        cartService.updateCartCheckout(cart);
        return "redirect:/checkout";
    }

    @GetMapping("/checkout")
    public String getPageCheckout(Model model, HttpServletRequest httpRequest) {
        HttpSession httpSession = httpRequest.getSession(false);
        String email = (String) httpSession.getAttribute("email");

        List<CartDetail> cartDetailList = cartService.getCartDetailList(email);

        double totalPrice = 0;
        for(CartDetail cartItem : cartDetailList){
            totalPrice += cartItem.getPrice() * cartItem.getQuantity();
        }

        model.addAttribute("cartDetails", cartDetailList);
        model.addAttribute("totalPrice", totalPrice);
        return "client/cart/checkout";
    }

    @PostMapping("/place-order")
    public String handlePlaceOrder(
        HttpServletRequest request, HttpSession session, 
        @RequestParam("receiverName") String receiverName, 
        @RequestParam("receiverAddress") String receiverAddress,
        @RequestParam("receiverPhone") String receiverPhone
    ) {
        orderService.handlePlaceOrder(session, receiverName, receiverAddress, receiverPhone);
        return "redirect:/checkout";
    }
}
