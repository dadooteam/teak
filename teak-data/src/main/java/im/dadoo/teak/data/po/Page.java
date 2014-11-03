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
public class Page implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  private Integer id;
  
  private String name;
  
  private String title;
  
  private String author;
  
  private String html;
  
  private String text;
  
  private Long publishDatetime;
  
  private Integer click;

  public Page() {}
  
  public static Page create(String name, String title, String author, String html, String text,
          Long publishDatetime, Integer click) {
    Page page = new Page();
    page.setName(name);
    page.setTitle(title);
    page.setAuthor(author);
    page.setHtml(html);
    page.setText(text);
    page.setPublishDatetime(publishDatetime);
    page.setClick(click);
    return page;
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    sb.append("name:").append(getName()).append(",");
    sb.append("title:").append(getTitle()).append(",");
    sb.append("author:").append(getAuthor()).append(",");
    sb.append("html:").append(getHtml()).append(",");
    sb.append("text:").append(getText()).append(",");
    sb.append("publishDatetime:").append(getPublishDatetime()).append(",");
    sb.append("click:").append(getClick()).append(",");
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
  
}
