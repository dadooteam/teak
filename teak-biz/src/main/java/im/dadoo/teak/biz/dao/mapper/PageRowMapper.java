package im.dadoo.teak.biz.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import im.dadoo.teak.data.po.PagePO;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class PageRowMapper implements RowMapper<PagePO> {
  
  @Override
  public PagePO mapRow(ResultSet rs, int rowNum) throws SQLException {
    PagePO page = new PagePO();
    page.setId(rs.getLong("id"));
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
