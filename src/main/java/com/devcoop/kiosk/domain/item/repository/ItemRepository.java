package com.devcoop.kiosk.domain.item.repository;

import com.devcoop.kiosk.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
  Item findByItemName(String itemName);

  Item findByBarcode(String barcode);

  @Query("select i.itemName from Item i")
  List<String> findNameAll();

  @Query("SELECT i FROM Item i WHERE i.event <> 'NONE'")
  List<Item> findAllByEvent();
}
