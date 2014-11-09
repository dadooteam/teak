/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.teak.biz.dao;

import im.dadoo.teak.biz.dao.mapper.CategoryRowMapper;
import im.dadoo.teak.biz.dao.mapper.SizeRowMapper;
import im.dadoo.teak.data.po.CategoryPO;

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
public class CategoryDAO extends BaseDAO<CategoryPO> {
  
  private static final String SQL_INSERT = 
          "INSERT INTO t_category(name,description) VALUES(:name,:description)";
  
  private static final String SQL_UPDATE_ALL_BY_ID = 
          "UPDATE t_category SET name=:name,description=:description WHERE id=:id";
  
  private static final String SQL_DELETE_BY_ID = 
          "DELETE FROM t_category WHERE id=:id";
  
  private static final String SQL_DELETE_ALL = 
          "DELETE FROM t_category";
  
  private static final String SQL_FIND_BY_ID = 
          "SELECT id,name,description FROM t_category WHERE id=:id LIMIT 1";
  
  private static final String SQL_FIND_BY_NAME = 
          "SELECT id,name,description FROM t_category WHERE name=:name LIMIT 1";
  
  private static final String SQL_LIST = 
          "SELECT id,name,description FROM t_category ORDER BY name ASC";
  
  private static final String SQL_SIZE = 
          "SELECT count(*) AS size FROM t_category";
 
  @Resource
  private CategoryRowMapper categoryRowMapper;
  
  @Resource
  private SizeRowMapper sizeRowMapper;
  
  public CategoryDAO() {
    super(CategoryPO.class);
  }
  
  @Override
  public CategoryPO insert(CategoryPO categoryPO) {
    if (categoryPO != null) {
      KeyHolder holder = new GeneratedKeyHolder();
      MapSqlParameterSource sps = new MapSqlParameterSource();
      sps.addValue("name", categoryPO.getName());
      sps.addValue("description", categoryPO.getDescription());
      this.jdbcTemplate.update(SQL_INSERT, sps, holder);
      categoryPO.setId(holder.getKey().longValue());
    }
    return categoryPO;
  }
  
  @Override
  public CategoryPO updateAllById(CategoryPO categoryPO) {
    if (categoryPO != null) {
      MapSqlParameterSource sps = new MapSqlParameterSource();
      sps.addValue("id", categoryPO.getId());
      sps.addValue("name", categoryPO.getName());
      sps.addValue("description", categoryPO.getDescription());
      this.jdbcTemplate.update(SQL_UPDATE_ALL_BY_ID, sps);
    }
    return categoryPO;
  }
  
  @Override
  public void deleteById(long id) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    this.jdbcTemplate.update(SQL_DELETE_BY_ID, sps);
  }
  
  @Override
  public void deleteAll() {
    this.jdbcTemplate.update(SQL_DELETE_ALL, (MapSqlParameterSource)null);
  }
  
  @Override
  public CategoryPO findById(long id) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    List<CategoryPO> categoryPOs = this.jdbcTemplate.query(SQL_FIND_BY_ID, sps, this.categoryRowMapper);
    if (categoryPOs != null && !categoryPOs.isEmpty()) {
      return categoryPOs.get(0);
    } else {
      return null;
    }
  }
  
  public CategoryPO findByName(String name) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("name", name);
    List<CategoryPO> categoryPOs = this.jdbcTemplate.query(SQL_FIND_BY_NAME, sps, this.categoryRowMapper);
    if (!categoryPOs.isEmpty()) {
      return categoryPOs.get(0);
    } else {
      return null;
    }
  }
  
  @Override
  public List<CategoryPO> list() {
    List<CategoryPO> categoryPOs = this.jdbcTemplate.query(SQL_LIST, this.categoryRowMapper);
    return categoryPOs;
  }
  
  @Override
  public long size() {
    return this.jdbcTemplate.queryForObject(SQL_SIZE, (SqlParameterSource)null, this.sizeRowMapper);
  }
  
}
