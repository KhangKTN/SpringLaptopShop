package vn.khangktn.laptopshop.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "Product name is required")
    String name;
    double price;
    String image;

    @Column(columnDefinition = "MIDIUMTEXT")
    String detailDesc;
    
    String shortDesc;
    long quantity;
    long sold;
    String factory;
    String target;

    @OneToMany(mappedBy = "product")
    List<OrderDetail> orderDetailList;

    @OneToMany(mappedBy = "product")
    List<CartDetail> cartDetails;
}
