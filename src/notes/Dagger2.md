# Dagger2

- ### Reference Websites:
  + [神兵利器](https://blog.csdn.net/mq2553299/article/details/73414710)
  + [Dagger 2 完全解析](https://www.jianshu.com/p/26d9f99ea3bb)
  + [Dagger2教程](https://blog.csdn.net/u010961631/article/details/72625715)

- ### Dependency
  + ##### build.gradle(Module:app)
  ```
  dependencies {
    implementation 'com.google.dagger:dagger:2.11-rc2'
    annotationProcessor 'com.google.dagger:dagger-compiler:2.11-rc2'
    
    implementation 'com.google.dagger:dagger-android-support:2.11-rc2'
  }
  ```
  
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

- ### Android Dagger Template:
  + ***AppComponent***
  ```java
  @Singleton
  @Component(modules = {TasksRepositoryModule.class,
        ApplicationModule.class,
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class})
  public interface AppComponent extends AndroidInjector<MyApplication> {

    TasksRepository getTasksRepository();

    // Gives us syntactic sugar. we can then do DaggerAppComponent.builder().application(this).build().inject(this);
    // never having to instantiate any modules or say which module we are passing the application to.
    // Application will just be provided into our app graph now.
    @Component.Builder
    interface Builder {
      
      @BindsInstance
      AppComponent.Builder application(Application application);

      AppComponent build();
    }
  }
  ```
  
  + ***ActivityBindingModule***
  ```java
  @Module
  public abstract class ActivityBindingModule {
    
    @ActivityScoped
    @ContributesAndroidInjector(modules = TasksModule.class)
    abstract TasksActivity tasksActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = TaskDetailPresenterModule.class)
    abstract TaskDetailActivity taskDetailActivity();
  }
  ```
  
  
  + ***ApplicationModule***
  ```java
  @Module
  public abstract class ApplicationModule {
      //expose Application as an injectable context
      @Binds
      abstract Context bindContext(Application application);
  }
  ```
  
  + ***MyApplication***
  ```java
  public class MyApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
      return DaggerAppComponent.builder().application(this).build();
    }
  }
  ```
  
  + ******
  ```kotlin
  @Scope
  @MustBeDocumented
  @Retention(AnnotationRetention.RUNTIME)
  annotation class ActivityScope
  ```
  
  
  + ##### SourceCode Analysis
    + 

    + ***DaggerFragment***
    ```java
    @Beta
    public abstract class DaggerFragment extends Fragment implements HasSupportFragmentInjector {
    
      @Inject DispatchingAndroidInjector<Fragment> childFragmentInjector;
    
      @Override
      public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
      }
    
      @Override
      public AndroidInjector<Fragment> supportFragmentInjector() {
        return childFragmentInjector;
      }
    }
    ```
    
    + ***AndroidInjection***
    AndroidInjection.inject() takes the Injector inside the Application 
    and do the Injection.
    ```java
    public final class AndroidInjection {

      public static void inject(Activity activity) { 
        checkNotNull(activity, "activity");
        Application application = activity.getApplication();
        if (!(application instanceof HasActivityInjector)) {
          throw new RuntimeException();
        }

        AndroidInjector<Activity> activityInjector = 
          ((HasActivityInjector) application).activityInjector();
          
        checkNotNull(activityInjector, "%s.activityInjector() returned null", application.getClass());

        activityInjector.inject(activity);
      }
      
      public static void inject(Fragment fragment) {...}
      
      public static void inject(Service service) {...}
      
      public static void inject(BroadcastReceiver broadcastReceiver, Context context) {...}
      
      public static void inject(ContentProvider contentProvider) {...}
    }
    ```
    
    ```java
    
    ```
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
    
  + ##### @Component.Builder + @BindsInstance
    + [Dagger 2 : Component.Builder 注解有什么用？](https://juejin.im/post/5a4cf2b2f265da430d586ace)
    + @Component.Builder is to modify the Component.Builder
    > A builder for a component. Components may have a single nested static abstract class or interface annotated with @Component.Builder. If they do, then the component's generated builder will match the API in the type.--source
    + @BindsInstance uses an instance as Injection Dependency Object
    > @BindsInstance methods should be preferred to writing a @Module with constructor arguments and immediately providing those values.--source
    
    ```java
    // Previous Code
    @Module
    public class AppModule {
      Application application;

      public AppModule(Application application) {
        this.application = application;
      }
      
      @Provides
      Application providesApplication() {
        return application;
      }
       
      @Provides
      @Singleton
      public SharedPreferences providePreferences() {
        return application.getSharedPreferences(DATA_STORE,
                               Context.MODE_PRIVATE);
      }
    }
    
    @Singleton
    @Component(modules = {AppModule.class})
    public interface AppComponent {
      void inject(MainActivity mainActivity);
    }
    
    @Override
    public void onCreate() {
      super.onCreate();
      AppComponent appComponent = DaggerAppComponent.builder()
            .appMoudle(new AppMoudle(this))
            .build();
    }
    ```
    
    ```java
    // The Previous Component is equal to this 
    @Singleton
    @Component(modules = {AppModule.class})
    public interface AppComponent {

      void inject(MainActivity mainActivity);
      
      @Component.Builder
      interface Builder {
      
        Builder appModule(AppModule appModule);
        
        AppComponent build();
      }
    }
    ```
    
    ```java
    // Using @BindsInstance
    @Module
    public class AppModule {
       
      @Provides
      @Singleton
      public SharedPreferences providePreferences(Application application) {
        return application.getSharedPreferences(DATA_STORE,
                               Context.MODE_PRIVATE);
      }
    }
    
    @Singleton
    @Component(modules = {AppModule.class})
    public interface AppComponent {
      void inject(MainActivity mainActivity);
      
      @Component.Builder
      interface Builder {
      
        @BindsInstance 
        Builder application(Application application);  
        
        AppComponent build();  
      }
    }
    
    @Override
    public void onCreate() {
      super.onCreate();
      AppComponent appComponent = DaggerAppComponent.builder()
            .application(this)
            .build();
    }
    ```

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
    
  + ##### @SubComponent + @Subcomponent.Builder

    ```java
    @Module(subcomponents = SonComponent.class)
    public class CarModule {
      
      @Provides
      @ManScope
      static Car provideCar() {
        return new Car();
      }
    }

    @ManScope
    @Component(modules = CarModule.class)
    public interface ManComponent {
      
      void inject(Man man); 
      
      SonComponent.Builder sonComponent();
      // or
      // SonComponent sonComponent();
    }

    @SonScope
    @SubComponent(modules = BikeModule.class)
    public interface SonComponent {
      
      void inject(Son son);

      @Subcomponent.Builder
      interface Builder { // SubComponent mush add Subcomponent.Builder
        
        SonComponent build();
      }
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