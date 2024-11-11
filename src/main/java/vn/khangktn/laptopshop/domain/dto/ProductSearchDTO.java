package vn.khangktn.laptopshop.domain.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductSearchDTO {
    String page;
    String name;
    List<String> factory;
    List<String> target;
    List<String> price;
    List<String> cpu;
    List<String> vga;
    List<String> ram;
    List<String> storage;
    List<String> screen;
    String sortBy;
}
