/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.teak.biz.bo.impl;

import im.dadoo.teak.biz.bo.ArchiveBO;
import im.dadoo.teak.biz.dao.ArchiveDAO;
import im.dadoo.teak.data.po.ArchivePO;

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
public class DefaultArchiveBO implements ArchiveBO {
  
  private static final Logger logger = LoggerFactory.getLogger(DefaultArchiveBO.class);
  
  @Resource
  private ArchiveDAO archiveDAO;
  
  @Override
  public Optional<ArchivePO> insert(String title, String author, String html, String thumbnailPath, long categoryId) {
    long publishDatetime = System.currentTimeMillis();
    int click = 0;
    String text = Jsoup.parse(html).text();
    ArchivePO archivePO = new ArchivePO();
    archivePO.setTitle(title);
    archivePO.setAuthor(author);
    archivePO.setHtml(html);
    archivePO.setText(text);
    archivePO.setPublishDatetime(publishDatetime);
    archivePO.setClick(click);
    archivePO.setThumbnailPath(thumbnailPath);
    archivePO.setCategoryId(categoryId); 
    return Optional.fromNullable(this.archiveDAO.insert(archivePO));
  }
  
  @Override
  public Optional<ArchivePO> updateAllById(long id, String title, String author, String html, Long publishDatetime, String thumbnailPath, long categoryId) {
    ArchivePO archivePO = this.archiveDAO.findById(id);
    archivePO.setTitle(title);
    archivePO.setAuthor(author);
    archivePO.setHtml(html);
    archivePO.setText(Jsoup.parse(html).text());
    archivePO.setPublishDatetime(publishDatetime);
    archivePO.setThumbnailPath(thumbnailPath);
    archivePO.setCategoryId(categoryId);
    return Optional.fromNullable(this.archiveDAO.updateAllById(archivePO));
  }

  @Override
  public Optional<ArchivePO> click(long id) {
    int line = this.archiveDAO.incClickById(id);
    Optional<ArchivePO> archiveOPO = Optional.fromNullable(this.archiveDAO.findById(id));
    if (line == 1) {
      logger.info(String.format("archive{%d} clicked{%d}", id, archiveOPO.get().getClick()));
    } else {
      logger.warn(String.format("archive{%d} failed to click", id));
    }
    return archiveOPO;
  }
  
  @Override
  public void deleteById(long id) {
    this.archiveDAO.deleteById(id);
  }
  
  @Override
  public Optional<ArchivePO> findById(long id) {
    return Optional.fromNullable(this.archiveDAO.findById(id));
  }
  
  @Override
  public List<ArchivePO> list(int limit) {
    return this.archiveDAO.list(limit);
  }
  
  @Override
  public List<ArchivePO> list(int pagecount, int pagesize) {
    return this.archiveDAO.page(pagecount, pagesize);
  }

  @Override
  public List<ArchivePO> pageByCategoryId(long categoryId, int pagecount, int pagesize) {
    return this.archiveDAO.pageByCategoryId(categoryId, pagecount, pagesize);
  }

  @Override
  public long size() {
    return this.archiveDAO.size();
  }

  @Override
  public long sizeByCategoryId(long categoryId) {
    return this.archiveDAO.sizeByCategoryId(categoryId);
  }
}
