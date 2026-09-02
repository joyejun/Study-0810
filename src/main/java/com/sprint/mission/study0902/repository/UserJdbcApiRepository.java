package com.sprint.mission.study0902.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Repository
@RequiredArgsConstructor
public class UserJdbcApiRepository {

    private final DataSource dataSource;

    public User findById(Integer Id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT * FROM \"User\" WHERE userId = ?");
            statement.setInt(1,Id);
            resultSet = statement.executeQuery(); // 1. 쿼리 실행 후 결과 테이블을 받아옴
            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("job"),
                        resultSet.getString("specialty"),
                        resultSet.getTimestamp("create_at")
                                .toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDateTime()
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (null != resultSet) resultSet.close();
            if (null != statement) statement.close();
            if (null != connection) connection.close();
        }
    }

    public User create(String name, Integer age, String job, String specialty) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            //INSERT 유저 정보
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("INSERT INTO \"User\" (name, age, job, specialty, create_at) VALUES (?, ? ,?, ?, ?);");
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setString(3, job);
            statement.setString(4, specialty);
            statement.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            statement.executeUpdate();

            //SELECT 발급 추가한 유저 정보
            Integer createdUserId = null;
            statement = connection.prepareStatement("SELECT lastval();");
            resultSet = statement.executeQuery();  //실행하고 테이블 반환
            if (resultSet.next()) {
                createdUserId = resultSet.getInt("lastval");
            }

            //SELECT 유저 정보
            statement = connection.prepareStatement("SELECT * FROM \"User\" WHERE id = ?");
            statement.setInt(1, createdUserId);
            resultSet = statement.executeQuery(); // 1. 쿼리 실행 후 결과 테이블을 받아옴
            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("job"),
                        resultSet.getString("specialty"),
                        resultSet.getTimestamp("create_at")
                                .toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDateTime()
                );
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (null != resultSet) resultSet.close();
            if (null != statement) statement.close();
            if (null != connection) connection.close();
        }
    }
}
