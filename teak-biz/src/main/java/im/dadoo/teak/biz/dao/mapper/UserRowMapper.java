package im.dadoo.teak.biz.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import im.dadoo.teak.data.po.UserPO;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class UserRowMapper implements RowMapper<UserPO> {
  @Override
  public UserPO mapRow(ResultSet rs, int rowNum) throws SQLException {
    UserPO user = new UserPO();
    user.setId(rs.getLong("id"));
    user.setName(rs.getString("name"));
    user.setPassword(rs.getString("password"));
    return user;
  }
}
