/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.teak.biz.dao;

import im.dadoo.teak.biz.dao.mapper.LinkRowMapper;
import im.dadoo.teak.biz.dao.mapper.SizeRowMapper;
import im.dadoo.teak.data.po.LinkPO;

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
public class LinkDAO extends BaseDAO<LinkPO>{
  
  private static final String SQL_INSERT = 
          "INSERT INTO t_link(name,url,description) VALUES(:name,:url,:description)";
  
  private static final String SQL_UPDATE_ALL_BY_ID = 
          "UPDATE t_link SET name=:name,url=:url,description=:description WHERE id=:id";
  
  private static final String SQL_DELETE_BY_ID = 
          "DELETE FROM t_link WHERE id=:id";
  
  private static final String SQL_FIND_BY_ID = 
          "SELECT id,name,url,description FROM t_link WHERE id=:id LIMIT 1";
  
  private static final String SQL_FIND_BY_NAME = 
          "SELECT id,name,url,description FROM t_link WHERE name=:name LIMIT 1";
  
  private static final String SQL_LIST = 
          "SELECT id,name,url,description FROM t_link ORDER BY name ASC";
  
  private static final String SQL_SIZE = 
          "SELECT count(*) AS size FROM t_link";
  
  @Resource
  private LinkRowMapper linkRowMapper;
  
  @Resource
  private SizeRowMapper sizeRowMapper;
  
  public LinkDAO() {
    super(LinkPO.class);
  }
  
  @Override
  public LinkPO insert(LinkPO linkPO) {
    if (linkPO != null) {
      KeyHolder holder = new GeneratedKeyHolder();
      MapSqlParameterSource sps = new MapSqlParameterSource();
      sps.addValue("name", linkPO.getName());
      sps.addValue("url", linkPO.getUrl());
      sps.addValue("description", linkPO.getDescription());
      this.jdbcTemplate.update(SQL_INSERT, sps, holder);
      linkPO.setId(holder.getKey().longValue());
    }
    return linkPO;
  }
  
  @Override
  public LinkPO updateAllById(LinkPO linkPO) {
    if (linkPO != null) {
      MapSqlParameterSource sps = new MapSqlParameterSource();
      sps.addValue("id", linkPO.getId());
      sps.addValue("name", linkPO.getName());
      sps.addValue("url", linkPO.getUrl());
      sps.addValue("description", linkPO.getDescription());
      this.jdbcTemplate.update(SQL_UPDATE_ALL_BY_ID, sps);
    }
    return linkPO;
  }
  
  @Override
  public void deleteById(long id) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    this.jdbcTemplate.update(SQL_DELETE_BY_ID, sps);
  }
  
  @Override
  public LinkPO findById(long id) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    List<LinkPO> linkPOs = this.jdbcTemplate.query(SQL_FIND_BY_ID, sps, this.linkRowMapper);
    if (linkPOs != null && !linkPOs.isEmpty()) {
      return linkPOs.get(0);
    } else {
      return null;
    }
  }
  
  public LinkPO findByName(String name) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("name", name);
    List<LinkPO> linkPOs = this.jdbcTemplate.query(SQL_FIND_BY_NAME, sps, this.linkRowMapper);
    if (!linkPOs.isEmpty()) {
      return linkPOs.get(0);
    } else {
      return null;
    }
  }
  
  @Override
  public List<LinkPO> list() {
    List<LinkPO> linkPOs = this.jdbcTemplate.query(SQL_LIST, this.linkRowMapper);
    return linkPOs;
  }
  
  @Override
  public long size() {
    return this.jdbcTemplate.queryForObject(SQL_SIZE, (SqlParameterSource)null, this.sizeRowMapper);
  }
}
