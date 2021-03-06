package com.jnshu.controller;

import com.jnshu.dto1.TransactionListRO;
import com.jnshu.dto1.TransactionRO;
import com.jnshu.exception.MyException;
import com.jnshu.service1.TransactionService1;
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
 * 投资信息相关接口，2E
 * @author wangqichao
 */
@RestController
public class TransactionController1 {
    private static final Logger log= LoggerFactory.getLogger(TransactionController1.class);

    @Autowired
    TransactionService1 transactionService;
    /**
     * 获得续投列表
     * @return 返回参数,code,message,续投
     */
    @GetMapping(value = "/a/u/transaction/list/continue")
    public Map getContinueInvList(HttpServletRequest request)throws Exception{
        Map<String,Object> map=new HashMap<>();
        //注意要判断续投时间
        //从cookie获得id
        String uidS= CookieUtil.getCookieValue(request,"uid");
        long id = Long.parseLong(uidS);
        log.info("获得用户"+id+"的可续投列表");
        List<TransactionListRO> ros=transactionService.getContinueInvList(id);
        map.put("code",0);
        map.put("message","success");
        map.put("data",ros);
        return map;
    }

    /**
     * 获得用户投资列表
     * @param status 投资状态
     * @return 返回参数，code,message,投资列表
     */
    @GetMapping(value = "/a/u/transaction/list/{status}")
    public Map getTransactionList(@PathVariable(value = "status")Integer status,HttpServletRequest request)throws Exception{
        Map<String,Object> map=new HashMap<>();
        String uidS= CookieUtil.getCookieValue(request,"uid");
        long id= Long.parseLong(uidS);
        log.info("获得用户:"+id+"的投资列表列表，投资状态为:"+status);
        List<TransactionListRO> ros=transactionService.getTransactionListRO(id,status);
        map.put("code",0);
        map.put("message","success");
        map.put("data",ros);
        return map;
    }

    /**
     * 获得用户投资详情
     * @param id 交易id
     * @return code,message,交易详情data
     */
    @GetMapping(value = "/a/u/transaction/{id}")
    public Map getTransaction(@PathVariable(value = "id")long id)throws Exception{
        log.info("获得用户的具体投资详情，交易id为"+id);
        Map<String,Object> map=new HashMap<>();
        TransactionRO ro=transactionService.getTransactionById(id);
        map.put("code",0);
        map.put("message","success");
        map.put("data",ro);
        return map;
    }


}
