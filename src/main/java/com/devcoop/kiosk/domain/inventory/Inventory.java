package com.devcoop.kiosk.domain.inventory;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "occount_inventory") // 테이블명 일치
@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventoryId", nullable = false)
    private int inventoryId;

    @Column(name = "itemId", nullable = false)
    private int itemId;

    @Column(name = "itemName", length = 200)
    private String itemName;

    @Column(name = "itemQuantity", nullable = false)
    private int itemQuantity;

    @Column(name = "lastUpdated", nullable = false)
    private LocalDateTime lastUpdated;

    @Column(name = "managedEmail", nullable = false, length = 45)
    private String managedEmail;

    @Column(name = "reason", columnDefinition = "text", nullable = false)
    private String reason;

    @Builder
    public Inventory(int inventoryId, int itemId, String itemName, int itemQuantity, LocalDateTime lastUpdated, String managedEmail, String reason) {
        this.inventoryId = inventoryId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.lastUpdated = lastUpdated;
        this.managedEmail = managedEmail;
        this.reason = reason;
    }
}
