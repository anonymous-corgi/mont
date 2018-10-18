# Android Tests

- ### 0. Docs:

  + ##### [Android Unit Test Collection](https://blog.csdn.net/column/details/18260.html)

- ### 1. Test Tools:

  + ##### Junit:
    + Assertion: [Class Assertions](https://junit.org/junit5/docs/current/api/org/junit/jupiter/api/Assertions.html)
    + @Test (expected = Exception.class)
    + @BeforeClass –> @Before –> @Test –> @After –> @AfterClass
    +
  + ##### Mockito:
    + 4 Ways of Mock:
      1. mock(xxx.class)
      2. @Mock + @Before MockitoAnnotations.initMocks(this);
      3. @RunWith(MockitoJUnitRunner.class) + @Mock

| Tools       | RunTime |     |
|:------------|:--------------|
| Junit       | JVM           |
| Mockito     | JVM           |
| Robolectric | JVM           |
| Espresso    | Dalvik or ART |

- ### 2. Unit Test:

  + ##### Dependency Isolation:
