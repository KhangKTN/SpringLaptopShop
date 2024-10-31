package vn.khangktn.laptopshop.domain;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "Email isn't allow blank")
    String email;

    @Length(min = 6, message = "Password must be least 6 character")
    String password;

    @NotBlank(message = "Name isn't allow blank")
    String fullName;

    String address;

    String phone;

    String avatar;

    @ManyToOne
    @JoinColumn(name = "roleId")
    Role role;

    @OneToMany(mappedBy = "user")
    List<Order> orderList;

    @OneToOne(mappedBy = "user")
    Cart cart;
}
