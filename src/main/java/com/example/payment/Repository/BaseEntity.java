package com.example.payment.Repository;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.example.payment.Common.context.UserContext;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.payment.Common.context.UserContext;

import java.time.LocalDateTime;

@ToString
@Getter
public abstract class BaseEntity {

    protected Integer id;
    protected boolean deleted = false;

    protected LocalDateTime createdAT;
    protected       Integer createdBy;
    protected LocalDateTime updatedAT;
    protected       Integer updatedBy;

    /**
     * BaseEntity (템플릿) 추상클래스 상속받는 엔티티 내 필드들이 수정되었을때 누가, 언제 바꿨는지 기록
     *  - 중요 ! 이번 예시에서는 필드가 갱신되는 엔티티는 Payment 하나에서만 발생하는것으로 진행할 것 !
     * @param userId - 어떤 유저가 값을 바꿨는지 추적하기 위함 <- Auditing
     *  + currentUserId - 어떤 유저가 값을 바꿨는지 추적하기 위함 <- Auditing
     */

    protected BaseEntity(Integer id, Integer userId) {
        this.id = id;
        //deleted
        this.createdAT = LocalDateTime.now();
        this.createdBy = userId;
        this.updatedAT = LocalDateTime.now();
        this.updatedBy = userId;
    }

    protected void updated() {
        this.updatedAT = LocalDateTime.now();
        this.updatedBy = currentUserId;
    }
}
