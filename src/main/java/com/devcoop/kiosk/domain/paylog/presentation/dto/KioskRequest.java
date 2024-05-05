package com.devcoop.kiosk.domain.paylog.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KioskRequest {
  List<KioskItemInfo> items = new ArrayList<>();
  String userId;
}
