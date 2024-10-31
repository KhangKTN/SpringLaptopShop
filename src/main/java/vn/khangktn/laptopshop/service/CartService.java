package vn.khangktn.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import vn.khangktn.laptopshop.domain.Cart;
import vn.khangktn.laptopshop.domain.CartDetail;
import vn.khangktn.laptopshop.domain.Product;
import vn.khangktn.laptopshop.domain.User;
import vn.khangktn.laptopshop.repository.CartDetailRepository;
import vn.khangktn.laptopshop.repository.CartRepository;

@Service
public class CartService {
    @Autowired CartRepository cartRepository;
    @Autowired CartDetailRepository cartDetailRepository;
    @Autowired UserService userService;
    @Autowired ProductService productService;

    public void handleAddProductCart(Long productId, String email, HttpSession session) {
        // Check if user email exists
        User user = userService.getUserByEmail(email);
        if(user == null){
            return;
        }

        // Check if user has shopping cart. Create new cart if it don't exists
        Cart userCart = cartRepository.findByUserEmail(email);
        CartDetail cartDetail = null;
        Product product = productService.getById(productId);

        if(userCart == null) {
            // Create new cart
            userCart = new Cart();
            userCart.setUser(user);
        }
        else {
            cartDetail = cartDetailRepository.findByCartIdAndProductId(userCart.getId(), productId);
        }

        // Create new cart detail if null
        if(cartDetail == null) cartDetail = new CartDetail();

        // Add product info to detail cart
        cartDetail.setCart(userCart);
        cartDetail.setPrice(product.getPrice());
        cartDetail.setProduct(product);
        cartDetail.setQuantity(cartDetail.getQuantity() + 1);

        // Save cart detail
        cartDetailRepository.save(cartDetail);

        // Update quantity and Save cart
        userCart.setQuantity(userCart.getQuantity() + 1);
        cartRepository.save(userCart);

        session.setAttribute("numberItemCart", userCart.getQuantity());
    }

    public List<CartDetail> getCartDetailList(String email) {
        Cart userCart = cartRepository.findByUserEmail(email);
        return cartDetailRepository.findByCart(userCart);
    }

    public boolean deleteProductCart(Long detailCartId, HttpSession session) {
        try {
            Optional<CartDetail> cartDetailOptional = cartDetailRepository.findById(detailCartId);
            CartDetail cartDetail = null;
            if(cartDetailOptional.isPresent()) {
                cartDetail = cartDetailOptional.get();
            } else {
                return false;
            }

            // Delete item in cart
            cartDetailRepository.deleteById(detailCartId);

            // Update cart quantity
            Cart cart = cartDetail.getCart();
            int newQuantity = cart.getQuantity() - cartDetail.getQuantity();
            cart.setQuantity(newQuantity);
            cartRepository.save(cart);

            session.setAttribute("numberItemCart", newQuantity);
        return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void updateCartCheckout(Cart cart) {
        List<CartDetail> cartDetailList = cart.getCartDetails();
        if(cartDetailList == null) {
            return;
        }

        // Get list cartDetail from list Id cartDetail
        List<Long> cartDetailIdList = cartDetailList.stream().map(item -> item.getId()).toList();
        List<CartDetail> cartDetailDbList = cartDetailRepository.findByIdIn(cartDetailIdList);

        // Update quantity product in cart
        CartDetail cartDetail = null;
        for(int i = 0; i < cartDetailDbList.size(); i++) {
            cartDetail = cartDetailDbList.get(i);
            cartDetail.setQuantity(cartDetailList.get(i).getQuantity());
        }

        cartDetailRepository.saveAll(cartDetailDbList);
    }
}
