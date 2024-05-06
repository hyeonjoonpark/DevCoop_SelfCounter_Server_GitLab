package com.devcoop.kiosk.domain.item.service;

import com.devcoop.kiosk.domain.item.repository.ItemRepository;
import com.devcoop.kiosk.domain.paylog.repository.PayLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopItemService {
  private final ItemRepository itemRepository;
  private final PayLogRepository payLogRepository;

  @Transactional(readOnly = true)
  public List<String> read() {
    List<String> topItemName = payLogRepository.findTop5ItemName();

    return topItemName;
  }
}

