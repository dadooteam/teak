package im.dadoo.teak.biz.bo;

import java.io.File;

import javax.annotation.Resource;

import im.dadoo.teak.biz.bo.impl.QiniuFileBO;
import im.dadoo.teak.biz.configuration.BizContext;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BizContext.class)
public class FileBOTest {
  
  @Resource
  private QiniuFileBO qiniuFileBO;
  
  @Test
  @Ignore
  public void testSave() {
    try {
      String localFilePath = "D:\\bd5d3f906dde14fc19f4d3504982181a";
      System.out.println(this.qiniuFileBO.save(new File(localFilePath)));
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
  
}
