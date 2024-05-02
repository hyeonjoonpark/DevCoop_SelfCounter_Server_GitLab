package com.devcoop.kiosk.domain.item;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @ToString
@Table(name = "items")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;
    private String barcode;
    private String itemName;
    private int itemPrice;

    @Builder
    public Item(int itemId, String barcode, String itemName, int itemPrice) {
        this.itemId = itemId;
        this.barcode = barcode;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }
}
