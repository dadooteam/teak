/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.teak.biz.dao;

import im.dadoo.teak.biz.dao.mapper.ArchiveRowMapper;
import im.dadoo.teak.biz.dao.mapper.SizeRowMapper;
import im.dadoo.teak.data.po.ArchivePO;

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
public class ArchiveDAO extends BaseDAO<ArchivePO> {
  
  private static final String SQL_INSERT = 
          "INSERT INTO t_archive(title,author,html,text,"
          + "publish_datetime,click,thumbnail_path,category_id) "
          + "VALUES(:title,:author,:html,:text,:publish_datetime,:click,:thumbnail_path,:category_id)";
  
  private static final String SQL_INC_CLICK_BY_ID = 
          "UPDATE t_archive SET click=click+1 WHERE id=:id";
  
  private static final String SQL_UPDATE_ALL_BY_ID = 
          "UPDATE t_archive SET title=:title,author=:author,html=:html,text=:text,"
          + "publish_datetime=:publish_datetime,thumbnail_path=:thumbnail_path,"
          + "category_id=:category_id "
          + "WHERE id=:id";
  
  private static final String SQL_DELETE_BY_ID = 
          "DELETE FROM t_archive WHERE id=:id";
  
  private static final String SQL_FIND_BY_ID = 
          "SELECT id,title,author,html,text,publish_datetime,click,thumbnail_path,category_id "
          + "FROM t_archive WHERE id=:id LIMIT 1";
  
  private static final String SQL_LIST_LIMIT = 
          "SELECT id,title,author,html,text,publish_datetime,click,thumbnail_path,category_id "
          + "FROM t_archive ORDER BY publish_datetime DESC LIMIT :limit";
  
  private static final String SQL_PAGE = 
          "SELECT id,title,author,html,text,publish_datetime,click,thumbnail_path,category_id "
          + "FROM t_archive ORDER BY publish_datetime DESC LIMIT :offset,:pagesize";
  
  private static final String SQL_PAGE_BY_CATEGORY_ID = 
          "SELECT id,title,author,html,text,publish_datetime,click,thumbnail_path,category_id "
          + "FROM t_archive WHERE category_id=:category_id "
          + "ORDER BY publish_datetime DESC LIMIT :offset,:pagesize";
  
  private static final String SQL_SIZE = 
          "SELECT count(*) AS size FROM t_archive";
  
  private static final String SQL_SIZE_BY_CATEGORY_ID = 
          "SELECT count(*) AS size FROM t_archive WHERE category_id=:category_id";
  
  @Resource
  private ArchiveRowMapper archiveRowMapper;
  
  @Resource
  private SizeRowMapper sizeRowMapper;
  
  public ArchiveDAO() {
    super(ArchivePO.class);
  }
  
  @Override
  public ArchivePO insert(ArchivePO archivePO) {
    if (archivePO != null) {
      KeyHolder holder = new GeneratedKeyHolder();
      MapSqlParameterSource sps = new MapSqlParameterSource();
      sps.addValue("title", archivePO.getTitle());
      sps.addValue("author", archivePO.getAuthor());
      sps.addValue("html", archivePO.getHtml());
      sps.addValue("text", archivePO.getText());
      sps.addValue("publish_datetime", archivePO.getPublishDatetime());
      sps.addValue("click", archivePO.getClick());
      sps.addValue("thumbnail_path", archivePO.getThumbnailPath());
      sps.addValue("category_id", archivePO.getCategoryId());
      this.jdbcTemplate.update(SQL_INSERT, sps, holder);
      archivePO.setId(holder.getKey().longValue());
    }
    return archivePO;
  }
  
  @Override
  public ArchivePO updateAllById(ArchivePO archivePO) {
    if (archivePO != null) {
      MapSqlParameterSource sps = new MapSqlParameterSource();
      sps.addValue("id", archivePO.getId());
      sps.addValue("title", archivePO.getTitle());
      sps.addValue("author", archivePO.getAuthor());
      sps.addValue("html", archivePO.getHtml());
      sps.addValue("text", archivePO.getText());
      sps.addValue("publish_datetime", archivePO.getPublishDatetime());
      sps.addValue("thumbnail_path", archivePO.getThumbnailPath());
      sps.addValue("category_id", archivePO.getCategoryId());
      this.jdbcTemplate.update(SQL_UPDATE_ALL_BY_ID, sps);
    }
    return archivePO;
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
  public ArchivePO findById(long id) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    List<ArchivePO> archivePOs = this.jdbcTemplate.query(SQL_FIND_BY_ID, sps, this.archiveRowMapper);
    if (archivePOs != null && !archivePOs.isEmpty()) {
      return archivePOs.get(0);
    } else {
      return null;
    }
  }
  
  public List<ArchivePO> list(int limit) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("limit", limit);
    List<ArchivePO> archivePOs = this.jdbcTemplate.query(SQL_LIST_LIMIT, sps, this.archiveRowMapper);
    return archivePOs;
  }
  
  @Override
  public List<ArchivePO> page(int pagecount, int pagesize) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("offset", pagecount * pagesize);
    sps.addValue("pagesize", pagesize);
    List<ArchivePO> archivePOs = this.jdbcTemplate.query(SQL_PAGE, sps, this.archiveRowMapper);
    return archivePOs;
  }
  
  public List<ArchivePO> pageByCategoryId(long categoryId, int pagecount, int pagesize) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("category_id", categoryId);
    sps.addValue("offset", pagecount * pagesize);
    sps.addValue("pagesize", pagesize);
    List<ArchivePO> archivePOs = 
            this.jdbcTemplate.query(SQL_PAGE_BY_CATEGORY_ID, sps, this.archiveRowMapper);
    return archivePOs;
  }
  
  public long sizeByCategoryId(long categoryId) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("category_id", categoryId);
    return this.jdbcTemplate.queryForObject(SQL_SIZE_BY_CATEGORY_ID, sps, this.sizeRowMapper);
  }
  
  @Override
  public long size() {
    return this.jdbcTemplate.queryForObject(SQL_SIZE, (SqlParameterSource)null, this.sizeRowMapper);
  }
  
}
