package cc.lostyouth.learn.subversiveofj2ee.ch9_2.batch;

import cc.lostyouth.learn.subversiveofj2ee.ch9_2.domain.Person;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

/**
 *
 * @author endless
 * @date 4/11/2018
 */
public class CsvItemProcessor extends ValidatingItemProcessor<Person> {
    @Override
    public Person process(Person item) throws ValidationException {
        //需执行super.process才会调用自定义校验器
        super.process(item);
        if (item.getNation().equals("汉族")) {
            item.setNation("01");
        } else {
            item.setNation("02");
        }
        return item;
    }
}
