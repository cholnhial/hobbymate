package com.cholnhial.shopservice.repository;

import com.cholnhial.shopservice.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    Optional<Shop> findByArtefactId(Long artefactId);
}
