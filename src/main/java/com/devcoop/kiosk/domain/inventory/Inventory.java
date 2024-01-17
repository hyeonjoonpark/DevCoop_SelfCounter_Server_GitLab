package com.devcoop.kiosk.domain.inventory;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "inventory")
@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Inventory {
    @Id
    @Column(name = "inventory_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inventoryId;
    @Column(name = "item_id")
    private int itemId;
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "last_updated")
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
