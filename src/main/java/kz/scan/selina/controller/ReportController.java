package kz.scan.selina.controller;


import kz.scan.selina.service.ScanningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {

  @Autowired
  ScanningService scanningService;

  @GetMapping("/report")
  public String getReportUrl() {
    scanningService.executeTests();
    scanningService.buildReport();
    return "http://127.0.0.1:61000";
  }
}
