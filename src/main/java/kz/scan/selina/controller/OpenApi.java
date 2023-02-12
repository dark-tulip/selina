package kz.scan.selina.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/open/api")
public class OpenApi {

  @GetMapping("tasks")
  public String getTasks() {
    return "aaa";
  }

}
