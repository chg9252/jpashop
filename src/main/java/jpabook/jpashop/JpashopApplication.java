package jpabook.jpashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpashopApplication {

    public static void main(String[] args) {
        testLombok tlb = new testLombok();
        tlb.setData("check check");
        String data = tlb.getData();
        System.out.println(data);

        SpringApplication.run(JpashopApplication.class, args);
    }

}
