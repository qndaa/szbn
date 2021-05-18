package sbnz.integracija.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sbnz.integracija.example.enums.CategoryOfUser;
import sbnz.integracija.example.facts.Subscriber;
import sbnz.integracija.example.service.DiscountService;

@RestController
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
