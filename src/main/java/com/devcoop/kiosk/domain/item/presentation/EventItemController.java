package com.devcoop.kiosk.domain.item.presentation;

import com.devcoop.kiosk.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/event-item")
@RequiredArgsConstructor
public class EventItemController {
  @GetMapping("/get-item")
  public List<Item> getItems() {

    return null;
  }
}
