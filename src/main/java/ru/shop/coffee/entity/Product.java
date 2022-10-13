package ru.shop.coffee.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "product")
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

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "subcategory_id")
  private Subcategory subcategory;

  @JsonManagedReference
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id")
  private BestSellers bestSellers;

}
