package com.devcoop.kiosk.domain.receipt.repository;

import com.devcoop.kiosk.domain.item.presentation.dto.RecommandResponse;
import com.devcoop.kiosk.domain.receipt.KioskReceipt;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KioskReceiptRepository extends JpaRepository<KioskReceipt, Integer> {

  @Query("SELECT new com.devcoop.kiosk.domain.item.presentation.dto.RecommandResponse(i.itemName, i.itemPrice, CAST(COUNT(r) AS int)) " +
    "FROM KioskReceipt r JOIN r.item i " +
    "GROUP BY i.itemId " +
    "ORDER BY COUNT(r) DESC")
  List<RecommandResponse> findTop5(Pageable topFive);

}


