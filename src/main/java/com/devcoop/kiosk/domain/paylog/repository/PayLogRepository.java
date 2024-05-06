package com.devcoop.kiosk.domain.paylog.repository;

import com.devcoop.kiosk.domain.paylog.PayLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayLogRepository extends JpaRepository<PayLog, Integer> {

  @Query("select i.itemName " +
    "from Item i, KioskReceipt kr " +
    "where i.itemName = kr.itemName " +
    "group by i.itemName " +
    "order by count(i.itemName) " +
    "desc limit 5")
  List<String> findTop5ItemName();
}
