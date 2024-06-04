package com.devcoop.kiosk.domain.item;

import com.devcoop.kiosk.domain.item.types.EventType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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

  private LocalDate eventStartDate;
  private LocalDate eventEndDate;

  // 상품 이미지
  private String itemImage;

  @Builder
  public Item(Integer itemId, String barcode, String itemName, int itemPrice, EventType event, LocalDate eventStartDate, LocalDate eventEndDate, String itemImage) {
    this.itemId = itemId;
    this.barcode = barcode;
    this.itemName = itemName;
    this.itemPrice = itemPrice;
    this.event = event;
    this.eventStartDate = eventStartDate;
    this.eventEndDate = eventEndDate;
    this.itemImage = itemImage;
  }
}
