# Dagger2

- ### Reference Websites:
  + [神兵利器](https://blog.csdn.net/mq2553299/article/details/73414710)
  + [Dagger 2 完全解析](https://www.jianshu.com/p/26d9f99ea3bb)
  + [Dagger2教程](https://blog.csdn.net/u010961631/article/details/72625715)
  + [Github.Dagger](https://google.github.io/dagger/)

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
    + When DaggerComponent is being created, it uses modules to create Provider<T> and uses this Provider<T> to create MembersInjector.
    + @Override void inject(): Method used to do injection work;
    + If you add inject(T t) method in Component, **Component becomes an Injector for T**
    + @Override T getT(): Method used to get 'Items' or 'Products' by the Provider in Component.
    + If you add T provide() method in Component, **Component becomes a provider of T**

  + ##### Provider or Factory
    If a dependency is provided by @Inject, the instance of the factory of that dependency is universal.
    + @Inject: Provider<T> TProvider = T_Factory.create();

    If a dependency is provided by @Module, the instance of the factory of that dependency is bound to that Module instance.
    + @Module: Provider<T> TProvider = XxxModule_ProvideTFactory.create(builder.module)

  + ##### MembersInjector
    + Instantiated with one or more Factory.
    + Has method - injectMembers(): Use the provided Factory to do the actual injection work.

- ### Android Dagger Template:
  + ***AppComponent***
  ```java
  @Singleton
  @Component(modules = {ApplicationModule.class,
        // ActivityBindingModule.class offers a good place to add @ContributesAndroidInjector.
        ActivityBindingModule.class,
        // Must have AndroidSupportInjectionModule.class
        AndroidSupportInjectionModule.class})
  public interface AppComponent extends AndroidInjector<MyApplication> {

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
  }
  ```

  + ***TasksModule***
  ```java
  @Module
  public abstract class TasksModule {
      @FragmentScoped
      @ContributesAndroidInjector
      abstract TasksFragment tasksFragment();

      @ActivityScoped
      @Binds abstract TasksContract.Presenter taskPresenter(TasksPresenter presenter);
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

  + ***ActivityScope***
  ```kotlin
  @Scope
  @MustBeDocumented
  @Retention(AnnotationRetention.RUNTIME)
  annotation class ActivityScope
  ```

+ ##### SourceCode Analysis
  The Key:
  1. DaggerApplication keeps all kinds of DispatchingAndroidInjector.
  2. DispatchingAndroidInjector\<T\> keeps a Map\<Class\<? extends T\>, Provider<AndroidInjector.Factory\<? extends T\>\>\> injectorFactories.
  3. We write subComponent to contribute a certain Map.Entry\<Class\<? extends T\>, AndroidInjector.Factory\<? extends T\>\> to the injectorFactories.
  4. The SubComponent will be Automatically generated. SubComponent\<T\> implements AndroidInjector\<T\>, so SubComponent is actually an Injector for T. In the meanwhile, SubComponent.Builder implements AndroidInjector.Builder\<T\>, so SubComponent.Builder is able to create an AndroidInjector\<T\>.

  + ***DaggerApplication***
  Main Tasks:
  1. DaggerApplication gathers all kinds of DispatchingAndroidInjector
  2. Do Injection with the injector returned by method applicationInjector().
  ```java
  public abstract class DaggerApplication extends Application
    implements HasActivityInjector,
        HasFragmentInjector,
        HasServiceInjector,
        HasBroadcastReceiverInjector,
        HasContentProviderInjector {
    @Inject DispatchingAndroidInjector<Activity> activityInjector;
    @Inject DispatchingAndroidInjector<BroadcastReceiver> broadcastReceiverInjector;
    @Inject DispatchingAndroidInjector<Fragment> fragmentInjector;
    @Inject DispatchingAndroidInjector<Service> serviceInjector;
    @Inject DispatchingAndroidInjector<ContentProvider> contentProviderInjector;
  }
  ```

  + ***AndroidInjector\<T\>***
  Main Functions:
  1. Component that implements AndroidInjector\<T\> is an Injector for T.
  2. Component.Builder that implements AndroidInjector.Builder\<T\> is able to call create() to create AndroidInjector\<T\> - (SubComponent).
  ```java
  public interface AndroidInjector<T> {

    void inject(T instance);

    interface Factory<T> {

      AndroidInjector<T> create(T instance);
    }

    abstract class Builder<T> implements AndroidInjector.Factory<T> {
      @Override
      public final AndroidInjector<T> create(T instance) {
        seedInstance(instance);
        return build();
      }

      @BindsInstance
      public abstract void seedInstance(T instance);

      public abstract AndroidInjector<T> build();
    }
  }
  ```

  + ***DispatchingAndroidInjector\<T\>***
  Main Functions:
  1. Keeps a Map\<Class\<? extends T\>, Provider<AndroidInjector.Factory\<? extends T\>\>\> injectorFactories.
  2. fun inject(T instance) will call maybeInject(T instance) to do actual Injection.
  3. fun maybeInject(T instance) get the actual AndroidInjector and do Injection.
  ```java
  public final class DispatchingAndroidInjector<T> implements AndroidInjector<T> {

    private final Map<Class<? extends T>, Provider<AndroidInjector.Factory<? extends T>>>
      injectorFactories;

    public void inject(T instance) {
      boolean wasInjected = maybeInject(instance);
    }

    public boolean maybeInject(T instance) {
      Provider<AndroidInjector.Factory<? extends T>> factoryProvider =
          injectorFactories.get(instance.getClass());
      if (factoryProvider == null) {
        return false;
      }

      AndroidInjector.Factory<T> factory = (AndroidInjector.Factory<T>) factoryProvider.get();
      try {
        AndroidInjector<T> injector =
            checkNotNull(
                factory.create(instance), "%s.create(I) should not return null.", factory.getClass());

        injector.inject(instance);
        return true;
      } catch (ClassCastException e) {
        throw new InvalidInjectorBindingException();
      }
    }
  }
  ```

  + ***TasksModule_TasksFragment***
  This is Automatically generated, as we write ActivityBindingModule and TasksModule. AppComponent will take TasksModule_TasksFragment.TasksFragmentSubcomponent as a subcomponent.
  TasksModule_TasksFragment.TasksFragmentSubcomponent has inject(T t) method, which will create
  ```java
  @Module(subcomponents = TasksModule_TasksFragment.TasksFragmentSubcomponent.class)
  public abstract class TasksModule_TasksFragment {
    private TasksModule_TasksFragment() {}

    // TasksFragmentSubcomponent is a subcomponent of AppComponent, so TasksFragmentSubcomponent.Builder can be provided by AppComponent.
    @Binds
    @IntoMap
    @FragmentKey(TasksFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindAndroidInjectorFactory(
        TasksFragmentSubcomponent.Builder builder);

    @Subcomponent
    @FragmentScoped
    public interface TasksFragmentSubcomponent extends AndroidInjector<TasksFragment> {
      @Subcomponent.Builder
      abstract class Builder extends AndroidInjector.Builder<TasksFragment> {}
    }
  }
  ```

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
