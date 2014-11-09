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
public class CategoryPO implements Serializable {
  
  private static final long serialVersionUID = 570101647912043500L;

  private long id;
  
  private String name;
  
  private String description;
  
  public CategoryPO() {}

  
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("CategoryPO [id=");
    builder.append(id);
    builder.append(", name=");
    builder.append(name);
    builder.append(", description=");
    builder.append(description);
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
