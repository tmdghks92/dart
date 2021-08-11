package cc.qwer.doamin;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestAdapter extends XmlAdapter<String, LocalDate> {

    public DateTimeFormatter dateFormat() {
        return DateTimeFormatter.ofPattern("yyyyMMdd");
    }

    @Override
    public LocalDate unmarshal(String v) {
        DateTimeFormatter formatter = dateFormat();
        return LocalDate.parse(v, formatter);
    }

    @Override
    public String marshal(LocalDate v) {
        return v.format(dateFormat());
    }
}
