/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.teak.biz.bo;

import im.dadoo.teak.biz.dao.UserDao;
import im.dadoo.teak.data.po.User;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author codekitten
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SignService {
  
  @Resource
  private UserDao userDao;
  
  public User signin(String name, String password) {
    User user = this.userDao.findByName(name);
    if (user != null && user.getPassword().equals(password)) {
      return user;
    } else {
      return null;
    }
  }
}
