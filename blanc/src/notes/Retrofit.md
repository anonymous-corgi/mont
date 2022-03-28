# Retrofit:

- ### Reference Websites:

  + [详细的 Retrofit 2.0 使用教程](https://blog.csdn.net/carson_ho/article/details/73732076)

- ### Dependency & Permission

  + ##### build.gradle(Module:app)
  ```
  dependencies {
    
    //Retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.4.0'
    
    //OkHttp
    implementation 'com.squareup.okhttp:okhttp:2.7.5'
    
    //RxJava2
    implementation 'io.reactivex.rxjava2:rxjava:2.1.17'
    implementation 'io.reactivex.rxjava2:rxandroid:2.0.2'
    
    //RxJava2 Adapter
    implementation 'com.squareup.retrofit2:adapter-rxjava:2.3.0'
    
    //JSON Parsing
    implementation 'com.google.code.gson:gson:2.8.2'
    
    //JSON Adapter
    implementation 'com.squareup.retrofit2:converter-gson:2.4.0'
  }
  ```
  
  + ##### AndroidManifest.xml
  ```
  <uses-permission android:name="android.permission.INTERNET"/>
  ```

> BASE_URL = "https://apis.raych.com/"

- ### Create Retrofit
  ```java
  Retrofit retrofit = new Retrofit.Builder()
               .baseUrl(BASE_URL)
               .client(okHttpClient)// Set OkHttpClient
               .addCallAdapterFactory(RxJavaCallAdapterFactory.create())// Convert response Call<T> to Observable<T>
               .addConverterFactory(GsonConverterFactory.create()) // Set the parser of the Data
               .build();
  ```
  
  + **GsonConverterFactory**
  ```java
  GsonBuilder gsonBuilder = new GsonBuilder();
  gsonBuilder.registerTypeAdapterFactory(AdapterFactory.create());
  Gson gson = gsonBuilder.create();
  GsonConverterFactory gsonConverter = GsonConverterFactory.create(gson);
  ```
  
  + **RxJavaCallAdapterFactory**
  ```java
  RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
  ```


- ### Request

  + ##### Get:
  
  > https://apis.raych.com/service/name?num=10&page=1
  
  ```java
  @Headers("userToken:abcdefg", "Content-Type:application/json")
  @GET("{service}/{name}")
  Observable<T> get(@Path("name") String name, @Query("num") String num, @Query("page")String page);
  ```

  + ##### Post:
  
  > https://apis.raych.com/service/name
  
  ```java
  @POST("service/name")
  Observable<T> createUser(@Body Map<String, String> body);
  ```

  ```java
  @POST("service/name")
  Observable<T> createUser(@Body Object obj);
  ```  

  + ### Add Header

    + ##### OKHttpClient interceptors
    
    ```java
    OkHttpClient.Builder client = new OkHttpClient.Builder();

    client.interceptors().add(new Interceptor() {
      @Override
      public Response intercept(Interceptor.Chain chain) throws IOException {
        Request original = chain.request();
        
        // Request customization: add request headers
        Request.Builder add = original.newBuilder()
        .addHeader("headerKey", "headerValue1")
        .addHeader("headerKey", "headerValue2");
        
        // Request customization: Override request headers         
        Request.Builder override = original.newBuilder()
        .header("headerKey", "headerValue"); 
        
        Request request = add.build();
        return chain.proceed(request);
      }
    });
    ```

    + ##### @Headers
    ```java
    @Headers("headerKey:headerValue", "Content-Type:application/json")
    @GET("{service}/{name}")
    Observable<T> get(@Path("name") String name);
    ```

    + ##### @Header
    ```java
    @GET("{service}/{name}")
    Observable<T> get(@Header("headerKey") String headerValue, @Path("name") String name);
    ```
