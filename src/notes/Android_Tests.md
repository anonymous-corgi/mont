# Android Tests

- ### 0. Docs:

  + ##### [Android Unit Test Collection](https://blog.csdn.net/column/details/18260.html)
  + ##### [Android 测试](https://blog.csdn.net/lmj623565791/article/details/79623159)
  + ##### [Write Once, Run Everywhere](https://medium.com/androiddevelopers/write-once-run-everywhere-tests-on-android-88adb2ba20c5)

- ### 1. Test Foundation:

  + ##### Testable:
    + Controllable Input conditions.
    + Observable Output results.
    + Dependency

  + ##### Testing and Architecture:
    + Each class should have a clearly defined purpose
    + Limit and be explicit about which classes know about other classes
    + Use constructor injection
    + Keep Android code out of the view model

  + #### Note:
    1. Android Component is hard to test.
    2. Asynchronized
    3. static fields and methods

- ### 2. Test Tools:

  + ##### Junit:
    + Assertion: [Class Assertions](https://junit.org/junit5/docs/current/api/org/junit/jupiter/api/Assertions.html)
    + @Test (expected = Exception.class)
    + @BeforeClass –> @Before –> @Test –> @After –> @AfterClass

  + ##### Mockito:
    + 4 Ways of Mock:
      1. mock(xxx.class)
      2. @Mock + @Before MockitoAnnotations.initMocks(this);
      3. @RunWith(MockitoJUnitRunner.class) + @Mock
      4. @Mock \+ @Rule

    + verify(object, frequency / duration).methodName(input);


  | Tools       | RunTime            |
  |:------------|:-------------------|
  | Junit       | JVM                |
  | Mockito     | JVM                |
  | Robolectric | JVM (mock Android) |
  | Espresso    | Dalvik or ART      |

  | Input                                |
  |:-------------------------------------|
  | anyObject()                          |
  | any(Class<T> type)                   |
  | any()                                |
  | anyBoolean()                         |
  | anyByte()                            |
  | anyCollection()                      |
  | anyDouble()                          |
  | anyFloat()                           |
  | anyInt()                             |
  | anyList()                            |
  | anyLong()                            |
  | anyMap()                             |
  | anyString()                          |
  | contains(String substring)           |
  | argThat(argumentMatcher <T> matcher) |


- ### 3. Unit Test:

  + ##### Dependency Isolation:

- ### 4. Demo:

  + ##### Mockito:

    ```
    public class TestDemoObj {

      String name;
      String age;

      private TestDemoObj(String name, String age) {
        this.name = name;
        this.age = age;
      }

      public String getName() {
          return name;
      }

      public String getAge() {
          return age;
      }
    }
    ```

    ```
    public class TestDemo {

      @Test
      public void testVerificationInOrder() throws Exception {
          // A. Single mock whose methods must be invoked in a particular order
          List singleMock = mock(List.class);

          //使用单个mock对象
          singleMock.add("was added first");
          singleMock.add("was added second");

          //创建inOrder
          InOrder inOrder = inOrder(singleMock);

          //验证调用次数，若是调换两句，将会出错，因为singleMock.add("was added first")是先调用的
          inOrder.verify(singleMock).add("was added first");
          inOrder.verify(singleMock).add("was added second");

          // 多个mock对象
          List firstMock = mock(List.class);
          List secondMock = mock(List.class);

          //using mocks
          firstMock.add("was called first");
          secondMock.add("was called second");

          //创建多个mock对象的inOrder
          inOrder = inOrder(firstMock, secondMock);

          //验证firstMock先于secondMock调用
          inOrder.verify(firstMock).add("was called first");
          inOrder.verify(secondMock).add("was called second");
      }

      @Test
      public void testDemoObj() {

          TestDemoObj obj = mock(TestDemoObj.class);

          when(obj.getName()).thenReturn("Sally");

        //  assertEquals("Sally", obj.getName());

          verify(obj).getName();
      }

    }
    ```
