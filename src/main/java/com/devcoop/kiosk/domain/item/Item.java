package com.devcoop.kiosk.domain.item;

import com.devcoop.kiosk.domain.receipt.KioskReceipt;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@ToString
@Table
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

  @OneToMany(
    mappedBy = "item",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  private List<KioskReceipt> receipts;

  public void addReceipt(KioskReceipt receipt) {
    receipt.setItem(this);
    receipts.add(receipt);
  }

  @Builder
  public Item(String barcode, String itemName, int itemPrice, List<KioskReceipt> receipts) {
    this.barcode = barcode;
    this.itemName = itemName;
    this.itemPrice = itemPrice;
    this.receipts = receipts;
  }
}
