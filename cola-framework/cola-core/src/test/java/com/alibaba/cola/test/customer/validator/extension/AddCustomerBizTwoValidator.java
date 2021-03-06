package com.alibaba.cola.test.customer.validator.extension;

import com.alibaba.cola.exception.BizException;
import com.alibaba.cola.extension.Extension;
import com.alibaba.cola.test.customer.AddCustomerCmd;
import com.alibaba.cola.test.customer.Constants;
import com.alibaba.cola.test.customer.validator.extensionpoint.AddCustomerValidatorExtPt;
import lombok.extern.slf4j.Slf4j;

/**
 * AddCustomerBizTwoValidator
 *
 * @author Frank Zhang
 * @date 2018-01-07 1:31 AM
 */
@Extension(bizId = Constants.BIZ_2)
@Slf4j
public class AddCustomerBizTwoValidator implements AddCustomerValidatorExtPt{

    public void validate(AddCustomerCmd addCustomerCmd) {
        log.warn("this is biz 2 validate .");
        System.out.println("this is biz 2 validate .");
        //For BIZ TWO CustomerTYpe could not be null
        if (addCustomerCmd.getCustomerCO().getCustomerType() == null)
            throw new BizException("CustomerType could not be null");
    }
}
