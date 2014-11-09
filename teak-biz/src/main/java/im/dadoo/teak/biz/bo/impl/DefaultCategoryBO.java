/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.teak.biz.bo.impl;

import im.dadoo.teak.biz.bo.CategoryBO;
import im.dadoo.teak.biz.dao.CategoryDAO;
import im.dadoo.teak.data.po.CategoryPO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Optional;

/**
 *
 * @author codekitten
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DefaultCategoryBO implements CategoryBO {
  
  private static final Logger logger = LoggerFactory.getLogger(DefaultCategoryBO.class);
  
  @Resource
  private CategoryDAO categoryDAO;
  
  @Override
  public Optional<CategoryPO> insert(String name, String description) {
    CategoryPO categoryPO = this.categoryDAO.findByName(name);
    //判断是否出现重名
    if (categoryPO == null) {
      logger.info(String.format("name{%s} unused", name));
      categoryPO = new CategoryPO();
      categoryPO.setName(name);
      categoryPO.setDescription(description);
      return Optional.fromNullable(this.categoryDAO.insert(categoryPO));
    } else {
      logger.info(String.format("name{%s} used by category{%d}", name, categoryPO.getId()));
      return Optional.absent();
    }
  }
  
  @Override
  public Optional<CategoryPO> updateAllById(long id, String name, String description) {
    CategoryPO categoryPO = this.categoryDAO.findById(id);
    if (categoryPO != null) {
      categoryPO.setName(name);
      categoryPO.setDescription(description);
      this.categoryDAO.updateAllById(categoryPO);
    }
    return Optional.fromNullable(categoryPO);
  }
  
  @Override
  public void deleteById(long id) {
    this.categoryDAO.deleteById(id);
  }
  
  @Override
  public void deleteAll() {
    this.categoryDAO.deleteAll();
  }
  
  @Override
  public Optional<CategoryPO> findById(long id) {
    return Optional.fromNullable(this.categoryDAO.findById(id));
  }
  
  @Override
  public List<CategoryPO> list() {
    return this.categoryDAO.list();
  }
  
  @Override
  public Map<Long, CategoryPO> map() {
    Map<Long, CategoryPO> categoryMap = new HashMap<Long, CategoryPO>();
    List<CategoryPO> categories = this.categoryDAO.list();
    if (categories != null && !categories.isEmpty()) {
      for (CategoryPO category : categories) {
        categoryMap.put(category.getId(), category);
      }
    }
    return categoryMap;
  }
  
  @Override
  public long size() {
    return this.categoryDAO.size();
  }
}
