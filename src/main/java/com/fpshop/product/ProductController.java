package com.fpshop.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

    @GetMapping("/outer")
    public String outer() {
        return "/product/outer";
    }

    @GetMapping("/top")
    public String top() {
        return "/product/top";
    }

    @GetMapping("/dress")
    public String dress() {
        return "/product/dress";
    }

    @GetMapping("/pants")
    public String pants() {
        return "/product/pants";
    }

    @GetMapping("/skirt")
    public String skirt() {
        return "/product/skirt";
    }

    @GetMapping("/shoes")
    public String shoes() {
        return "/product/shoes";
    }

}
