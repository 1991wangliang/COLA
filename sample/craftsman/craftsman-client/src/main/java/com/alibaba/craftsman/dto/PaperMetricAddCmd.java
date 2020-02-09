package com.alibaba.craftsman.dto;

import com.alibaba.cola.dto.Response;
import com.alibaba.craftsman.dto.clientobject.PaperMetricCO;
import com.alibaba.craftsman.dto.clientobject.PatentMetricCO;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * PaperMetricAddCmd
 *
 * @author Frank Zhang
 * @date 2019-03-03 11:38 AM
 */
@Data
public class PaperMetricAddCmd extends CommonCommand<Response>{
    @NotNull
    private PaperMetricCO paperMetricCO;
}