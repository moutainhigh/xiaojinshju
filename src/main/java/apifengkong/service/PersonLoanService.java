/*
package apifengkong.service;

import apifengkong.entity.PersonLoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

*/
/**
 * 个信通讯版服务类
 *
 * @author huanghengkun
 * @version 1.0.0
 * @date 2017-12-31-下午4:40
 *//*

@Service
public class PersonLoanService {

    @Autowired
    PersonLoanRepository personLoanRepository;

    public Long count30daysSearchNumber(String idCard) {
        Calendar c1 = Calendar.getInstance();// 取当前日期。
        c1.add(Calendar.DATE, -30);
        Long number = personLoanRepository.countByIdCardAndCreateAtGreaterThan(idCard, c1.getTime());
        return number;
    }
}
*/
