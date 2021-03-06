package com.jnshu.controller;

import com.jnshu.entity.TransactionLog;
import com.jnshu.exception.MyException;
import com.jnshu.service1.TransactionLogService1;
import com.jnshu.utils.CookieUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 交易流水相关接口，1E
 * @author wangqichao
 */
@RestController
public class TransactionLogController1 {
    private static final Logger log= LoggerFactory.getLogger(TransactionLogController1.class);
    @Autowired
    TransactionLogService1 transactionLogService1;

    /**
     * 获得用户交易流水列表
     * @return 返回参数,data,message,流水列表
     */
    @GetMapping(value = "/a/u/transaction-log/list")
    public Map getTransactionLogList(HttpServletRequest request)throws Exception{
        Map<String,Object> map=new HashMap<>();
        String uidS= CookieUtil.getCookieValue(request,"uid");
        long id= Long.parseLong(uidS);
        log.info("查找用户"+id+"交易流水列表");
        List<TransactionLog> logs= transactionLogService1.getTransactionLogList(id);
        map.put("code",0);
        map.put("message","success");
        map.put("data",logs);
        return map;
    }

    /**
     * 获得指定id的交易流水
     * @param id 交易流水id
     * @return 返回参数,code,message,交易流水详情
     */
    @GetMapping(value = "/a/u/transaction-log/{id}")
    public Map getTransactionLog(@PathVariable(value = "id")long id)throws Exception{
        log.info("查找用户交易流水号为"+id+"的交易流水");
        Map<String,Object> map=new HashMap<>();
        TransactionLog logs= transactionLogService1.getTransactionLogById(id);
        map.put("code",0);
        map.put("message","success");
        map.put("data",logs);
        return map;
    }
}
