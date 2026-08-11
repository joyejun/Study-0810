package com.example.payment.Repository.product;

import com.example.payment.Repository.BaseEntity;
import com.example.payment.Repository.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@Getter
public class Product extends BaseEntity {
    private static int PRODUCT_CURRENT_ID = 0;
    private static int idGenerator() {
        return ++PRODUCT_CURRENT_ID;
    }

    private String name;
    private int price;
    private int stock;
    private boolean deleted = false;


    private Product(Integer id, String name, int price, int stock, Integer userId) {
        super(id, userId);
        this.name = name;
        this.price = price;
        this.stock = stock;
        //deleted
    }

    private static Product create(String name, int price, int stock, Integer userId) {
        int generatedId = idGenerator();
        return new Product(generatedId, name, price, stock, userId);
    }

    public void buyable() {
        if (this.stock < 1) {
            throw new RuntimeException("구매하시려는 상품의 재고가 존재하지 않습니다.- product" + this.toString());
        }
    }

    public void decrease() {
        this.stock -= 1;
    }

    public void increase() {
        this.stock += 1;
    }
}
