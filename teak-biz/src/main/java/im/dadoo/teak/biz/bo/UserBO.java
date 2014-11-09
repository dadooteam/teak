package im.dadoo.teak.biz.bo;

import im.dadoo.teak.data.po.UserPO;

import com.google.common.base.Optional;

public interface UserBO {
  
  public Optional<UserPO> insert(String name, String password);
  
}
