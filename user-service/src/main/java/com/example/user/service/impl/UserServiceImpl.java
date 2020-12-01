package com.example.user.service.impl;


import com.example.user.entity.User;
import com.example.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import static com.example.user.sql.SqlStatements.*;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 16:52 on 2020/11/2
 * @version V0.1
 * @classNmae HelloServiceImpl
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private DataSource dataSource;

    private Connection connection;

    @PostConstruct
    public void init() throws SQLException {
        this.connection = dataSource.getConnection();
    }

    /**
     *     "INSERT INTO user
     *     (user_uuid,user_name,
     *      portrait_url,login_pwd,
     *      mobile,status,email,
     *      user_point,user_level,
     *      create_time,update_time)
     *      VALUES(?,?,null,null,null,1,null,0,0,?,?)";
     * @param user
     */
    public void insert(User user) {
        log.info("insert user ={}",user);
        try {
            PreparedStatement prepareStatement = getConnection().prepareStatement(INSERT_SQL);
            user.setUserUuid(UUID.randomUUID().toString().replace("-",""));
            prepareStatement.setString(1,user.getUserUuid());
            prepareStatement.setString(2,user.getUserName());
            long createTime = System.currentTimeMillis();
            user.setCreateTime(createTime);
            user.setUpdateTime(createTime);
            prepareStatement.setLong(3,user.getCreateTime());
            prepareStatement.setLong(4,createTime);
            prepareStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            if(connection==null||connection.isClosed()){
                connection = dataSource.getConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public User select(int id) {
        log.info("select user id ={}",id);

        try(PreparedStatement prepareStatement = getConnection().prepareStatement(QUERY_SQL);) {

            prepareStatement.setInt(1,id);
            ResultSet resultSet = prepareStatement.executeQuery();

            if(resultSet.first()){
                int id1 = resultSet.getInt("id");
                String  firstName = resultSet.getString("firstName");
                String  lastName = resultSet.getString("lastName");
                return new User();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(int id) {

        try(PreparedStatement prepareStatement = getConnection().prepareStatement(DELETE_SQL);){
            prepareStatement.setInt(1,id);
            prepareStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(User user) {
        try (PreparedStatement prepareStatement = getConnection().prepareStatement(UPDATE_SQL);){
//            prepareStatement.setInt(3,user.getId());
//            prepareStatement.setString(1,user.getFirstName());
//            prepareStatement.setString(2,user.getLastName());
            prepareStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
