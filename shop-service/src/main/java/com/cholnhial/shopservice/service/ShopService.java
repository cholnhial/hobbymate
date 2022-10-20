package com.cholnhial.shopservice.service;

import com.cholnhial.shopservice.model.Shop;
import com.cholnhial.shopservice.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ShopService {

    private final ShopRepository shopRepository;

    public List<Shop> getAllListings() {
        return shopRepository.findAll();
    }

    public Shop saveListing(Shop newListing) {
        Optional<Shop> existing = shopRepository.findByArtefactId(newListing.getArtefactId());
        if (existing.isEmpty()) {
            return shopRepository.save(newListing);
        }

        return null;
    }

    public void unlist(Long artefactId) {
        shopRepository.findByArtefactId(artefactId).ifPresent(shopRepository::delete);
    }
}
