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
public class ArchivePO implements Serializable {

  private static final long serialVersionUID = 8215748915066979021L;

  private long id;
  
  private String title;
  
  private String author;
  
  private String html;
  
  private String text;
  
  private long publishDatetime;
  
  private int click;
  
  private String thumbnailPath;
  
  private long categoryId;

  public ArchivePO() {} 
  
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("ArchivePO [id=");
    builder.append(id);
    builder.append(", title=");
    builder.append(title);
    builder.append(", author=");
    builder.append(author);
    builder.append(", html=");
    builder.append(html);
    builder.append(", text=");
    builder.append(text);
    builder.append(", publishDatetime=");
    builder.append(publishDatetime);
    builder.append(", click=");
    builder.append(click);
    builder.append(", thumbnailPath=");
    builder.append(thumbnailPath);
    builder.append(", categoryId=");
    builder.append(categoryId);
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
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * @param title the title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * @return the author
   */
  public String getAuthor() {
    return author;
  }

  /**
   * @param author the author to set
   */
  public void setAuthor(String author) {
    this.author = author;
  }

  /**
   * @return the html
   */
  public String getHtml() {
    return html;
  }

  /**
   * @param html the html to set
   */
  public void setHtml(String html) {
    this.html = html;
  }

  /**
   * @return the text
   */
  public String getText() {
    return text;
  }

  /**
   * @param text the text to set
   */
  public void setText(String text) {
    this.text = text;
  }

  /**
   * @return the publishDatetime
   */
  public long getPublishDatetime() {
    return publishDatetime;
  }

  /**
   * @param publishDatetime the publishDatetime to set
   */
  public void setPublishDatetime(long publishDatetime) {
    this.publishDatetime = publishDatetime;
  }

  /**
   * @return the click
   */
  public int getClick() {
    return click;
  }

  /**
   * @param click the click to set
   */
  public void setClick(Integer click) {
    this.click = click;
  }

  /**
   * @return the thumbnailPath
   */
  public String getThumbnailPath() {
    return thumbnailPath;
  }

  /**
   * @param thumbnailPath the thumbnailPath to set
   */
  public void setThumbnailPath(String thumbnailPath) {
    this.thumbnailPath = thumbnailPath;
  }

  /**
   * @return the categoryId
   */
  public long getCategoryId() {
    return categoryId;
  }

  /**
   * @param categoryId the categoryId to set
   */
  public void setCategoryId(long categoryId) {
    this.categoryId = categoryId;
  }
  
}
