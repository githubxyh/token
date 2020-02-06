import org.junit.Test;
import util.SHAUtil;

public class SHATest {

    @Test
    public void testSHA(){
        System.out.println(SHAUtil.getResult("123456"));
        System.out.println(SHAUtil.getResult("test"));
        System.out.println(SHAUtil.getResult(""));
        System.out.println(SHAUtil.getResult(null));
        System.out.println(SHAUtil.getResult("0"));
    }
}
