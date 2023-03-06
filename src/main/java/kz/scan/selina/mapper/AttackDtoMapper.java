package kz.scan.selina.mapper;

import kz.scan.selina.dto.AttackDto;
import kz.scan.selina.enums.VulnerabilitySeverity;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class AttackDtoMapper {

  @SneakyThrows
  public static AttackDto mapRow(ResultSet rs) {
    AttackDto result = new AttackDto();

    result.attackId = rs.getLong("attack_id");
    result.attackScript = rs.getString("attack_script");
    result.vulnerabilitySeverity = VulnerabilitySeverity.valueOf(rs.getString("vulnerability_severity"));
    result.attackName = rs.getString("attack_name");
    result.executedDate = new Date();

    return result;
  }
}
