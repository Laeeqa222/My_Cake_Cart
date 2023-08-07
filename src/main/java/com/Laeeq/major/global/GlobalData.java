package com.Laeeq.major.global;

import java.util.ArrayList;
import java.util.List;

import com.Laeeq.major.model.Product;

public class GlobalData {
    public static Boolean isLoggedin=true;
    public static List<Product> cart;
    static{
        cart =new ArrayList<Product>();
    }
}
