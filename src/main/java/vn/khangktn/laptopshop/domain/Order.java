package vn.khangktn.laptopshop.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    double totalPrice;

    private String receiverName;

    private String receiverAddress;

    private String receiverPhone;

    @ManyToOne
    @JoinColumn(name = "userId")
    User user;

    @OneToMany(mappedBy = "order")
    List<OrderDetail> orderDetailList;
}
