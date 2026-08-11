package com.example.payment.Repository.user;

import com.example.payment.Repository.BaseEntity;
import lombok.Getter;
import lombok.ToString;

@ToString(callSuper = true)
@Getter
public class User extends BaseEntity {
    private static int USER_CURRENT_ID = 0;
    private static int idGenerator() {
        return ++USER_CURRENT_ID;
    }

    private String name;
    private UserGrade grade = UserGrade.BRONZE;
    private int point = 0;


    private User(Integer id, String name, Integer userId) {
        super(id, userId);
        this.name = name;
        //deleted
    }

    private static User create(String name, Integer userId) {
        int generatedId = idGenerator();
        return new User(generatedId, name, userId);
    }

    public void earn(int paidPrice) {
        this.point += (int) (paidPrice * this.grade.getEarningRate());
    }


}
