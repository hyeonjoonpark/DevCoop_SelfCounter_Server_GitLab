package com.devcoop.kiosk.domain.item.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecommandResponse {
  private String itemName;    // 엔티티의 필드 이름과 일치시킴
  private int itemPrice;      // 엔티티의 필드 이름과 일치시킴
  private int studentsCount;

  @Builder
  public RecommandResponse(String itemName, int itemPrice, int studentsCount) {
    this.itemName = itemName;
    this.itemPrice = itemPrice;
    this.studentsCount = studentsCount;
  }
}
