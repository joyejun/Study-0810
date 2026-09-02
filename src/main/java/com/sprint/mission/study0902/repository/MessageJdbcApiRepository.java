package com.sprint.mission.study0902.repository;

import com.sprint.mission.study0902.controller.dto.UserCreateRequestDto;
import com.sprint.mission.study0902.controller.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MessageJdbcApiRepository {
    private final DataSource dataSource;

    public List<Message> findById(Integer userId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT * FROM \"Message\" WHERE user_id = ?");
            statement.setInt(1,userId);
            List<Message> results = new ArrayList<>();
            while (resultSet.next()) {
                results.add(
                        new Message(
                                resultSet.getInt("id"),
                                resultSet.getString("message"),
                                resultSet.getInt("user_id"),
                                resultSet.getTimestamp("created_at")
                                        .toInstant()
                                        .atZone(ZoneId.systemDefault())
                                        .toLocalDateTime()
                        )
                );
            }
            return Collections.EMPTY_LIST;
        } finally {
            if (null != resultSet) resultSet.close();
            if (null != statement) statement.close();
            if (null != connection) connection.close();
        }
    }


    public Message create(Integer userId, String message) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            //INSERT 유저 정보
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("INSERT INTO \"Message\" (message, user_id, created_at) VALUES (?, ? ,?)");
            statement.setString(1, message);
            statement.setInt(2, userId);
            statement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            statement.executeUpdate();

            //SELECT 발급 추가한 유저 정보
            Integer createdMessageId = null;
            statement = connection.prepareStatement("SELECT lastval();");
            resultSet = statement.executeQuery();  //실행하고 테이블 반환
            if (resultSet.next()) {
                createdMessageId = resultSet.getInt("lastval");
            }

            //SELECT 유저 정보
            statement = connection.prepareStatement("SELECT * FROM \"Message\" WHERE user_id = ?");
            statement.setInt(1, createdMessageId);
            resultSet = statement.executeQuery(); // 1. 쿼리 실행 후 결과 테이블을 받아옴
            if (resultSet.next()) {
                return new Message(
                        resultSet.getInt("id"),
                        resultSet.getString("message"),
                        resultSet.getInt("user_id"),
                        resultSet.getTimestamp("created_at")
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
