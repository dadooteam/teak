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
public class PagePO implements Serializable {

  private static final long serialVersionUID = 4832839882671486980L;

  private long id;
  
  private String name;
  
  private String title;
  
  private String author;
  
  private String html;
  
  private String text;
  
  private long publishDatetime;
  
  private int click;

  public PagePO() {}
  
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("PagePO [id=");
    builder.append(id);
    builder.append(", name=");
    builder.append(name);
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
  public void setClick(int click) {
    this.click = click;
  }
  
}
