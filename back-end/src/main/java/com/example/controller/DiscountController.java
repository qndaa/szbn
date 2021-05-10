package com.example.controller;

import com.example.enums.CategoryOfUser;
import com.example.model.Subscriber;
import com.example.model.User;
import com.example.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(value = "api/discount")
public class DiscountController {

    @Autowired
    DiscountService discountService;

    @RequestMapping(value = "/category", method = RequestMethod.GET, produces = "application/json")
    public Subscriber getDiscountBasedOnCategory() {
        Subscriber subscriber = new Subscriber();
        subscriber.setCategoryOfUser(CategoryOfUser.SILVER);
        return discountService.getDiscountBasedOnUser(subscriber);
    }
}
