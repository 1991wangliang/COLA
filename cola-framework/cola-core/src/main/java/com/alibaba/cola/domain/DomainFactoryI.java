package com.alibaba.cola.domain;

/**
 * 领域工厂
 * @author xueliang.sxl
 *
 */
public interface DomainFactoryI<T extends EntityObject> {

	default T create(){
		return null;
	}

}
