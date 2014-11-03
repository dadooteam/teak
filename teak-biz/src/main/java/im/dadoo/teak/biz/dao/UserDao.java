/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.teak.biz.dao;

import im.dadoo.teak.data.po.User;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author codekitten
 */
@Repository
public class UserDao extends BaseDao<User> {
  
  private static final String SAVE_SQL = 
          "INSERT INTO t_user(name,password) VALUES(:name,:password)";
  
  private static final String FIND_BY_ID_SQL = 
          "SELECT id,name,password FROM t_user WHERE id=:id LIMIT 1";
  
  private static final String FIND_BY_NAME_SQL = 
          "SELECT id,name,password FROM t_user WHERE name=:name LIMIT 1";
  
  private static final String LIST_SQL = 
          "SELECT id,name,password FROM t_user ORDER BY name ASC";
  
  private static final String SIZE_SQL = 
          "SELECT count(*) AS size FROM t_user";
  
  private final RowMapper<User> baseRowMapper;
  
  public UserDao() {
    super(User.class);
    this.baseRowMapper = new BaseRowMapper();
  }
  
  @Override
  public User save(User user) {
    KeyHolder holder = new GeneratedKeyHolder();
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("name", user.getName());
    sps.addValue("password", user.getPassword());
    this.jdbcTemplate.update(SAVE_SQL, sps, holder);
    user.setId(holder.getKey().intValue());
    return user;
  }
  
  @Override
  public User findById(Serializable id) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    List<User> users = this.jdbcTemplate.query(FIND_BY_ID_SQL, sps, this.baseRowMapper);
    if (users != null && !users.isEmpty()) {
      return users.get(0);
    } else {
      return null;
    }
  }
  
  public User findByName(String name) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("name", name);
    List<User> users = this.jdbcTemplate.query(FIND_BY_NAME_SQL, sps, this.baseRowMapper);
    if (!users.isEmpty()) {
      return users.get(0);
    } else {
      return null;
    }
  }
  
  @Override
  public List<User> list() {
    List<User> users = this.jdbcTemplate.query(LIST_SQL, this.baseRowMapper);
    return users;
  }
  
  @Override
  public Serializable size() {
    return (Serializable)this.jdbcTemplate.queryForObject(SIZE_SQL, 
            (SqlParameterSource)null, new RowMapper<Integer>() {
      @Override
      public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getInt("size");
      }
    });
  }
  
  private class BaseRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
      User user = new User();
      user.setId(rs.getInt("id"));
      user.setName(rs.getString("name"));
      user.setPassword(rs.getString("password"));
      return user;
    }
  }
}
