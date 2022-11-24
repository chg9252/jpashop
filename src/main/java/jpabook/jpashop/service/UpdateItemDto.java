package jpabook.jpashop.service;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateItemDto {

    /*사용할 파라미터가 많을때 사용하면 좋은 방식*/

    private String name;
    private int price;
    private int stockQuantity;

}
