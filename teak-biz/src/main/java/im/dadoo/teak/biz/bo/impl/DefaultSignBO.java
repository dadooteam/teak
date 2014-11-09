/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.teak.biz.bo.impl;

import im.dadoo.teak.biz.bo.SignBO;
import im.dadoo.teak.biz.dao.UserDAO;
import im.dadoo.teak.data.po.UserPO;

import javax.annotation.Resource;

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
public class DefaultSignBO implements SignBO {
  
  @Resource
  private UserDAO userDAO;
  
  @Override
  public Optional<UserPO> signin(String name, String password) {
    UserPO userPO = this.userDAO.findByName(name);
    if (userPO != null && userPO.getPassword().equals(password)) {
      return Optional.of(userPO);
    } else {
      return Optional.absent();
    }
  }
}
