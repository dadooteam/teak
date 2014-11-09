/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.teak.biz.dao;

import im.dadoo.teak.biz.dao.mapper.PageRowMapper;
import im.dadoo.teak.biz.dao.mapper.SizeRowMapper;
import im.dadoo.teak.data.po.PagePO;

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
public class PageDAO extends BaseDAO<PagePO> {
  
  private static final String SQL_INSERT = 
          "INSERT INTO t_page(name,title,author,html,text,publish_datetime,click) "
          + "VALUES(:name,:title,:author,:html,:text,:publish_datetime,:click)";
  
  private static final String SQL_INC_CLICK_BY_ID = 
          "UPDATE t_page SET click=click+1 WHERE id=:id";
  
  private static final String SQL_UPDATE_ALL_BY_ID = 
          "UPDATE t_page SET name=:name, title=:title,author=:author,html=:html,text=:text,"
          + "publish_datetime=:publish_datetime "
          + "WHERE id=:id";
  
  private static final String SQL_DELETE_BY_ID = 
          "DELETE FROM t_page WHERE id=:id";
  
  private static final String SQL_FIND_BY_ID = 
          "SELECT id,name,title,author,html,text,publish_datetime,click "
          + "FROM t_page WHERE id=:id LIMIT 1";
  
  private static final String SQL_LIST = 
          "SELECT id,name,title,author,html,text,publish_datetime,click "
          + "FROM t_page ORDER BY name ASC";
  
  private static final String SQL_SIZE = 
          "SELECT count(*) AS size FROM t_page";
  
  @Resource
  private PageRowMapper pageRowMapper;
  
  @Resource
  private SizeRowMapper sizeRowMapper;
  
  public PageDAO() {
    super(PagePO.class);
  }
  
  @Override
  public PagePO insert(PagePO pagePO) {
    if (pagePO != null) {
      KeyHolder holder = new GeneratedKeyHolder();
      MapSqlParameterSource sps = new MapSqlParameterSource();
      sps.addValue("name", pagePO.getName());
      sps.addValue("title", pagePO.getTitle());
      sps.addValue("author", pagePO.getAuthor());
      sps.addValue("html", pagePO.getHtml());
      sps.addValue("text", pagePO.getText());
      sps.addValue("publish_datetime", pagePO.getPublishDatetime());
      sps.addValue("click", pagePO.getClick());
      this.jdbcTemplate.update(SQL_INSERT, sps, holder);
      pagePO.setId(holder.getKey().longValue());
    }
    return pagePO;
  }
  
  @Override
  public PagePO updateAllById(PagePO pagePO) {
    if (pagePO != null) {
      MapSqlParameterSource sps = new MapSqlParameterSource();
      sps.addValue("id", pagePO.getId());
      sps.addValue("name", pagePO.getName());
      sps.addValue("title", pagePO.getTitle());
      sps.addValue("author", pagePO.getAuthor());
      sps.addValue("html", pagePO.getHtml());
      sps.addValue("text", pagePO.getText());
      sps.addValue("publish_datetime", pagePO.getPublishDatetime());
      this.jdbcTemplate.update(SQL_UPDATE_ALL_BY_ID, sps);
    }
    return pagePO;
  }
  
  public int incClickById(long id) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    return this.jdbcTemplate.update(SQL_INC_CLICK_BY_ID, sps);
  }
  
  @Override
  public void deleteById(long id) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    this.jdbcTemplate.update(SQL_DELETE_BY_ID, sps);
  }
  
  @Override
  public PagePO findById(long id) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    List<PagePO> pagePOs = this.jdbcTemplate.query(SQL_FIND_BY_ID, sps, this.pageRowMapper);
    if (pagePOs != null && !pagePOs.isEmpty()) {
      return pagePOs.get(0);
    } else {
      return null;
    }
  }
  
  @Override
  public List<PagePO> list() {
    List<PagePO> pagePOs = this.jdbcTemplate.query(SQL_LIST, this.pageRowMapper);
    return pagePOs;
  }
  
  @Override
  public long size() {
    return this.jdbcTemplate.queryForObject(SQL_SIZE, (SqlParameterSource)null, this.sizeRowMapper);
  }
}
