import com.alibaba.fastjson.annotation.JSONField;

/**
 * ClassName    LIMS-LabsMessage
 * Description  
 *
 * @author      xuanc
 * @date        2019/6/12 下午12:05
 * @version     1.0
 */ 
public class LabsMessage {

    private String kind;
    @JSONField(jsonDirect=true)
    private String laboratoryList;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getLaboratoryList() {
        return laboratoryList;
    }

    public void setLaboratoryList(String laboratoryList) {
        this.laboratoryList = laboratoryList;
    }
}
