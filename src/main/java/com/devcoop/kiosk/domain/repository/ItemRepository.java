package com.devcoop.kiosk.domain.repository;

import com.devcoop.kiosk.domain.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {
    ItemEntity findByBarcode(String barcode);

    ItemEntity findItemIdByItemName(String itemName);

    List<String> findItemIdsByItemName(String itemName);

    List<ItemEntity> findItemEntitiesByItemName(String itemName);
}
