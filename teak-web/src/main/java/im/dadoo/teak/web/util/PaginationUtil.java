package im.dadoo.teak.web.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class PaginationUtil {
  
  private PaginationUtil() {}
  
  public static final String TPL = "pagecount=00";
  public static final String PATTERN = "pagecount=(\\d+)";
  
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
