package com.devcoop.kiosk.domain.inventory;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventory")
@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inventoryId;
    private int itemId;
    private String itemName;
    private int quantity;
    private LocalDateTime lastUpdated;

    @Builder
    public Inventory(int inventoryId, int itemId, String itemName, int quantity, LocalDateTime lastUpdated) {
        this.inventoryId = inventoryId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.lastUpdated = lastUpdated;
    }
}
