package im.dadoo.teak.biz.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import im.dadoo.teak.data.po.LinkPO;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class LinkRowMapper implements RowMapper<LinkPO> {
  
  @Override
  public LinkPO mapRow(ResultSet rs, int rowNum) throws SQLException {
    LinkPO link = new LinkPO();
    link.setId(rs.getLong("id"));
    link.setName(rs.getString("name"));
    link.setUrl(rs.getString("url"));
    link.setDescription(rs.getString("description"));
    return link;
  }
}
