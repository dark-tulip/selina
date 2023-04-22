package kz.scan.selina.controller;

import kz.scan.selina.dto.ScriptHolderDto;
import kz.scan.selina.service.AttackService;
import kz.scan.selina.service.ScanningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.List;

@RestController
@RequestMapping("/open/api")
public class OpenApi {

  @Autowired
  AttackService attackService;

  @Autowired
  ScanningService scanningService;

  @GetMapping("scripts")
  public List<ScriptHolderDto> getTasks() {
    return attackService.selectAll();
  }

  @GetMapping("scripts/filter")
  public List<ScriptHolderDto> getTasks(SelectFilters filters) {
    return attackService.selectAll(filters);
  }

  @PostMapping("start")
  public void startScanning() {


  }

  @GetMapping("test")
  public SelectFilters tt() {
    return new SelectFilters();
  }


}
