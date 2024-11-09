package vn.khangktn.laptopshop.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "Product name is required!")
    String name;

    @NotBlank(message = "Slug is required!")
    String slug;

    @NotNull(message = "Price is required!")
    @Min(message = "Price must be greater 0", value = 1)
    long price;

    String image;

    @Column(columnDefinition = "MEDIUMTEXT")
    String description;

    // @NotNull(message = "Quantity is required!")
    int quantity;

    @NotBlank(message = "Factory is required!")
    String factory;

    @NotBlank(message = "Target is required!")
    String target;

    @NotBlank(message = "CPU is required!")
    String cpu;

    @NotBlank(message = "Cpu Type is required!")
    String cpuType;

    @NotBlank(message = "VGA is required!")
    String vga;

    @NotBlank(message = "VGA Type is required!")
    String vgaType;

    @NotNull(message = "Ram is required!")
    @Min(message = "Ram is required!", value = 4)
    int ram;

    @NotBlank(message = "Storage is required!")
    String storage; 

    @NotNull(message = "Screen size is required!")
    @Min(message = "Screen size is invalid!", value = 13)
    float screen;

    @NotNull(message = "Status is required!")
    String status;

    @OneToMany(mappedBy = "product")
    List<OrderDetail> orderDetailList;

    @OneToMany(mappedBy = "product")
    List<CartDetail> cartDetails;
}
