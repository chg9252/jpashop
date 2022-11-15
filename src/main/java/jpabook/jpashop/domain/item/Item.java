package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // jpa 상속전략중 하나. 한테이블에 전부다 때려 넣는다.
@DiscriminatorColumn(name = "dtype")
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    // Category 엔티티와 Item 엔티티 간이 다대다 관계이다.
    @ManyToMany(mappedBy="items")
    private List<Category> categories = new ArrayList<>();


}
