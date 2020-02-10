package com.alibaba.cola.test.customer.validator.extension;

import com.alibaba.cola.exception.BizException;
import com.alibaba.cola.extension.Extension;
import com.alibaba.cola.test.customer.AddCustomerCmd;
import com.alibaba.cola.test.customer.Constants;
import com.alibaba.cola.test.customer.CustomerType;
import com.alibaba.cola.test.customer.validator.extensionpoint.AddCustomerValidatorExtPt;
import lombok.extern.slf4j.Slf4j;

/**
 * AddCustomerBizOneValidator
 *
 * @author Frank Zhang
 * @date 2018-01-07 1:31 AM
 */
@Extension(bizId = Constants.BIZ_1)
@Slf4j
public class AddCustomerBizOneValidator implements AddCustomerValidatorExtPt{

    public void validate(AddCustomerCmd addCustomerCmd) {
        log.warn("this is biz 1 validate .");
        System.out.println("this is biz 1 validate .");
        //For BIZ TWO CustomerTYpe could not be VIP
        if(CustomerType.VIP == addCustomerCmd.getCustomerCO().getCustomerType())
            throw new BizException("Customer Type could not be VIP for Biz One");
    }
}
