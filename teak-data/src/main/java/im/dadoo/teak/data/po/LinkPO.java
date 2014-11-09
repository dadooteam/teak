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
public class LinkPO implements Serializable {
  
  private static final long serialVersionUID = 1945553264284018753L;

  private long id;
  
  private String name;
  
  private String url;
  
  private String description;
  
  public LinkPO() {}
  
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("LinkPO [id=");
    builder.append(id);
    builder.append(", name=");
    builder.append(name);
    builder.append(", url=");
    builder.append(url);
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
   * @return the url
   */
  public String getUrl() {
    return url;
  }

  /**
   * @param url the url to set
   */
  public void setUrl(String url) {
    this.url = url;
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
