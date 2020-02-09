package com.alibaba.craftsman.dto;

import com.alibaba.cola.dto.Response;
import com.alibaba.craftsman.dto.clientobject.SharingMetricCO;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * SharingMetricAddCmd
 *
 * @author Frank Zhang
 * @date 2019-03-01 10:12 AM
 */
@Data
public class SharingMetricAddCmd extends CommonCommand<Response>{
    @NotNull
    private SharingMetricCO sharingMetricCO;
}
