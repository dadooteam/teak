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
public class UserService {
  
  @Resource
  private UserDao userDao;
  
  public User save(String name, String password) {
    User user = this.userDao.findByName(name);
    //判断是否重名
    if (user == null) {
      user = User.create(name, password);
      return this.userDao.save(user);
    } else {
      return null;
    }
  }
}
