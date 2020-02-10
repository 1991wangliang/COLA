package com.alibaba.cola.domain;

import com.alibaba.cola.common.ApplicationContextHelper;
import com.alibaba.cola.event.EventBus;
import com.alibaba.cola.extension.BizScenario;
import com.alibaba.cola.extension.ExtensionExecutor;
import com.alibaba.cola.repository.RepositoryBus;
import com.alibaba.cola.repository.RepositoryHandlerI;

/**
 * @author lorne
 * @date 2020/1/23
 * @description
 */
public abstract class DomainObject extends EntityObject {

  protected static EventBus eventBus;

  protected static RepositoryBus repositoryBus;

  protected static ExtensionExecutor extensionExecutor;

  static {
    eventBus = ApplicationContextHelper.getBean(EventBus.class);
    repositoryBus = ApplicationContextHelper.getBean(RepositoryBus.class);
    extensionExecutor = ApplicationContextHelper.getBean(ExtensionExecutor.class);
  }

  public void execute(){}

  protected  <T extends RepositoryHandlerI> T repository(Class<T> clazz){
     return repositoryBus.getHandler(clazz);
  }

  protected void newDefaultBizScenario(){
    setBizScenario(BizScenario.newDefault());
  }

}
