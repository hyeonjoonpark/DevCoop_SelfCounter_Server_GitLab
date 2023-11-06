package com.devcoop.kiosk.domain.repository;

import com.devcoop.kiosk.domain.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Integer> {

}
