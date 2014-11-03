/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.teak.biz.bo;

import im.dadoo.teak.biz.dao.PageDao;
import im.dadoo.teak.data.po.Page;
import java.util.List;
import javax.annotation.Resource;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author codekitten
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class PageService {
  
  @Resource
  private PageDao pageDao;
  
  public Page save(String name, String title, String author, String html) {
    Long publishDatetime = System.currentTimeMillis();
    Integer click = 0;
    String text = Jsoup.parse(html).text();
    Page page = Page.create(name, title, author, html, text, publishDatetime, click);
    return this.pageDao.save(page);
  }
  
  public Page update(Integer id, String name, String title, String author, 
          String html, Long publishDatetime) {
    Page page = this.pageDao.findById(id);
    page.setName(name);
    page.setTitle(title);
    page.setAuthor(author);
    page.setHtml(html);
    page.setText(Jsoup.parse(html).text());
    page.setPublishDatetime(publishDatetime);
    return this.pageDao.update(page);
  }
  
  public Page click(Integer id) {
    this.pageDao.click(id);
    return this.pageDao.findById(id);
  }
  
  public void deleteById(Integer id) {
    this.pageDao.deleteById(id);
  }
  
  public Page findById(Integer id) {
    return this.pageDao.findById(id);
  }
  
  public List<Page> list() {
    return this.pageDao.list();
  }
  
  public Integer size() {
    return (Integer)this.pageDao.size();
  }
}
