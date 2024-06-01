package com.example.springtransaction.service;

import com.example.springtransaction.entity.AllLog;
import com.example.springtransaction.enums.ErrorCode;
import com.example.springtransaction.exception.BankException;
import com.example.springtransaction.repository.AllLogRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoggingService {
    @Autowired
    private AllLogRepository allLogRepo;


    @Transactional(value = Transactional.TxType.REQUIRES_NEW, dontRollbackOn={ BankException.class })

    //@Transactional(value = TxType.NEVER)  //lưu được all log thành công vì tạo ra 2 transaction context khác nhau
    //@Transactional(value = TxType.REQUIRED) //Nằm trong transaction context của hàm gọi, nên không lưu được mọi log
    //@Transactional(value = TxType.REQUIRED, dontRollbackOn={ BankException.class })
    //@Transactional(value = TxType.SUPPORTS) //Không ghi được hết log
    //@Transactional(value = TxType.NOT_SUPPORTED) //Cũng ghi được nhiều log thành công
    //@Transactional(value = TxType.NEVER) //Báo lỗi Existing transaction found for transaction marked with propagation 'never'
    public void saveLog(long fromID, long toID, Long amount, ErrorCode resultCode, String detail) {
        allLogRepo.save(new AllLog(fromID, toID, amount, resultCode, detail));
        allLogRepo.save(AllLog
                .builder()
                        .fromID(fromID)
                        .toID(toID)
                        .amount(amount)
                        .resultCode(resultCode)
                        .detail(detail)
                .build());
    }
}
