package kz.scan.selina.service;

import kz.scan.selina.dto.AttackDto;
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
public class AttackService implements Repository<AttackDto, Long> {

  @Override
  public AttackDto select(Long objectId) {
    try (Connection con = openConnection()) {
      PreparedStatement query = con.prepareStatement("SQL_HERE");
      query.setLong(1, objectId);
      ResultSet rs = query.executeQuery();
      rs.next();
      return  AttackDtoMapper.mapRow(rs);

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }


  @Override
  public List<AttackDto> selectAll() {
    try (Connection con = openConnection()) {
      PreparedStatement query = con.prepareStatement("SQL_HERE");
      ResultSet rs = query.executeQuery();

      List<AttackDto> result = new ArrayList<>();
      while(rs.next()) {
        result.add(AttackDtoMapper.mapRow(rs));
      }

      return result;

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean insert(AttackDto object) {
    return false;
  }

  @Override
  public boolean update(AttackDto object) {
    return false;
  }

  @Override
  public boolean delete(Long objectId) {
    return false;
  }

}
