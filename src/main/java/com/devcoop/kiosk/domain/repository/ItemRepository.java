package com.devcoop.kiosk.domain.repository;

import com.devcoop.kiosk.domain.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {
    ItemEntity findByBarcode(String barcode);
}