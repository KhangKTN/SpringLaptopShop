package vn.khangktn.laptopshop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import vn.khangktn.laptopshop.domain.Cart;
import vn.khangktn.laptopshop.domain.CartDetail;
import vn.khangktn.laptopshop.domain.Order;
import vn.khangktn.laptopshop.domain.OrderDetail;
import vn.khangktn.laptopshop.domain.User;
import vn.khangktn.laptopshop.repository.CartRepository;
import vn.khangktn.laptopshop.repository.OrderDetailRepository;
import vn.khangktn.laptopshop.repository.OrderRepository;

@Service
public class OrderService {
    @Autowired OrderRepository orderRepository;
    @Autowired CartRepository cartRepository;
    @Autowired OrderDetailRepository orderDetailRepository;
    @Autowired UserService userService;

    public void handlePlaceOrder(HttpSession session, String receiverName, String receiverAddress, String receiverPhone) {
        String email = (String) session.getAttribute("email");
        User user = userService.getUserByEmail(email);
        if(user == null) {
            return;
        }

        // Create new order
        Order order = new Order();
        
        order.setUser(user);
        order.setReceiverName(receiverName);
        order.setReceiverAddress(receiverAddress);
        order.setReceiverPhone(receiverPhone);

        // Get cart user
        

        // Set order list and save
        List<OrderDetail> orderDetailList = new ArrayList<>();
        double totalPrice = getTotalPriceCart(user, order, orderDetailList);
        order.setTotalPrice(totalPrice);

        orderRepository.save(order);
        orderDetailRepository.saveAll(orderDetailList);

        // Delete all product in cart
    }

    double getTotalPriceCart(User user, Order order, List<OrderDetail> orderDetailList) {
        Cart cart = cartRepository.findByUser(user);
        List<CartDetail> cartDetailList = cart.getCartDetails();

        OrderDetail orderDetail = null;
        double totalPrice = 0;
        for(CartDetail cartDetail : cartDetailList){
            orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(cartDetail.getProduct());
            orderDetail.setQuantity(cartDetail.getQuantity());
            orderDetail.setPrice(cartDetail.getPrice());

            totalPrice += cartDetail.getPrice() * cartDetail.getQuantity();
            orderDetailList.add(orderDetail);
        }
        return totalPrice;
    }
}
