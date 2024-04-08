package com.devcoop.kiosk.domain.item.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecommandResponse {
  private String name;
  private int price;
  private int studentsCount; // 이 필드를 설정하는 로직이 필요합니다.
  @Builder
  public RecommandResponse(String itemName, int itemPrice, int studentsCount) {
    this.name = itemName;
    this.price = itemPrice;
    this.studentsCount = studentsCount; // 이제 생성자를 통해 이 필드를 설정할 수 있습니다.
  }
}
