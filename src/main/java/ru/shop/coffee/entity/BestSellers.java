package ru.shop.coffee.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "best_sellers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BestSellers {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @JsonBackReference
  @OneToOne(mappedBy = "bestSellers")
  @JoinColumn(name = "product_id")
  private Product product;
}
