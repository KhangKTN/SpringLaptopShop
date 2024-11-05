package vn.khangktn.laptopshop.domain.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductSearchDTO {
    String page;
    String name;
    List<String> brand;
    List<String> target;
    List<String> price;
    String sortBy;
}
