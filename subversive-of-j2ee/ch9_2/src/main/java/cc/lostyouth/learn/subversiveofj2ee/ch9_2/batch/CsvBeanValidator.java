package cc.lostyouth.learn.subversiveofj2ee.ch9_2.batch;

import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.InitializingBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Created by endless on 4/11/2018.
 */
public class CsvBeanValidator<T> implements Validator<T>, InitializingBean {
    private javax.validation.Validator validator;
    @Override
    public void validate(T value) throws ValidationException {
        //使用Validator来校验数据
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(value);
        if(constraintViolations.size() > 0) {
            StringBuilder message = new StringBuilder();
            for (ConstraintViolation<T> constraintViolation : constraintViolations) {
                message.append(constraintViolation.getMessage() + "\n");
            }
            throw new ValidationException(message.toString());
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //对JSR-303的Validator初始化
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.usingContext().getValidator();
    }
}
