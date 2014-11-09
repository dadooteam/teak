package im.dadoo.teak.biz.bo;

import im.dadoo.teak.data.po.ArchivePO;

import java.util.List;

import com.google.common.base.Optional;

public interface ArchiveBO {
  
  public Optional<ArchivePO> insert(String title, String author, String html, String thumbnailPath,
      long categoryId);
  
  public Optional<ArchivePO> updateAllById(long id, String title, String author, String html, Long publishDatetime,
      String thumbnailPath, long categoryId);
  
  public Optional<ArchivePO> click(long id);
  
  public void deleteById(long id);
  
  public Optional<ArchivePO> findById(long id);
  
  public List<ArchivePO> list(int limit);
  
  public List<ArchivePO> list(int pagecount, int pagesize);
  
  public List<ArchivePO> pageByCategoryId(long categoryId, int pagecount, int pagesize);
  
  public long size();
  
  public long sizeByCategoryId(long categoryId);
  
}
