package im.dadoo.teak.biz.dao.mapper;

import im.dadoo.teak.data.po.ArchivePO;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ArchiveRowMapper implements RowMapper<ArchivePO> {
  @Override
  public ArchivePO mapRow(ResultSet rs, int rowNum) throws SQLException {
    ArchivePO archive = new ArchivePO();
    archive.setId(rs.getLong("id"));
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
