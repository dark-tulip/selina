package kz.scan.selina.repo;

import kz.scan.selina.controller.SelectFilters;

import java.util.List;

/**
 * @param <T> - dto object type
 * @param <K> - key object type
 */
public interface Repository<T, K> {

  List<T> selectAll(SelectFilters objectId);

  List<T> selectAll();

  boolean insert(T object);

  boolean update(T object);

  boolean delete(K objectId);

}
