package kz.scan.selina.repo;

public interface Repository<T, K> {

  <T> void insert(T object);

  <K> T select(K key);
}
