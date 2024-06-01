package com.devcoop.kiosk.domain.item;

import com.devcoop.kiosk.domain.item.types.EventType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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
    @Enumerated(EnumType.STRING)
    private EventType event = EventType.NONE;

    @Builder
    public Item(int itemId, String barcode, String itemName, int itemPrice, EventType event) {
        this.itemId = itemId;
        this.barcode = barcode;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.event = event;
    }
}
