package com.example.user.sql;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 14:39 on 2020/11/19
 * @version V0.1
 * @classNmae SqlStatements
 */
public interface SqlStatements {

    final String INSERT_SQL = "INSERT INTO user (id,first_name,last_name) VALUES(?,?,?)";

    final String UPDATE_SQL = "UPDATE user  SET user.first_name = ?,  user.last_name = ? WHERE user.id=?";

    final String DELETE_SQL = "DELETE FROM user WHERE id =?";

    final   String QUERY_SQL = "SELECT id,first_name as firstName,last_name as lastName from user where id=?";
}
