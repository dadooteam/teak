package im.dadoo.teak.biz.bo;

import im.dadoo.teak.data.po.PagePO;

import java.util.List;

import com.google.common.base.Optional;

public interface PageBO {
  
  public Optional<PagePO> insert(String name, String title, String author, String html);
  
  public Optional<PagePO> update(long id, String name, String title, String author, String html, long publishDatetime);
  
  public Optional<PagePO> click(long id);
  
  public void deleteById(long id);
  
  public Optional<PagePO> findById(long id);
  
  public List<PagePO> list();
  
  public long size();
  
}
