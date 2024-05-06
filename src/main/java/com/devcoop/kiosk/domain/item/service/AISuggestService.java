package com.devcoop.kiosk.domain.item.service;

import java.util.List;
import java.util.Random;

import com.devcoop.kiosk.domain.item.repository.ItemRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AISuggestService {
  private final ItemRepository itemRepository;
  private final Random random = new Random(); // Random 객체를 필드로 선언

  public String read() {
    List<String> items = itemRepository.findNameAll(); // 모든 아이템을 가져옴

    int randomIndex = random.nextInt(items.size()); // 무작위 인덱스 생성

    return items.get(randomIndex); // 무작위 아이템 반환
  }
}
