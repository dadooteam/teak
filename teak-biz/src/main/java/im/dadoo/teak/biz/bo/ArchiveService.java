/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.teak.biz.bo;

import im.dadoo.teak.biz.dao.ArchiveDao;
import im.dadoo.teak.data.po.Archive;
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
public class ArchiveService {
  
  @Resource
  private ArchiveDao archiveDao;
  
  public Archive save(String title, String author, String html, 
          String thumbnailPath, Integer categoryId) {
    Long publishDatetime = System.currentTimeMillis();
    Integer click = 0;
    String text = Jsoup.parse(html).text();
    Archive archive = Archive.create(title, author, html, text, 
            publishDatetime, click, thumbnailPath, categoryId);
    return this.archiveDao.save(archive);
  }
  
  public Archive update(Integer id, String title, String author, String html, Long publishDatetime,
          String thumbnailPath, Integer categoryId) {
    Archive archive = this.archiveDao.findById(id);
    archive.setTitle(title);
    archive.setAuthor(author);
    archive.setHtml(html);
    archive.setText(Jsoup.parse(html).text());
    archive.setPublishDatetime(publishDatetime);
    archive.setThumbnailPath(thumbnailPath);
    archive.setCategoryId(categoryId);
    return this.archiveDao.update(archive);
  }
  
  public Archive click(Integer id) {
    this.archiveDao.click(id);
    return this.archiveDao.findById(id);
  }
  
  public void deleteById(Integer id) {
    this.archiveDao.deleteById(id);
  }
  
  public Archive findById(Integer id) {
    return this.archiveDao.findById(id);
  }
  
  public List<Archive> list(Integer limit) {
    return this.archiveDao.list(limit);
  }
  
  public List<Archive> list(Integer pagecount, Integer pagesize) {
    return this.archiveDao.list(pagecount, pagesize);
  }
  
  public List<Archive> listByCategoryId(Integer categoryId, Integer pagecount, Integer pagesize) {
    return this.archiveDao.listByCategoryId(categoryId, pagecount, pagesize);
  }
  
  public Integer size() {
    return (Integer)this.archiveDao.size();
  }
  
  public Integer sizeByCategoryId(Integer categoryId) {
    return (Integer)this.archiveDao.sizeByCategoryId(categoryId);
  }
}
