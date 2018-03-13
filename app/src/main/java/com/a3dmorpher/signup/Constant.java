package com.a3dmorpher.signup;


import com.a3dmorpher.POJO.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public final class Constant {
    public static final List<Integer> QUANTITY_LIST = new ArrayList<Integer>();
    public static final Product PRODUCT1 = new Product(1, "Samsung Galaxy S6", BigDecimal.valueOf(199.996), "Worldly looks and top-notch specs make the impressive, metal Samsung Galaxy S6 the Android phone to beat for 2015", "samsung_galaxy_s6");
    public static final Product PRODUCT2 = new Product(2, "HTC One M8", BigDecimal.valueOf(449.9947), "Excellent overall phone. Beautifull, battery life more than 20 hours daily and great customization in any way. 100% configuration on any aspect", "htc_one_m8");
    public static final Product PRODUCT3 = new Product(3, "Xiaomi Mi3", BigDecimal.valueOf(319.998140), "Xiaomi's Mi 3 is a showcase of how Chinese phonemakers can create quality hardware without breaking the bank. If you don't need 4G LTE, and you can get hold of it, this is one of the best smartphones you can buy in its price range.", "xiaomi_mi3");
    public static final Product PRODUCT4 = new Product(4, "Motorola Mi3", BigDecimal.valueOf(519.998140), "Motorola's G4 Plus is a showcase of how Chinese phonemakers can create quality hardware without breaking the bank. If you don't need 4G LTE, and you can get hold of it, this is one of the best smartphones you can buy in its price range.", "xiaomi_mi3");
    public static final Product PRODUCT5 = new Product(5, "iPhone 10", BigDecimal.valueOf(919.998140), "Apple's iPhone 10 is a showcase of how Chinese phonemakers can create quality hardware without breaking the bank. If you don't need 4G LTE, and you can get hold of it, this is one of the best smartphones you can buy in its price range.", "xiaomi_mi3");
    public static final List<Product> PRODUCT_LIST = new ArrayList<Product>();
    public static final String CURRENCY = "$";

    static {
        for (int i = 1; i < 11; i++) QUANTITY_LIST.add(i);
    }

    static {
        PRODUCT_LIST.add(PRODUCT1);
        PRODUCT_LIST.add(PRODUCT2);
        PRODUCT_LIST.add(PRODUCT3);
        PRODUCT_LIST.add(PRODUCT4);
        PRODUCT_LIST.add(PRODUCT5);
    }
}