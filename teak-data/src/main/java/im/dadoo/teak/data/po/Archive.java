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
public class Archive implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  private Integer id;
  
  private String title;
  
  private String author;
  
  private String html;
  
  private String text;
  
  private Long publishDatetime;
  
  private Integer click;
  
  private String thumbnailPath;
  
  private Integer categoryId;

  public Archive() {}
  
  public static Archive create(String title, String author, String html, String text,
          Long publishDatetime, Integer click, String thumbnailPath, Integer categoryId) {
    Archive archive = new Archive();
    archive.setTitle(title);
    archive.setAuthor(author);
    archive.setHtml(html);
    archive.setText(text);
    archive.setPublishDatetime(publishDatetime);
    archive.setClick(click);
    archive.setThumbnailPath(thumbnailPath);
    archive.setCategoryId(categoryId);
    return archive;
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    sb.append("title:").append(getTitle()).append(",");
    sb.append("author:").append(getAuthor()).append(",");
    sb.append("html:").append(getHtml()).append(",");
    sb.append("text:").append(getText()).append(",");
    sb.append("publishDatetime:").append(getPublishDatetime()).append(",");
    sb.append("click:").append(getClick()).append(",");
    sb.append("thumbnaliPath:").append(getThumbnailPath()).append(",");
    sb.append("categoryId:").append(getCategoryId());
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
  public Long getPublishDatetime() {
    return publishDatetime;
  }

  /**
   * @param publishDatetime the publishDatetime to set
   */
  public void setPublishDatetime(Long publishDatetime) {
    this.publishDatetime = publishDatetime;
  }

  /**
   * @return the click
   */
  public Integer getClick() {
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
  public Integer getCategoryId() {
    return categoryId;
  }

  /**
   * @param categoryId the categoryId to set
   */
  public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
  }
  
}
