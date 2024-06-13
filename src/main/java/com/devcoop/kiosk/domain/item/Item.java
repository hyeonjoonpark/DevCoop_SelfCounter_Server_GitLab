package com.devcoop.kiosk.domain.item;

import com.devcoop.kiosk.domain.item.types.EventType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "items")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer itemId;
    private String barcode;
    private String itemName;
    private int itemPrice;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventType event = EventType.NONE;

    // 상품 이미지
    private String itemImage;

    @Builder
    public Item(Integer itemId, String barcode, String itemName, int itemPrice, EventType event, String itemImage) {
        this.itemId = itemId;
        this.barcode = barcode;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.event = event;
        this.itemImage = itemImage;
    }
}
