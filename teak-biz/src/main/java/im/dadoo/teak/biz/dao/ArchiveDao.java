/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.teak.biz.dao;

import im.dadoo.teak.data.po.Archive;
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
public class ArchiveDao extends BaseDao<Archive> {
  
  private static final String SAVE_SQL = 
          "INSERT INTO t_archive(title,author,html,text,"
          + "publish_datetime,click,thumbnail_path,category_id) "
          + "VALUES(:title,:author,:html,:text,:publish_datetime,:click,:thumbnail_path,:category_id)";
  
  private static final String UPDATE_CLICK_SQL = 
          "UPDATE t_archive SET click=click+1 WHERE id=:id";
  
  private static final String UPDATE_SQL = 
          "UPDATE t_archive SET title=:title,author=:author,html=:html,text=:text,"
          + "publish_datetime=:publish_datetime,thumbnail_path=:thumbnail_path,"
          + "category_id=:category_id "
          + "WHERE id=:id";
  
  private static final String DELETE_BY_ID_SQL = 
          "DELETE FROM t_archive WHERE id=:id";
  
  private static final String FIND_BY_ID_SQL = 
          "SELECT id,title,author,html,text,publish_datetime,click,thumbnail_path,category_id "
          + "FROM t_archive WHERE id=:id LIMIT 1";
  
  private static final String LIST_LIMIT_SQL = 
          "SELECT id,title,author,html,text,publish_datetime,click,thumbnail_path,category_id "
          + "FROM t_archive ORDER BY publish_datetime DESC LIMIT :limit";
  
  private static final String LIST_PAGINATION_SQL = 
          "SELECT id,title,author,html,text,publish_datetime,click,thumbnail_path,category_id "
          + "FROM t_archive ORDER BY publish_datetime DESC LIMIT :pagecount,:pagesize";
  
  private static final String LIST_BY_CATEGORY_ID_PAGINATION_SQL = 
          "SELECT id,title,author,html,text,publish_datetime,click,thumbnail_path,category_id "
          + "FROM t_archive WHERE category_id=:category_id "
          + "ORDER BY publish_datetime DESC LIMIT :pagecount,:pagesize";
  
  private static final String SIZE_SQL = 
          "SELECT count(*) AS size FROM t_archive";
  
  private static final String SIZE_BY_CATEGORY_ID_SQL = 
          "SELECT count(*) AS size FROM t_archive WHERE category_id=:category_id";
  
  private final RowMapper<Archive> baseRowMapper;
  
  public ArchiveDao() {
    super(Archive.class);
    this.baseRowMapper = new BaseRowMapper();
  }
  
  @Override
  public Archive save(Archive archive) {
    KeyHolder holder = new GeneratedKeyHolder();
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("title", archive.getTitle());
    sps.addValue("author", archive.getAuthor());
    sps.addValue("html", archive.getHtml());
    sps.addValue("text", archive.getText());
    sps.addValue("publish_datetime", archive.getPublishDatetime());
    sps.addValue("click", archive.getClick());
    sps.addValue("thumbnail_path", archive.getThumbnailPath());
    sps.addValue("category_id", archive.getCategoryId());
    this.jdbcTemplate.update(SAVE_SQL, sps, holder);
    archive.setId(holder.getKey().intValue());
    return archive;
  }
  
  @Override
  public Archive update(Archive archive) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", archive.getId());
    sps.addValue("title", archive.getTitle());
    sps.addValue("author", archive.getAuthor());
    sps.addValue("html", archive.getHtml());
    sps.addValue("text", archive.getText());
    sps.addValue("publish_datetime", archive.getPublishDatetime());
    sps.addValue("thumbnail_path", archive.getThumbnailPath());
    sps.addValue("category_id", archive.getCategoryId());
    this.jdbcTemplate.update(UPDATE_SQL, sps);
    return archive;
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
  public Archive findById(Serializable id) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    List<Archive> archives = this.jdbcTemplate.query(FIND_BY_ID_SQL, sps, this.baseRowMapper);
    if (archives != null && !archives.isEmpty()) {
      return archives.get(0);
    } else {
      return null;
    }
  }
  
  public List<Archive> list(Integer limit) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("limit", limit);
    List<Archive> archives = this.jdbcTemplate.query(LIST_LIMIT_SQL, sps, this.baseRowMapper);
    return archives;
  }
  
  @Override
  public List<Archive> list(Integer pagecount, Integer pagesize) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("pagecount", pagecount);
    sps.addValue("pagesize", pagesize);
    List<Archive> archives = this.jdbcTemplate.query(LIST_PAGINATION_SQL, sps, this.baseRowMapper);
    return archives;
  }
  
  public List<Archive> listByCategoryId(Integer categoryId, Integer pagecount, Integer pagesize) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("category_id", categoryId);
    sps.addValue("pagecount", pagecount);
    sps.addValue("pagesize", pagesize);
    List<Archive> archives = 
            this.jdbcTemplate.query(LIST_BY_CATEGORY_ID_PAGINATION_SQL, sps, this.baseRowMapper);
    return archives;
  }
  
  public Serializable sizeByCategoryId(Integer categoryId) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("category_id", categoryId);
    return (Serializable)this.jdbcTemplate.queryForObject(SIZE_BY_CATEGORY_ID_SQL, 
            sps, new RowMapper<Integer>() {
      @Override
      public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getInt("size");
      }
    });
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
  
  private class BaseRowMapper implements RowMapper<Archive> {
    @Override
    public Archive mapRow(ResultSet rs, int rowNum) throws SQLException {
      Archive archive = new Archive();
      archive.setId(rs.getInt("id"));
      archive.setTitle(rs.getString("title"));
      archive.setAuthor(rs.getString("author"));
      archive.setHtml(rs.getString("html"));
      archive.setText(rs.getString("text"));
      archive.setPublishDatetime(rs.getLong("publish_datetime"));
      archive.setClick(rs.getInt("click"));
      archive.setThumbnailPath(rs.getString("thumbnail_path"));
      archive.setCategoryId(rs.getInt("category_id"));
      return archive;
    }
  }
}
