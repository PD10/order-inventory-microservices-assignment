package com.koerber.inventory_service.repository;

import com.koerber.inventory_service.entity.ProductMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductMaterialRepository extends JpaRepository<ProductMaterial, Long> {
}
