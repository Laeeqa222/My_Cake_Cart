package com.Laeeq.major.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.Laeeq.major.global.GlobalData;
import com.Laeeq.major.service.CategoryService;
import com.Laeeq.major.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
public class HomeController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService  productService;
    @GetMapping({"/","home"})
    public String Home(Model m) {
        if(GlobalData.isLoggedin){
        m.addAttribute("cartCount",GlobalData.cart.size());}
        return "index";
    }
    @GetMapping("/shop")
    public String shop(Model m) {
        m.addAttribute("cartCount",GlobalData.cart.size());
        m.addAttribute("categories",categoryService.getAllCategory());
        m.addAttribute("products", productService.getAllProduct());
        return "shop";
    }
    @GetMapping("/shop/category/{id}")
    public String shop(Model m,@PathVariable int id) {
        m.addAttribute("cartCount",GlobalData.cart.size());
        m.addAttribute("categories",categoryService.getAllCategory());
        m.addAttribute("products", productService.findProductsByCategoryId(id));
        return "shop";
    }
    @GetMapping("/shop/viewproduct/{id}")
    public String viewProduct(Model m,@PathVariable long id) {
        m.addAttribute("cartCount",GlobalData.cart.size());
        m.addAttribute("product", productService.findProductById(id).get());
        return "viewProduct";
    }
}
