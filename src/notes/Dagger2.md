# Dagger2

- ### Reference Websites:
  + [神兵利器](https://blog.csdn.net/mq2553299/article/details/73414710)
  + [Dagger2教程](https://blog.csdn.net/u010961631/article/details/72625715)

- ### Basic Components:
  
  + ##### Dagger2 Automatically generates:
    + 1. DaggerComponent
    + 2. MembersInjector
    + 3. Factory: Module_ProvideFactory or _Factory

  + ##### Module
    + Provides methods to generate 'Items' or 'Products'. (It is used to create Factory(Module_ProvideFactory))
   
  + ##### Component
    + When DaggerComponent is being created, it uses module to create Provider<T> and uses this Provider<T> to create MembersInjector.
    + @Override void inject(): Method used to do injection work;
    + @Override T getT(): Method used to get 'Items' or 'Products' by the Provider in Component.

  + ##### Provider or Factory
    is created in 2 ways:
    + Provider<T> TProvider = T_Factory.create();
    + Provider<T> TProvider = XxxModule_ProvideTFactory.create(builder.mainActivityModule)

  + ##### MembersInjector
    + Provided Factory to be instantiated.
    + Has method - injectMembers(): Use the provided Factory to do the actual injection work.

- ### Scope:
  
  Scope is only valid for a Component instance. If you create a new Component instance, the scope doesn't work on it.
  
    + ##### @Singleton
      + Must Be added in both Module and Component.

- ### Notes:

  + If a provider needs parameters to instantiate an object. Dagger2 will search for the corresponding provider of the parameters in the same Module or other dependent Modules or Constructors that have @Inject annotation to instantiate that object.
  + If an object needs to be injected before it is provided to others, Dagger2 will firstly do the injection inside that object when it is instantiated and then provides this new object to others.


  1、Activity中通过DaggerXXXComponent的Inject()触发注入过程
  2、Dagger在Activity中搜索用@Inject标注的变量，说明改对象需要被注入
  3、去Component中注册的Module中搜索注入类
  4、在Module中搜索返回值为注入类的方法，执行并拿到注入类对象，从而完成注入过程
  5、如果在Module中没有搜索到提供目标类注入的方法，则在工程中搜索目标类
  6、找到需要注入对象后，寻找该对象中用@Inject标识的构造方法，完成自动创建过程