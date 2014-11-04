package im.dadoo.teak.web.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.base.Joiner;

public final class RequestUtil {
  
  private RequestUtil() {}
  
  public static final String buildQueryString(Map<String, String[]> params) {
    List<String> list = new ArrayList<String>();
    if (params != null && !params.isEmpty()) {
      for (String key : params.keySet()) {
        list.add(key + "=" + Joiner.on(",").join(params.get(key)));
      }
    }
    return Joiner.on("&").join(list);
  }
  
}
