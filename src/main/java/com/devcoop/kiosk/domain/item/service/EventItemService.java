package com.devcoop.kiosk.domain.item.service;

import com.devcoop.kiosk.domain.item.Item;
import com.devcoop.kiosk.domain.item.presentation.dto.EventItemResponse;
import com.devcoop.kiosk.domain.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventItemService {
  private final ItemRepository itemRepository;

  @Transactional(readOnly = true)
  public List<EventItemResponse> read() {
    List<Item> items = itemRepository.findAllByEvent();
    return items.stream()
      .map(
        item -> EventItemResponse.builder()
          .barcode(item.getBarcode())
          .itemName(item.getItemName())
          .itemPrice(item.getItemPrice())
          .event(item.getEvent())
          .eventStartDate(item.getEventStartDate())
          .eventEndDate(item.getEventEndDate())
          .build())
        .collect(Collectors.toList()
      );
  }
}
