package im.dadoo.teak.biz.bo;

import im.dadoo.teak.data.po.CategoryPO;

import java.util.List;
import java.util.Map;

import com.google.common.base.Optional;



public interface CategoryBO {
  
  public Optional<CategoryPO> insert(String name, String description);
  
  public Optional<CategoryPO> updateAllById(long id, String name, String description);
  
  public void deleteById(long id);
  
  public void deleteAll();
  
  public Optional<CategoryPO> findById(long id);
  
  public List<CategoryPO> list();
  
  public Map<Long, CategoryPO> map();
  
  public long size();
  
}
