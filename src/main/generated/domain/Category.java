package domain;

import domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"), // 중간테이블에 있는 category_id 이다.
            inverseJoinColumns = @JoinColumn(name = "item_id") // "category_item" 테이블에서 Item 테이블로 매핑되는 Id
    )
    private List<Item> items = new ArrayList<>();
    // 중간테이블 매핑을 해줘야 한다. 객체는 컬랙션 컬랙션끼리 해서 다대다관계가 가능하지만 관계형 DB는 컬랙션관계를 양쪽에 가질 수 없다.
    // 때문에 다대 다 관계를풀어낼 수 있도록 중간에 연결할 수 있는 테이블을 넣어주는 것이다.
    // 하지만 실무에서는 확장성이 너무 낮기 때문에 거의 사용하지 않는다.

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    //==연관관계 메서드==//
    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }

}
