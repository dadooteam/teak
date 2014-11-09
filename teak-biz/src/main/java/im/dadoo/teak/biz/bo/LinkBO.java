package im.dadoo.teak.biz.bo;

import im.dadoo.teak.data.po.LinkPO;

import java.util.List;

import com.google.common.base.Optional;

public interface LinkBO {
  
  public Optional<LinkPO> insert(String name, String url, String description);
  
  public Optional<LinkPO> update(long id, String name, String url, String description);
  
  public void deleteById(long id);
  
  public Optional<LinkPO> findById(long id);
  
  public List<LinkPO> list();
  
}
