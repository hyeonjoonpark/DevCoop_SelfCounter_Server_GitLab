package com.devcoop.kiosk.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Entity(name = "items")
@Table(name = "items")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ItemEntity {
    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;
    @Column(name = "barcode")
    private String barcode;
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "item_price")
    private int itemPrice;
}
