package com.sprint.mission.study0902.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)  //id 생성 책임을 database 에 위임한다.
        private Integer id;
        private String  name;
        private Integer age;
        private String job;
        private String specialty;
        private LocalDateTime createAt;

}
