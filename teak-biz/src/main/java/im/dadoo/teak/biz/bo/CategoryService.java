/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.teak.biz.bo;

import im.dadoo.teak.biz.dao.CategoryDao;
import im.dadoo.teak.data.po.Category;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author codekitten
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CategoryService {
  
  @Resource
  private CategoryDao categoryDao;
  
  public Category save(String name, String description) {
    Category category = this.categoryDao.findByName(name);
    //判断是否出现重名
    if (category == null) {
      category = Category.create(name, description);
      return this.categoryDao.save(category);
    } else {
      return null;
    }
  }
  
  public Category update(Integer id, String name, String description) {
    Category category = this.categoryDao.findById(id);
    if (category != null) {
      category.setName(name);
      category.setDescription(description);
      return this.categoryDao.update(category);
    } else {
      return category;
    }
  }
  
  public void deleteById(Integer id) {
    this.categoryDao.deleteById(id);
  }
  
  public void deleteAll() {
    this.categoryDao.deleteAll();
  }
  
  public Category findById(Integer id) {
    return this.categoryDao.findById(id);
  }
  
  public List<Category> list() {
    return this.categoryDao.list();
  }
  
  public Map<Integer, Category> map() {
    List<Category> categories = this.categoryDao.list();
    if (categories != null) {
      Map<Integer, Category> cm = new HashMap<>();
      for (Category category : categories) {
        cm.put(category.getId(), category);
      }
      return cm;
    } else {
      return null;
    }
  }
  
  public Integer size() {
    return (Integer)this.categoryDao.size();
  }
}
