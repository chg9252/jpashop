package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    // 함부로 new 생성 금지. jpa 스팩상 만들어 둔 것이다.
    protected Address() {}

    //생상할 때만 값이 할당되게 해야한다. 그리고 setter 는 아에 제공하지 않는다.
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
