package im.dadoo.teak.biz.bo.impl;

import im.dadoo.teak.biz.bo.FileBO;

import java.io.File;

import org.json.JSONException;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.PutPolicy;

@Component
public class QiniuFileBO implements FileBO {
  
  private String ACCESS_KEY = "K_sLxJjKPoms2DrECw-1FqRjxpjPFVLYTI2q6-Ue";
  private String SECRET_KEY = "n5ne9ImpHwpvbg_v5cy7w8OL7h1t-xth3oFQLq1z";
  
  private String bucket = "dadoo";
  private String project = "teak";
  private String cdnUrl = String.format("http://%s.qiniudn.com/", bucket);
  
  public Optional<String> save(File src) {
    try {
      if (src != null && src.exists()) {
        Config.ACCESS_KEY = ACCESS_KEY;
        Config.SECRET_KEY = SECRET_KEY;
        Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
        PutPolicy putPolicy = new PutPolicy(this.bucket);
        String uptoken = putPolicy.token(mac);
        PutExtra extra = new PutExtra();
        String dstPath = project + "/" + src.getName();
        PutRet ret = IoApi.putFile(uptoken, dstPath, src, extra);
        if (ret.ok()) {
          return Optional.of(this.cdnUrl + dstPath);
        } else {
          return Optional.absent();
        }
      } else {
        return Optional.absent();
      }
    } catch(AuthException | JSONException e) {
      e.printStackTrace();
      return Optional.absent();
    }
  }
}
