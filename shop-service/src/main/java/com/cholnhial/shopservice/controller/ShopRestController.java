package com.cholnhial.shopservice.controller;

import com.cholnhial.shopservice.mapper.ShopMapper;
import com.cholnhial.shopservice.payload.NewListingRequest;
import com.cholnhial.shopservice.payload.ShopResponse;
import com.cholnhial.shopservice.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/shop/api")
@RequiredArgsConstructor
public class ShopRestController {

    private final ShopService shopService;
    private final ShopMapper shopMapper;

    @PostMapping("/list")
    public ResponseEntity<ShopResponse> createNewListing(@Valid @RequestBody NewListingRequest request) {
        var newShopListing = shopMapper.shopListRequestToShop(request);
        return ResponseEntity.ok().body(shopMapper.shopToShopResponse(this.shopService.saveListing(newShopListing)));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ShopResponse>> getAllListings() {
        return ResponseEntity.ok().body(this.shopService.getAllListings()
                .stream().map(shopMapper::shopToShopResponse).toList());
    }
}
