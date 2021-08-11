package cc.qwer.parser;

import cc.qwer.doamin.Test1;
import cc.qwer.doamin.Test2;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JAXBTest {
    @Test
    void jaxb() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Test1.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        File file = new File("CORPCODE.xml");
        Test1 Items = (Test1)unmarshaller.unmarshal(file);
        List<Test2> list = Items.getTestList();
        assertThat(list).isNotEmpty();
        for (Test2 index : list) {
            System.out.println("index = " + index);
        }
    }
}
