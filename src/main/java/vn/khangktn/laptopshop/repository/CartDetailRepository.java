package vn.khangktn.laptopshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.khangktn.laptopshop.domain.Cart;
import vn.khangktn.laptopshop.domain.CartDetail;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
    CartDetail findByCartIdAndProductId(Long cartId, Long productId);
    List<CartDetail> findByCart(Cart cart);
    List<CartDetail> findByIdIn(List<Long> idList);
}
