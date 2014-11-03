package im.dadoo.teak.web.vo;

public class PaginationVO {
  
  private String url;
  private int cur;
  private int max;
  
  public PaginationVO(String url, int cur, int max) {
    this.url = url;
    this.cur = cur;
    this.max = max;
  }
  
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("PaginationVO [url=");
    builder.append(url);
    builder.append(", cur=");
    builder.append(cur);
    builder.append(", max=");
    builder.append(max);
    builder.append("]");
    return builder.toString();
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public int getCur() {
    return cur;
  }

  public void setCur(int cur) {
    this.cur = cur;
  }

  public int getMax() {
    return max;
  }

  public void setMax(int max) {
    this.max = max;
  }
  
}
