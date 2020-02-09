package com.alibaba.craftsman.dto;

import com.alibaba.cola.dto.Response;
import com.alibaba.craftsman.dto.clientobject.MiscMetricCO;
import com.alibaba.craftsman.dto.clientobject.RefactoringMetricCO;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * RefactoringMetricAddCmd
 *
 * @author Frank Zhang
 * @date 2019-03-04 11:04 AM
 */
@Data
public class RefactoringMetricAddCmd extends CommonCommand<Response>{
    @NotNull
    private RefactoringMetricCO refactoringMetricCO;
}
