package com.devcoop.kiosk.domain.item.presentation;

import com.devcoop.kiosk.domain.item.service.AISuggestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class AISuggestController {
  private final AISuggestService suggestService;
  @GetMapping("/ai/suggest")
  public String getSuggest() {
    String data = suggestService.read();
    System.out.println("data = " + data);
    return data;
  }
}
