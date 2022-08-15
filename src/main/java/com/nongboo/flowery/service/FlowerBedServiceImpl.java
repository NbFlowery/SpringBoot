package com.nongboo.flowery.service;

import com.nongboo.flowery.repository.FlowerBedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlowerBedServiceImpl implements FlowerBedService{

    @Autowired
    private FlowerBedRepository flowerBedRepository;

}
