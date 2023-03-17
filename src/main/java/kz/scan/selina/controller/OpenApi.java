package kz.scan.selina.controller;

import kz.scan.selina.dto.ScriptHolderDto;
import kz.scan.selina.service.AttackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/open/api")
public class OpenApi {

  @Autowired
  AttackService attackService;

  @GetMapping("tasks")
  public List<ScriptHolderDto> getTasks() {
    return attackService.selectAll();
  }

  @GetMapping("scripts")
  public List<ScriptHolderDto> getTasks(SelectFilters filters) {
    System.out.println(filters);
    return attackService.selectAll(filters);
  }

  @GetMapping("test")
  public SelectFilters tt() {
    return new SelectFilters();
  }


}
