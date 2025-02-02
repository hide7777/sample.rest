package com.example.sampleapi.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name = "Users")
public class Users {

  @Id
  //@GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonProperty("Id")
  @Column(name = "Id")
  private Long id;

  @JsonProperty("Name")
  @Column(name = "Name")
  private String name;

  @JsonProperty("Email")
  @Column(name = "Email")
  private String email;
  
  @JsonProperty("Startdate")
  @Column(name = "Startdate")
  private Date startdate;

  @JsonProperty("Lank")
  @Column(name = "Lank")
  private Integer lank;

}
