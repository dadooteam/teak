/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.teak.biz.bo.impl;

import im.dadoo.teak.biz.bo.LinkBO;
import im.dadoo.teak.biz.dao.LinkDAO;
import im.dadoo.teak.data.po.LinkPO;

import java.util.List;

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
public class DefaultLinkBO implements LinkBO {
  
  @Resource
  private LinkDAO linkDAO;
  
  @Override
  public Optional<LinkPO> insert(String name, String url, String description) {
    LinkPO linkPO = new LinkPO();
    linkPO.setName(name);
    linkPO.setUrl(url);
    linkPO.setDescription(description);
    return Optional.fromNullable(this.linkDAO.insert(linkPO));
  }
  
  @Override
  public Optional<LinkPO> update(long id, String name, String url, String description) {
    LinkPO linkPO = this.linkDAO.findById(id);
    if (linkPO != null) {
      linkPO.setName(name);
      linkPO.setUrl(url);
      linkPO.setDescription(description); 
      this.linkDAO.updateAllById(linkPO);
    }
    return Optional.fromNullable(linkPO);
  }
  
  @Override
  public void deleteById(long id) {
    this.linkDAO.deleteById(id);
  }
  
  @Override
  public Optional<LinkPO> findById(long id) {
    return Optional.fromNullable(this.linkDAO.findById(id));
  }
  
  @Override
  public List<LinkPO> list() {
    return this.linkDAO.list();
  }
  
}
