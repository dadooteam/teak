package im.dadoo.teak.biz.bo;

import java.io.File;

import com.google.common.base.Optional;

public interface FileBO {
  
  public Optional<String> save(File src);
  
}
