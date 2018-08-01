package com.worldpay.rascioni.ecommerce.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    Map<String,String> map = new HashMap<>();

    @RequestMapping("/welcome")
    public Map firstPage() {
        map.put("ciao", "ciao");
        return map;
    }

}
