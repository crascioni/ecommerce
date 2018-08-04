package com.worldpay.rascioni.ecommerce.utility;

public class Utility {

    public static boolean isNullOrEmpty(String string) {
        if (string == null || string == "")
            return true;
        return false;
    }

}
