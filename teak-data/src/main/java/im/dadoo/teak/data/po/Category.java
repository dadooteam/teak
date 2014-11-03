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
public class Category implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  private Integer id;
  
  private String name;
  
  private String description;
  
  public Category() {}
  
  public static Category create(String name, String description) {
    Category category = new Category();
    category.setName(name);
    category.setDescription(description);
    return category;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    sb.append("id:").append(getId()).append(",");
    sb.append("name:").append(getName()).append(",");
    sb.append("description:").append(getDescription());
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
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }
  
  
}
