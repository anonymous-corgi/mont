# Android Tests

- ### 0. Docs:

  + ##### [Android Unit Test Collection](https://blog.csdn.net/column/details/18260.html)
  + ##### [Android 测试](https://blog.csdn.net/lmj623565791/article/details/79623159)

- ### 1. Test Tools:

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

| Tools       | RunTime |          |
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


- ### 2. Unit Test:

  + ##### Dependency Isolation:
