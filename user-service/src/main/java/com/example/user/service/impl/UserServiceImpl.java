package com.example.user.service.impl;


import com.example.user.entity.User;
import com.example.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
public class UserServiceImpl implements IUserService {

    @Autowired
    private DataSource dataSource;

    private Connection connection;

    @PostConstruct
    public void init() throws SQLException {
        this.connection = dataSource.getConnection();
    }

    public void insert(User user) {
        log.info("insert user ={}",user);
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(INSERT_SQL);
            prepareStatement.setInt(1,user.getId());
            prepareStatement.setString(2,user.getFirstName());
            prepareStatement.setString(3,user.getLastName());
            prepareStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User select(int id) {
        log.info("select user id ={}",id);

        try( PreparedStatement prepareStatement = connection.prepareStatement(QUERY_SQL);) {

            prepareStatement.setInt(1,id);
            ResultSet resultSet = prepareStatement.executeQuery();

            if(resultSet.first()){
                int id1 = resultSet.getInt("id");
                String  firstName = resultSet.getString("firstName");
                String  lastName = resultSet.getString("lastName");
                return new User(id1,firstName,lastName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(int id) {

        try(PreparedStatement prepareStatement = connection.prepareStatement(DELETE_SQL);){
            prepareStatement.setInt(1,id);
            prepareStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(User user) {
        try (PreparedStatement prepareStatement = connection.prepareStatement(UPDATE_SQL);){
            prepareStatement.setInt(3,user.getId());
            prepareStatement.setString(1,user.getFirstName());
            prepareStatement.setString(2,user.getLastName());
            prepareStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
