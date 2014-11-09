package im.dadoo.teak.biz.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import im.dadoo.teak.data.po.CategoryPO;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryRowMapper implements RowMapper<CategoryPO> {
  @Override
  public CategoryPO mapRow(ResultSet rs, int rowNum) throws SQLException {
    CategoryPO category = new CategoryPO();
    category.setId(rs.getLong("id"));
    category.setName(rs.getString("name"));
    category.setDescription(rs.getString("description"));
    return category;
  }
}
