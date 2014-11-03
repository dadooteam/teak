/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.teak.biz.dao;

import im.dadoo.teak.data.po.Category;
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
public class CategoryDao extends BaseDao<Category>{
  
  private static final String SAVE_SQL = 
          "INSERT INTO t_category(name,description) VALUES(:name,:description)";
  
  private static final String UPDATE_SQL = 
          "UPDATE t_category SET name=:name,description=:description WHERE id=:id";
  
  private static final String DELETE_BY_ID_SQL = 
          "DELETE FROM t_category WHERE id=:id";
  
  private static final String DELETE_ALL_SQL = 
          "DELETE FROM t_category";
  
  private static final String FIND_BY_ID_SQL = 
          "SELECT id,name,description FROM t_category WHERE id=:id LIMIT 1";
  
  private static final String FIND_BY_NAME_SQL = 
          "SELECT id,name,description FROM t_category WHERE name=:name LIMIT 1";
  
  private static final String LIST_SQL = 
          "SELECT id,name,description FROM t_category ORDER BY name ASC";
  
  private static final String SIZE_SQL = 
          "SELECT count(*) AS size FROM t_category";
 
  private final RowMapper<Category> baseRowMapper;
  
  public CategoryDao() {
    super(Category.class);
    this.baseRowMapper = new BaseRowMapper();
  }
  
  @Override
  public Category save(Category category) {
    KeyHolder holder = new GeneratedKeyHolder();
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("name", category.getName());
    sps.addValue("description", category.getDescription());
    this.jdbcTemplate.update(SAVE_SQL, sps, holder);
    category.setId(holder.getKey().intValue());
    return category;
  }
  
  @Override
  public Category update(Category category) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", category.getId());
    sps.addValue("name", category.getName());
    sps.addValue("description", category.getDescription());
    this.jdbcTemplate.update(UPDATE_SQL, sps);
    return category;
  }
  
  @Override
  public void deleteById(Serializable id) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    this.jdbcTemplate.update(DELETE_BY_ID_SQL, sps);
  }
  
  @Override
  public void deleteAll() {
    this.jdbcTemplate.update(DELETE_ALL_SQL, (MapSqlParameterSource)null);
  }
  
  @Override
  public Category findById(Serializable id) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    List<Category> categories = this.jdbcTemplate.query(FIND_BY_ID_SQL, sps, this.baseRowMapper);
    if (categories != null && !categories.isEmpty()) {
      return categories.get(0);
    } else {
      return null;
    }
  }
  
  public Category findByName(String name) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("name", name);
    List<Category> categories = this.jdbcTemplate.query(FIND_BY_NAME_SQL, sps, this.baseRowMapper);
    if (!categories.isEmpty()) {
      return categories.get(0);
    } else {
      return null;
    }
  }
  
  @Override
  public List<Category> list() {
    List<Category> categories = this.jdbcTemplate.query(LIST_SQL, this.baseRowMapper);
    return categories;
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
  
  private class BaseRowMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
      Category category = new Category();
      category.setId(rs.getInt("id"));
      category.setName(rs.getString("name"));
      category.setDescription(rs.getString("description"));
      return category;
    }
  }
}
