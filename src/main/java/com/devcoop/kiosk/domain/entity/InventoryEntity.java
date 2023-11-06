package com.devcoop.kiosk.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity(name = "inventory")
@Table(name = "inventory")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryEntity {
    @Id
    @Column(name = "inventory_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int inventoryId;
    @Column(name = "item_id")
    private int itemId;
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "last_updated")
    private Date lastUpdated;
}
