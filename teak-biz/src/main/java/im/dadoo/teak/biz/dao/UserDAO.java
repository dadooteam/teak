/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.teak.biz.dao;

import im.dadoo.teak.biz.dao.mapper.SizeRowMapper;
import im.dadoo.teak.biz.dao.mapper.UserRowMapper;
import im.dadoo.teak.data.po.UserPO;

import java.util.List;

import javax.annotation.Resource;

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
public class UserDAO extends BaseDAO<UserPO> {
  
  private static final String SQL_INSERT = 
          "INSERT INTO t_user(name,password) VALUES(:name,:password)";
  
  private static final String SQL_FIND_BY_ID = 
          "SELECT id,name,password FROM t_user WHERE id=:id LIMIT 1";
  
  private static final String SQL_FIND_BY_NAME = 
          "SELECT id,name,password FROM t_user WHERE name=:name LIMIT 1";
  
  private static final String SQL_LIST = 
          "SELECT id,name,password FROM t_user ORDER BY name ASC";
  
  private static final String SQL_SIZE = 
          "SELECT count(*) AS size FROM t_user";
  
  @Resource
  private UserRowMapper userRowMapper;
  
  @Resource
  private SizeRowMapper sizeRowMapper;
  
  public UserDAO() {
    super(UserPO.class);
  }
  
  @Override
  public UserPO insert(UserPO userPO) {
    if (userPO != null) {
      KeyHolder holder = new GeneratedKeyHolder();
      MapSqlParameterSource sps = new MapSqlParameterSource();
      sps.addValue("name", userPO.getName());
      sps.addValue("password", userPO.getPassword());
      this.jdbcTemplate.update(SQL_INSERT, sps, holder);
      userPO.setId(holder.getKey().longValue());
    }
    return userPO;
  }
  
  @Override
  public UserPO findById(long id) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    List<UserPO> userPOs = this.jdbcTemplate.query(SQL_FIND_BY_ID, sps, this.userRowMapper);
    if (userPOs != null && !userPOs.isEmpty()) {
      return userPOs.get(0);
    } else {
      return null;
    }
  }
  
  public UserPO findByName(String name) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("name", name);
    List<UserPO> userPOs = this.jdbcTemplate.query(SQL_FIND_BY_NAME, sps, this.userRowMapper);
    if (!userPOs.isEmpty()) {
      return userPOs.get(0);
    } else {
      return null;
    }
  }
  
  @Override
  public List<UserPO> list() {
    List<UserPO> userPOs = this.jdbcTemplate.query(SQL_LIST, this.userRowMapper);
    return userPOs;
  }
  
  @Override
  public long size() {
    return this.jdbcTemplate.queryForObject(SQL_SIZE, (SqlParameterSource)null, this.sizeRowMapper);
  }
  
}
