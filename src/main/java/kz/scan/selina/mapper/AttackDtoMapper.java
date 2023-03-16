package kz.scan.selina.mapper;

import kz.scan.selina.dto.ScriptHolderDto;
import kz.scan.selina.enums.VulnerabilitySeverity;
import lombok.SneakyThrows;

import java.sql.ResultSet;

import static kz.scan.selina.mapper.DbColumnsDefinition.*;

public class AttackDtoMapper {

  @SneakyThrows
  public static ScriptHolderDto mapRow(ResultSet rs) {
    ScriptHolderDto result = new ScriptHolderDto();

    result.scriptId = rs.getLong(SCRIPT_ID);
    result.attackName = rs.getString(ATTACK_NAME);
    result.severityType = VulnerabilitySeverity.valueOf(rs.getString(SEVERITY_TYPE));
    result.attackScript = rs.getString(ATTACK_SCRIPT);

    return result;
  }
}
