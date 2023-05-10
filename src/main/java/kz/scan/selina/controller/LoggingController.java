package kz.scan.selina.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggingController {

  Logger logger = LoggerFactory.getLogger(LoggingController.class);

  @RequestMapping("/")
  public String checkLogging() {
    logger.trace("INFO message");
    logger.debug("DEBUG message");
    logger.info("INFO message");
    logger.warn("WARN message");
    logger.error("ERROR message");

    return "See console output to check loggers";
  }
}
