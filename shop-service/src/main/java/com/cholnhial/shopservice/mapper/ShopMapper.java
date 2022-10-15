package com.cholnhial.shopservice.mapper;

import com.cholnhial.shopservice.model.Shop;
import com.cholnhial.shopservice.payload.NewListingRequest;
import com.cholnhial.shopservice.payload.ShopResponse;
import org.springframework.stereotype.Service;

@Service
public class ShopMapper {

    public Shop shopListRequestToShop(NewListingRequest request) {
        Shop shop = new Shop();
        shop.setName(request.getName());
        shop.setDescription(request.getDescription());
        shop.setPicture(request.getPicture());
        shop.setPrice(request.getPrice());
        shop.setArtefactId(request.getArtefactId());
        return shop;
    }

    public ShopResponse shopToShopResponse(Shop shop) {
        ShopResponse shopResponse = new ShopResponse();
        shopResponse.setId(shop.getId());
        shopResponse.setName(shop.getName());
        shopResponse.setDescription(shop.getDescription());
        shopResponse.setPicture(shop.getPicture());
        shopResponse.setArtefactId(shop.getArtefactId());
        shopResponse.setPrice(shop.getPrice());

        return shopResponse;
    }
}
