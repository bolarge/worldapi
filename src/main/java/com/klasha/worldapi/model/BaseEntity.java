package com.klasha.worldapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@MappedSuperclass
public class BaseEntity implements Serializable {

  @JsonIgnore
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY, generator="native")
  @GenericGenerator(name = "native", strategy = "native")
  protected Integer id;

  @Column(name = "date_created", updatable = false)
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  protected LocalDate dateCreated = LocalDate.now();

  @Column(name = "date_updated")
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  protected LocalDate dateUpdated = LocalDate.now();

  public BaseEntity(Integer id, LocalDate dateCreated) {
    this.id = id;
    this.dateCreated = dateCreated;
  }
}
