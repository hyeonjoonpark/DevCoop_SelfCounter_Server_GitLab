package com.devcoop.kiosk.domain.item;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @ToString
@Table(name = "items")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {
    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;
    @Column(name = "barcode")
    private String barcode;
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "item_price")
    private int itemPrice;

    @Builder
    public Item(int itemId, String barcode, String itemName, int itemPrice) {
        this.itemId = itemId;
        this.barcode = barcode;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }
}
