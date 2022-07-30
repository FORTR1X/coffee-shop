package ru.shop.coffee.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "subcategory")
public class Subcategory {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Integer id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "url")
  private String url;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  @JsonBackReference
  private Category category;

  @JsonManagedReference
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "subcategory", cascade = CascadeType.ALL)
  private List<Product> products;

}
