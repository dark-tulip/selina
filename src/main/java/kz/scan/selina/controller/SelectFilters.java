package kz.scan.selina.controller;

import kz.scan.selina.enums.SortOrdering;
import lombok.Data;

import static kz.scan.selina.mapper.DbColumnsDefinition.SEVERITY_TYPE;


@Data
public class SelectFilters {


  public SelectFilters() {
    this.orderByColumn = SEVERITY_TYPE;
    this.orderBy = SortOrdering.UNDEFINED;
    this.selectWhereColumn = "";
    this.selectWhereValue = "";
  }

  /**
   * Сортировка по полю
   */
  public String orderByColumn;

  /**
   * Сортировка по возрастанию / убыванию
   */
  public SortOrdering orderBy;

  /**
   * Выборка по какому полю
   */
  public String selectWhereColumn;

  /**
   * Выборка по полю где значение равно
   */
  public String selectWhereValue;

}
