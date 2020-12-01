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

    final String INSERT_SQL = "INSERT INTO t_user (user_uuid,user_name, portrait_url,login_pwd, mobile,status,email,user_point,user_level, create_time,update_time) VALUES(?,?,null,null,null,1,null,0,0,?,?)";

    final String UPDATE_SQL = "UPDATE user  SET t_user.first_name = ?,  user.last_name = ? WHERE user.id=?";

    final String DELETE_SQL = "DELETE FROM t_user WHERE id =?";

    final   String QUERY_SQL = "SELECT id,first_name as firstName,last_name as lastName from t_user where id=?";
}
