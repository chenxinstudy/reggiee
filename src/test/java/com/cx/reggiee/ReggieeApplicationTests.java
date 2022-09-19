package com.cx.reggiee;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

@SpringBootTest
class ReggieeApplicationTests {

  @Test
  void contextLoads() {}

  @Test
  public void testUpload() {
    String fileName = "aaddk.d.pmg.png";
    System.err.println(fileName.substring(fileName.lastIndexOf(".")));
  }

  @Test
  public void testUUID() {
    UUID uuid = UUID.randomUUID();
    System.out.println(uuid);
    String u2 = UUID.randomUUID().toString();
    System.out.println(
        "UUID.randomUUID().toString().toLowerCase() = "
            + UUID.randomUUID().toString().toLowerCase());

    System.out.println(UUID.randomUUID().toString().toUpperCase());
    System.out.println(uuid);
    System.out.println(u2);
  }

  @Test
  public void testDate() {
    Date date1 = new Date();
    LocalDateTime date2 = LocalDateTime.now();
    System.out.println("date2.toLocalDate() = " + date2.toLocalDate());
    System.out.println("date2.toLocalTime() = " + date2.toLocalTime());
    LocalDate date3 = LocalDate.now();
    LocalTime date4 = LocalTime.now();
    System.out.println("LocalTime.of(01,01,01) = " + LocalDateTime.of(2020, 1, 2, 13, 14, 34));
    System.out.println("date1 = " + date1);
    System.out.println("date2 = " + date2);
    System.out.println("date3 = " + date3);
    System.out.println("date4 = " + date4);
    LocalDateTime parse = LocalDateTime.parse("2020-01-02T13:14:34");
    System.out.println("parse = " + parse);
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    System.out.println(
        "dateTimeFormatter.format(LocalDateTime.now()) = "
            + dateTimeFormatter.format(LocalDateTime.now()));

    LocalDateTime now = LocalDateTime.now();
    System.out.println("now.plusYears(2L) = " + now.plusYears(2L));
    LocalDateTime plusMonths = now.plusMonths(2L);
    System.out.println("dateTimeFormatter.format(now) = " + dateTimeFormatter.format(plusMonths));
  }

  @Test
  public void testOrderId() {
    long orderId = IdWorker.getId();
    System.out.println(orderId);
  }
}
