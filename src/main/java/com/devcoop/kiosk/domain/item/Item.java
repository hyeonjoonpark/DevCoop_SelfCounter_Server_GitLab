package com.devcoop.kiosk.domain.item;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Table(name = "occount_items")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemId", nullable = false)
    private Integer itemId;

    @Column(name = "itemCode", nullable = false, length = 255, columnDefinition = "varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'None'")
    private String itemCode;

    @Column(name = "itemName", nullable = false, length = 255)
    private String itemName;

    @Column(name = "itemExplain", columnDefinition = "text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci")
    private String itemExplain;

    @Column(name = "itemPrice", nullable = false, columnDefinition = "int DEFAULT 0")
    private int itemPrice;

    @Column(name = "itemCategory", length = 45, columnDefinition = "varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL")
    private String itemCategory;

    @Column(name = "itemQuantity", nullable = false, columnDefinition = "int DEFAULT 0")
    private int itemQuantity;

    @Column(name = "event", length = 45, columnDefinition = "varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'NONE'")
    private String event = "NONE"; // 이벤트 타입을 String으로 설정

    @Column(name = "event_start_date", columnDefinition = "date DEFAULT NULL")
    private LocalDate eventStartDate;

    @Column(name = "event_end_date", columnDefinition = "date DEFAULT NULL")
    private LocalDate eventEndDate;

    @Column(name = "item_image", length = 255, columnDefinition = "varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL")
    private String itemImage;

    @Builder
    public Item(Integer itemId, String itemCode, String itemName, String itemExplain, int itemPrice, String itemCategory, int itemQuantity, String event, LocalDate eventStartDate, LocalDate eventEndDate, String itemImage) {
        this.itemId = itemId;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemExplain = itemExplain;
        this.itemPrice = itemPrice;
        this.itemCategory = itemCategory;
        this.itemQuantity = itemQuantity;
        this.event = event;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.itemImage = itemImage;
    }
}
