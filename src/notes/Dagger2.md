# Dagger2

- ### Reference Websites:
  + [神兵利器](https://blog.csdn.net/mq2553299/article/details/73414710)
  + [Dagger 2 完全解析](https://www.jianshu.com/p/26d9f99ea3bb)
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

- ### Annotation:

  + ##### @Inject
    + Added to Constructors as Provider
    + Added to class members as Injection Places
    + **@Inject Lazy\<T\> t**: Inject Lazy Member
    + **@Inject Provider\<T\> t**: Inject Provider Member

  + ##### @Singleton
    + Must Be added in both Module and Component:
    + Add \@Singleton to Provides or Class
    ```java
    @Module
    abstract public class TasksRepositoryModule {

      @Singleton
      @Binds
      @Local
      abstract TasksDataSource provideTasksLocalDataSource(TasksLocalDataSource dataSource);
    }
    
    @Singleton
    public class TasksLocalDataSource implements TasksDataSource {}
    ```
    
    + Also add \@Singleton to Component
    ```java
    @Singleton
    @Component(modules = AppModule.class)
    public interface AppComponent {}
    ```
  
  + ##### @scope
    + Scope is only valid for a Component instance. If you create a new Component instance, the scope doesn't work on it. The instance's lifecycle is bound to that Component.
    > When a binding uses a scope annotation, that means that the component object holds a reference to the bound object until the component object itself is garbage-collected.

    + Define a custom annotation
    ```java
    @Scope
    @Retention(RUNTIME)
    public @interface ActivityScope {}
    ```
    + Added to both Module and Component like \@Singleton

  + ##### @BindsInstance
    + Use an instance as Injection Dependency Object
    > A builder for a component. Components may have a single nested static abstract class or interface annotated with @Component.Builder. If they do, then the component's generated builder will match the API in the type.--source
    

著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
  
  + ##### @Qualifier
    + Used to distinguish provider with the same resource type.
    + Define a custom annotation
    ```java
    @Qualifier
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Local {}
    ```
    
    ```java
    @Qualifier
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Remote {}
    ```
  
    + Add this self annotation to the Module
    ```java
    @Module
    abstract public class TasksRepositoryModule {

      @Singleton
      @Binds
      @Local
      abstract TasksDataSource provideTasksLocalDataSource(TasksLocalDataSource dataSource);

      @Singleton
      @Binds
      @Remote
      abstract TasksDataSource provideTasksRemoteDataSource(FakeTasksRemoteDataSource dataSource); 
    }
    ```
    
    + Add this  self annotation to the 
    ```java
    @Inject
    TasksRepository(@Remote TasksDataSource tasksRemoteDataSource,
        @Local TasksDataSource tasksLocalDataSource) {
        mTasksRemoteDataSource = tasksRemoteDataSource;
        mTasksLocalDataSource = tasksLocalDataSource;
    }
    ```

- ### Notes:

  + If a provider needs parameters to instantiate an object. Dagger2 will search for the corresponding provider of the parameters in the same Module or other dependent Modules or Constructors that have @Inject annotation to instantiate that object.
  + If an object needs to be injected before it is provided to others, Dagger2 will firstly do the injection inside that object when it is instantiated and then provides this new object to others.


  1、Activity中通过DaggerXXXComponent的Inject()触发注入过程
  2、Dagger在Activity中搜索用@Inject标注的变量，说明改对象需要被注入
  3、去Component中注册的Module中搜索注入类
  4、在Module中搜索返回值为注入类的方法，执行并拿到注入类对象，从而完成注入过程
  5、如果在Module中没有搜索到提供目标类注入的方法，则在工程中搜索目标类
  6、找到需要注入对象后，寻找该对象中用@Inject标识的构造方法，完成自动创建过程