package cc.qwer.doamin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@XmlRootElement(name = "list")
@XmlAccessorType(XmlAccessType.FIELD)
public class Test2 {
    public Test2() {
    }

    @XmlElement(name = "corp_code")
    private String corporationCode;
    @XmlElement(name = "corp_name")
    private String corporationName;
    @XmlElement(name = "stock_code")
    private String stockCode;
    @XmlElement(name = "modify_date")
    @XmlJavaTypeAdapter(TestAdapter.class)
    private LocalDate localDate;
}
