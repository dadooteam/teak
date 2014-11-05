package im.dadoo.teak.biz.util;

import java.util.Random;

import org.springframework.util.DigestUtils;

public final class Util {
  
  private Util() {}
  
  public static String createFileName() {
    byte[] bytes = new byte[64];
    Random random = new Random(System.currentTimeMillis());
    random.nextBytes(bytes);
    return DigestUtils.md5DigestAsHex(bytes); 
  }
  
}
