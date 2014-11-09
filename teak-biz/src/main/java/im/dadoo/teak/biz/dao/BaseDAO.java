/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.teak.biz.dao;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 *
 * @author codekitten
 * @param <T>
 */
public class BaseDAO<T> {
  
  @Resource
  protected NamedParameterJdbcTemplate jdbcTemplate;
  
  protected Class<T> c;
  
  public BaseDAO(Class<T> c) {
    this.c = c;
  }

  public T insert(T obj) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public T updateAllById(T obj) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public void deleteById(long id) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public void deleteAll() {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public T findById(long id) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public List<T> list() {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public List<T> page(int pagecount, int pagesize) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public long size() {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
