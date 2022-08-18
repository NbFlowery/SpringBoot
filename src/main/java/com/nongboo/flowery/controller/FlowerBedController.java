package com.nongboo.flowery.controller;

import com.nongboo.flowery.service.FlowerBedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowerBedController {

    @Autowired
    private FlowerBedService flowerBedService;

}
