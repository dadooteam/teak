/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.teak.biz.bo.impl;

import im.dadoo.teak.biz.bo.PageBO;
import im.dadoo.teak.biz.dao.PageDAO;
import im.dadoo.teak.data.po.PagePO;

import java.util.List;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
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
public class DefaultPageBO implements PageBO {
  
  private static final Logger logger = LoggerFactory.getLogger(DefaultPageBO.class);
  
  @Resource
  private PageDAO pageDAO;
  
  @Override
  public Optional<PagePO> insert(String name, String title, String author, String html) {
    long publishDatetime = System.currentTimeMillis();
    int click = 0;
    String text = Jsoup.parse(html).text();
    
    PagePO pagePO = new PagePO();
    pagePO.setName(name);
    pagePO.setTitle(title);
    pagePO.setAuthor(author);
    pagePO.setHtml(html);
    pagePO.setText(text);
    pagePO.setPublishDatetime(publishDatetime);
    pagePO.setClick(click);
    return Optional.fromNullable(this.pageDAO.insert(pagePO));
  }
  
  @Override
  public Optional<PagePO> update(long id, String name, String title, String author, 
          String html, long publishDatetime) {
    PagePO pagePO = this.pageDAO.findById(id);
    if (pagePO != null) {
      pagePO.setName(name);
      pagePO.setTitle(title);
      pagePO.setAuthor(author);
      pagePO.setHtml(html);
      pagePO.setText(Jsoup.parse(html).text());
      pagePO.setPublishDatetime(publishDatetime);
      this.pageDAO.updateAllById(pagePO);
    }
    return Optional.fromNullable(pagePO);
  }
  
  @Override
  public Optional<PagePO> click(long id) {
    int line = this.pageDAO.incClickById(id);
    Optional<PagePO> pageOPO = Optional.fromNullable(this.pageDAO.findById(id));
    if (line == 1) {
      logger.info(String.format("page{%d} clicked{%d}", id, pageOPO.get().getClick()));
    } else {
      logger.warn(String.format("page{%d} failed to click", id));
    }
    return pageOPO;
  }
  
  @Override
  public void deleteById(long id) {
    this.pageDAO.deleteById(id);
  }
  
  @Override
  public Optional<PagePO> findById(long id) {
    return Optional.fromNullable(this.pageDAO.findById(id));
  }
  
  @Override
  public List<PagePO> list() {
    return this.pageDAO.list();
  }
  
  @Override
  public long size() {
    return this.pageDAO.size();
  }
}
