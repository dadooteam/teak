/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.teak.biz.dao;

import im.dadoo.teak.data.po.Page;
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
public class PageDao extends BaseDao<Page> {
  
  private static final String SAVE_SQL = 
          "INSERT INTO t_page(name,title,author,html,text,publish_datetime,click) "
          + "VALUES(:name,:title,:author,:html,:text,:publish_datetime,:click)";
  
  private static final String UPDATE_CLICK_SQL = 
          "UPDATE t_page SET click=click+1 WHERE id=:id";
  
  private static final String UPDATE_SQL = 
          "UPDATE t_page SET name=:name, title=:title,author=:author,html=:html,text=:text,"
          + "publish_datetime=:publish_datetime "
          + "WHERE id=:id";
  
  private static final String DELETE_BY_ID_SQL = 
          "DELETE FROM t_page WHERE id=:id";
  
  private static final String FIND_BY_ID_SQL = 
          "SELECT id,name,title,author,html,text,publish_datetime,click "
          + "FROM t_page WHERE id=:id LIMIT 1";
  
  private static final String LIST_SQL = 
          "SELECT id,name,title,author,html,text,publish_datetime,click "
          + "FROM t_page ORDER BY name ASC";
  
  private static final String SIZE_SQL = 
          "SELECT count(*) AS size FROM t_page";
  
  private final RowMapper<Page> baseRowMapper;
  
  public PageDao() {
    super(Page.class);
    this.baseRowMapper = new BaseRowMapper();
  }
  
  @Override
  public Page save(Page page) {
    KeyHolder holder = new GeneratedKeyHolder();
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("name", page.getName());
    sps.addValue("title", page.getTitle());
    sps.addValue("author", page.getAuthor());
    sps.addValue("html", page.getHtml());
    sps.addValue("text", page.getText());
    sps.addValue("publish_datetime", page.getPublishDatetime());
    sps.addValue("click", page.getClick());
    this.jdbcTemplate.update(SAVE_SQL, sps, holder);
    page.setId(holder.getKey().intValue());
    return page;
  }
  
  //TODO:更新之前需判断name是否重复
  @Override
  public Page update(Page page) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", page.getId());
    sps.addValue("name", page.getName());
    sps.addValue("title", page.getTitle());
    sps.addValue("author", page.getAuthor());
    sps.addValue("html", page.getHtml());
    sps.addValue("text", page.getText());
    sps.addValue("publish_datetime", page.getPublishDatetime());
    this.jdbcTemplate.update(UPDATE_SQL, sps);
    return page;
  }
  
  public void click(Serializable id) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    this.jdbcTemplate.update(UPDATE_CLICK_SQL, sps);
  }
  
  @Override
  public void deleteById(Serializable id) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    this.jdbcTemplate.update(DELETE_BY_ID_SQL, sps);
  }
  
  @Override
  public Page findById(Serializable id) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    List<Page> pages = this.jdbcTemplate.query(FIND_BY_ID_SQL, sps, this.baseRowMapper);
    if (pages != null && !pages.isEmpty()) {
      return pages.get(0);
    } else {
      return null;
    }
  }
  
  @Override
  public List<Page> list() {
    List<Page> pages = this.jdbcTemplate.query(LIST_SQL, this.baseRowMapper);
    return pages;
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
  
  private class BaseRowMapper implements RowMapper<Page> {
    @Override
    public Page mapRow(ResultSet rs, int rowNum) throws SQLException {
      Page page = new Page();
      page.setId(rs.getInt("id"));
      page.setName(rs.getString("name"));
      page.setTitle(rs.getString("title"));
      page.setAuthor(rs.getString("author"));
      page.setHtml(rs.getString("html"));
      page.setText(rs.getString("text"));
      page.setPublishDatetime(rs.getLong("publish_datetime"));
      page.setClick(rs.getInt("click"));
      return page;
    }
  }
}
