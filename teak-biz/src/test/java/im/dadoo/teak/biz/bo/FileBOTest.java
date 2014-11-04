package im.dadoo.teak.biz.bo;

import javax.annotation.Resource;

import im.dadoo.teak.biz.configuration.BizContext;

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
  public void testSave() {
//    try {
//      String localFilePath = "D:\\a.jpg";
//      System.out.println(this.qiniuFileBO.save(localFilePath));
//    } catch(Exception e) {
//      e.printStackTrace();
//    }
  }
}
