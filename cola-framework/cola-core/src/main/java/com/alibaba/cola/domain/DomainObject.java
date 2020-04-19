package com.alibaba.cola.domain;

import com.alibaba.cola.common.ApplicationContextHelper;
import com.alibaba.cola.event.DefaultEventBus;
import com.alibaba.cola.extension.BizScenario;
import com.alibaba.cola.extension.ExtensionExecutor;
import com.alibaba.cola.repository.RepositoryBus;
import com.alibaba.cola.repository.RepositoryHandlerI;

/**
 * @author lorne
 * @date 2020/1/23
 * Domain层不允许操作资源层对象，对资源层的操作要在Executor中完成.
 */
@Deprecated
public abstract class DomainObject extends EntityObject {

  protected static DefaultEventBus defaultEventBus;

  protected static RepositoryBus repositoryBus;

  protected static ExtensionExecutor extensionExecutor;

  static {
    defaultEventBus = ApplicationContextHelper.getBean(DefaultEventBus.class);
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
