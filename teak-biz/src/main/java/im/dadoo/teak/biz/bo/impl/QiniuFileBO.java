package im.dadoo.teak.biz.bo.impl;

import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import im.dadoo.teak.biz.bo.FileBO;

import java.io.File;


import com.google.common.base.Optional;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class QiniuFileBO implements FileBO {

  @Resource
  private Environment env;
  
  private Auth auth;
  private UploadManager uploadManager;

  @PostConstruct
  public void init() {
    this.auth = Auth.create(this.env.getProperty("qiniu.access_key"), this.env.getProperty("qiniu.secret_key"));
    this.uploadManager = new UploadManager();
  }
  
  public Optional<String> save(File src) {
    try {
      if (src != null && src.exists()) {
        //调用put方法上传
        String upToken = this.auth.uploadToken(this.env.getProperty("qiniu.bucket"));
        String dstPath = this.env.getProperty("qiniu.project") + "/" + src.getName();
        Response res = this.uploadManager.put(src, dstPath, upToken);

        if (res.isOK()) {
          return Optional.of(this.env.getProperty("qiniu.cdn_url") + dstPath);
        } else {
          return Optional.absent();
        }
      } else {
        return Optional.absent();
      }
    } catch(Exception e) {
      e.printStackTrace();
      return Optional.absent();
    }
  }
}
