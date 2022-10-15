package com.cholnhial.shopservice.service;

import com.cholnhial.shopservice.model.Shop;
import com.cholnhial.shopservice.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;

    public List<Shop> getAllListings() {
        return shopRepository.findAll();
    }

    public Shop saveListing(Shop newListing) {
        return shopRepository.save(newListing);
    }
}
