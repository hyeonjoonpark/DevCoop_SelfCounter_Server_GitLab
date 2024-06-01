package com.devcoop.kiosk.domain.item.presentation;

import com.devcoop.kiosk.domain.item.presentation.dto.EventItemResponse;
import com.devcoop.kiosk.domain.item.service.EventItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/event-item")
@RequiredArgsConstructor
public class EventItemController {
  private final EventItemService eventItemService;
  @GetMapping("/get-item")
  public List<EventItemResponse> getItems() {
    List<EventItemResponse> result = eventItemService.read();
    return result;
  }
}
