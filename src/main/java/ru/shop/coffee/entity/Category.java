package ru.shop.coffee.entity;

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
@Table(name = "category")
public class Category {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Integer id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "url", nullable = false)
  private String url;

  @JsonManagedReference
  @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Subcategory> subcategoryList;
}
