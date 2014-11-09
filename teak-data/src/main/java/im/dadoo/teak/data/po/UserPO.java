/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.teak.data.po;

import java.io.Serializable;

/**
 *
 * @author codekitten
 */
public class UserPO implements Serializable {

  private static final long serialVersionUID = -5312523234515476335L;

  private long id;
  
  private String name;
  
  private String password;
  
  public UserPO() {}

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("UserPO [id=");
    builder.append(id);
    builder.append(", name=");
    builder.append(name);
    builder.append(", password=");
    builder.append(password);
    builder.append("]");
    return builder.toString();
  }

  /**
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }
  
}
