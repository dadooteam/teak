package im.dadoo.teak.web.vo;

public class PaginationVO {
  
  private String url;
  private long cur;
  private long max;
  
  public PaginationVO(String url, long cur, long max) {
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

  public long getCur() {
    return cur;
  }

  public void setCur(long cur) {
    this.cur = cur;
  }

  public long getMax() {
    return max;
  }

  public void setMax(long max) {
    this.max = max;
  }
  
}
