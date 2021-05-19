package com.lk.spring.transaction.propagation;


import com.lk.spring.SpringTestApplication;
import com.lk.spring.transaction.propagation.service.TransactionPropagationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = SpringTestApplication.class)
@RunWith(SpringRunner.class)
public class TransactionPropagationServiceImplTest {

    @Autowired
    TransactionPropagationService transactionPropagationService;

    /**
     * required
     * 外围方法没有开启事务 抛出异常 各自不受影响
     */
    @Test
    public void notransaction_exception_required_required(){
        transactionPropagationService.notransaction_exception_required_required();
    }
}
