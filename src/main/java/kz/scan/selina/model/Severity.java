package kz.scan.selina.model;

import kz.scan.selina.enums.VulnerabilitySeverity;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Серъезность выполненного скрипта
 */
@Data
public class Severity {

  long severityId;

  VulnerabilitySeverity severityType;

}
