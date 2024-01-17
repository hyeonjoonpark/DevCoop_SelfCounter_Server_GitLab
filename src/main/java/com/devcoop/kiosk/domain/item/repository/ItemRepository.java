package com.devcoop.kiosk.domain.item.repository;

import com.devcoop.kiosk.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    Item findByBarcode(String barcode);

    Item findItemIdByItemName(String itemName);

    List<String> findItemIdsByItemName(String itemName);

    List<Item> findItemEntitiesByItemName(String itemName);
}
