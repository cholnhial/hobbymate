package com.cholnhial.shopservice.controller;

import com.cholnhial.shopservice.mapper.ShopMapper;
import com.cholnhial.shopservice.payload.NewListingRequest;
import com.cholnhial.shopservice.payload.ShopResponse;
import com.cholnhial.shopservice.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
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
    public ResponseEntity<?> createNewListing(@Valid @RequestBody NewListingRequest request) {
        var newShopListing = shopMapper.shopListRequestToShop(request);
        var savedListing = this.shopService.saveListing(newShopListing);
        if (savedListing == null) {
            return ResponseEntity.ok("");
        }

        return ResponseEntity.ok().body(savedListing);
    }

    @PostMapping("/unlist/{artefactId}")
    public ResponseEntity<?> unlist(@PathVariable Long artefactId) {
        this.shopService.unlist(artefactId);
        return ResponseEntity.ok().body("");
    }

    @GetMapping("/all")
    public ResponseEntity<List<ShopResponse>> getAllListings() {
        return ResponseEntity.ok().body(this.shopService.getAllListings()
                .stream().map(shopMapper::shopToShopResponse).toList());
    }
}
