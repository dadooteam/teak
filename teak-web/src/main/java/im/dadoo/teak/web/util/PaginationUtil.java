package im.dadoo.teak.web.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class PaginationUtil {
  
  private PaginationUtil() {}
  
  public static final String TPL = "pagecount=00";
  public static final String PATTERN = "pagecount=(\\d+)";
  
  /**
   * 根据查询的参数，生成 一个包含pagecount=00的查询字符串
   * 若查询参数中没有pagecount，则直接生成pagecount==00并添加到查询字符串的末尾，若参数中包含pagecount，则直接将pagecount的值赋为00
   * @param queryString
   * @return
   */
  public static String template(String queryString) {
    if (queryString != null && queryString.length() > 0) {
      Pattern pattern = Pattern.compile(PATTERN);
      Matcher m = pattern.matcher(queryString);
      if (m.lookingAt()) {
        return m.replaceFirst(TPL);
      } else {
        return queryString + "&" + TPL;
      }
    } else {
      return TPL;
    }
  }
}
