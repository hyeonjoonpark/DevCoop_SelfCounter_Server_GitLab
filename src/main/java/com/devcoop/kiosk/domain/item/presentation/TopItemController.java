package com.devcoop.kiosk.domain.item.presentation;

import com.devcoop.kiosk.domain.item.service.TopItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class TopItemController {
  private final TopItemService topItemService;
  @GetMapping("/top/list")
  public List<String> getTopList() {
    List<String> data = topItemService.read();
    return data;
  }
}
