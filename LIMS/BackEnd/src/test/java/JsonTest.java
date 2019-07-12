import com.alibaba.fastjson.*;
import org.junit.jupiter.api.Test;

public class JsonTest {
    @Test
    public void tttt() {
        LabsMessage labsMessage = new LabsMessage();
        labsMessage.setKind("hhh");
        labsMessage.setLaboratoryList("{\"name\":\"albert\"}");
        System.out.println(JSON.toJSONString(labsMessage));
    }

}