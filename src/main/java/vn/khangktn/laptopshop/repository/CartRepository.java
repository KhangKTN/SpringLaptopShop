package vn.khangktn.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.khangktn.laptopshop.domain.Cart;
import vn.khangktn.laptopshop.domain.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserEmail(String email);
    Cart findByUser(User user);
}
