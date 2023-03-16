package kz.scan.selina.service;

import kz.scan.selina.controller.SelectFilters;
import kz.scan.selina.dto.ScriptHolderDto;
import kz.scan.selina.enums.SortOrdering;
import kz.scan.selina.mapper.AttackDtoMapper;
import kz.scan.selina.repo.Repository;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static kz.scan.selina.config.DbConfig.openConnection;


@Service
public class AttackService implements Repository<ScriptHolderDto, Long> {

  @Override
  public List<ScriptHolderDto> select(SelectFilters filterBy) {
    StringBuilder sql = new StringBuilder("SELECT\n" +
      "    script_id, attack_name, severity_type, attack_script\n" +
      "FROM attack_script\n" +
      "    LEFT JOIN attack   USING(attack_id)\n" +
      "    LEFT JOIN severity USING(severity_id) ");

    appendFilters(filterBy, sql);

    try (Connection con = openConnection()) {
      PreparedStatement query = con.prepareStatement(sql.toString());
      ResultSet rs = query.executeQuery();

      List<ScriptHolderDto> result = new ArrayList<>();
      while(rs.next()) {
        result.add(AttackDtoMapper.mapRow(rs));
      }

      return result;

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private static void appendFilters(SelectFilters filterBy, StringBuilder sql) {
    if (!filterBy.selectWhereColumn.isBlank() &&
      !filterBy.selectWhereValue.isBlank()) {
      sql.append(" WHERE ");
      sql.append(filterBy.selectWhereColumn);
      sql.append(" = ");
      sql.append(filterBy.selectWhereValue);
    }

    if (!filterBy.orderByColumn.isBlank() && !SortOrdering.UNDEFINED.equals(filterBy.orderBy)) {
      sql.append(" ORDER BY ");
      sql.append(filterBy.orderByColumn);
      sql.append(" ");
      sql.append(filterBy.orderBy);
    }
  }


  @Override
  public List<ScriptHolderDto> selectAll() {
    try (Connection con = openConnection()) {
      PreparedStatement query = con.prepareStatement("SELECT\n" +
        "    script_id, attack_name, severity_type, attack_script\n" +
        "FROM attack_script\n" +
        "    LEFT JOIN attack   USING(attack_id)\n" +
        "    LEFT JOIN severity USING(severity_id); ");

      ResultSet rs = query.executeQuery();

      List<ScriptHolderDto> result = new ArrayList<>();
      while(rs.next()) {
        result.add(AttackDtoMapper.mapRow(rs));
      }

      return result;

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean insert(ScriptHolderDto object) {
    return false;
  }

  @Override
  public boolean update(ScriptHolderDto object) {
    return false;
  }

  @Override
  public boolean delete(Long objectId) {
    return false;
  }

}
