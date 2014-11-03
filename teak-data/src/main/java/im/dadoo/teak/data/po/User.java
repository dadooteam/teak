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
public class User implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  private Integer id;
  
  private String name;
  
  private String password;
  
  public User() {}
  
  public static User create(String name, String password) {
    User user = new User();
    user.setName(name);
    user.setPassword(password);
    return user;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    sb.append("id:").append(getId()).append(",");
    sb.append("name:").append(getName()).append(",");
    sb.append("password:").append(getPassword());
    sb.append("}");
    return sb.toString();
  }
  /**
   * @return the id
   */
  public Integer getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Integer id) {
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
