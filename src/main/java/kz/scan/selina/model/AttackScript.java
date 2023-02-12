package kz.scan.selina.model;


import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors
public class AttackScript {
  long attackId;
  String attackScript;
}
