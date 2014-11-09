package im.dadoo.teak.biz.bo;

import im.dadoo.teak.data.po.UserPO;

import com.google.common.base.Optional;

public interface SignBO {
  
  public Optional<UserPO> signin(String name, String password);
  
}
