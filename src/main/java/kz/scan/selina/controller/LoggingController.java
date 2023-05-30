package kz.scan.selina.controller;


import lombok.extern.apachecommons.CommonsLog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CommonsLog
public class LoggingController {

  @RequestMapping("/")
  public String checkLogging() {
    log.trace("INFO message");
    log.debug("DEBUG message");
    log.info("INFO message");
    log.warn("WARN message");
    log.error("ERROR message");

    return "See console output to check loggers";
  }
}
