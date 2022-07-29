package ru.shop.coffee.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Product {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Integer id;

  @Column(name = "header", nullable = false)
  private String header;

  @Column(name = "price", nullable = false)
  private int price;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "subcatId", nullable = false)
  private Integer subcatId;

}
