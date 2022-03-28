# Java
- ### 1. Generics:

  + ##### Generics is a definition for varible reference.
  + ##### PECS(Producer Extends, Consumer Super)
  + ##### If *RedApple* extends *Apple* extends *Fruit* extends *Foods* extends *Object*
    + ###### ? super Apple -> Apple, Foods
    + ###### ? extends Apple -> Apple, RedApple
    
    + ###### Producer    
    + List<T> { fun get() : T }
    The reference List\<? extends Fruit\> is enabled to reference List\<Fruit\> or List\<Apple\> ... instances. (List\<Fruit\> can't refer to List\<Apple\>... instances.)
    So it is guaranteed to get a Fruit from from List\<? extends Fruit\>. The producer behaviour of List\<? extends Fruit\> is the same to the List\<Fruit\>.
    Conversely, adding a Fruit to List\<? extends Fruit\> is prohibited. Because List\<? extends Fruit\> might refer to List\<RedApple\>, adding Fruit to List\<RedApple\> is prohibited.
    
    + ###### Consumer
    + Knife<T> { fun cut(t: T) }
    The reference Knife\<? super Fruit\> can refer to Knife\<Fruit\> or Knife\<Foods\> ... instance.
    The situation is that we are guaranteed to have Fruit, and Knife\<? super Fruit\> refers to those instance that can consume Fruit.
    
  + Explanation:

    ```
    interface Function<T, R> {

      R apply(T t);

      default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
        return (V v) -> apply(before.apply(v));
      }

    }
    ```

    this.compose() is going to call before.apply().
    As this.compose() is providing V, we need to make sure that before.apply() is not consuming objects that lower than V.
    That means before.apply(? super V).
    this.compose() is call going to call this.apply().
    As this.apply(T t) requres T, we need to make sure that before.apply() returns objects that that is not higher than T.
    That means before.apply(? super V) return ? extends T.
    
    + C\<T\> can only refer C\<T\> instance.
    + C\<? extends T\> can refer C\<T\> and C\<those ? extends T\> instances.
    + C\<? super T\> can refer C\<T\> and C\<those ? super T\> instances.
  
  + ##### Definition of Producer and Consumer.
    + Producer\<T\> is that the instance is providing objects inside it or itself for other to use.
    + Consumer\<T\> is taking object relates to T inside it.
    + For Map<<? extends K>, <? extends V>>, actually the Key is also a producer. Because we require Producer to produce Key that is <? extends K>. The Map is a Consumer of Producer<? extends K>.
    
- ### 2. Lambda

  + ##### **->**

  > ```
  > (parameters) -> Expression
  > (class parameters) -> Expression
  >
  > (parameters) -> { Statements; }
  > (class parameters) -> { Statements; }
  > ```

  + ##### **::**

  > ```
  > class::method
  >
  > instance::method
  > ```

  > ```
  > *Type1:*
  > Function<char[], String> function = String::new;
  > Function<char[], String> function = String::valueOf;
  >
  > *Type2:*
  > Function<String, Integer> function = String::hashCode;
  > BiFunction<String, String, Boolean> biFunction = String::equals;
  > ```

- ### 3. Stream

  ***ForEach***

  ```
  interface Iterable<T> {

    default void forEach(Consumer<? super T> action) {
      Objects.requireNonNull(action);
        for (T t : this) {
          action.accept(t);
        }
    }

  }
  ```

  ***Stream<T\>***

  ```
  interface Stream<T> extends BaseStream<T, Stream<T>> {

    //Predicate<? super P_OUT> predicate: predicate.test(u)
    Stream<T> filter(Predicate<? super T> predicate);

    //Function<? super P_OUT, ? extends R> mapper: mapper.apply(u)
    <R> Stream<R> map(Function<? super T, ? extends R> mapper);

  ----------------------------------------------------------------
  ****Terminal Executions 1:**************************************
  ----------------------------------------------------------------

    boolean anyMatch(Predicate<? super T> predicate);

    boolean allMatch(Predicate<? super T> predicate);

    boolean noneMatch(Predicate<? super T> predicate);

    Optional<T> min(Comparator<? super T> comparator);

    Optional<T> max(Comparator<? super T> comparator);

  ----------------------------------------------------------------
  ****Terminal Executions 2:**************************************
  ----------------------------------------------------------------

    T reduce(T identity, BinaryOperator<T> accumulator);

    Optional<T> reduce(BinaryOperator<T> accumulator);

    <U> U reduce(U identity,
                 BiFunction<U, ? super T, U> accumulator,
                 BinaryOperator<U> combiner);

  ----------------------------------------------------------------
  ****Terminal Executions 3:**************************************
  ----------------------------------------------------------------

    <R> R collect(Supplier<R> supplier,
              BiConsumer<R, ? super T> accumulator,
              BiConsumer<R, R> combiner);

    <R, A> R collect(Collector<? super T, A, R> collector);

  }
  ```

  ***Predicate<T\>*** & ***Function<T, R\>*** & ***BiFunction<T, U, R\>*** & ***BinaryOperator<T\>***

  ```
  interface Predicate<T> {

    boolean test(T t);

    default Predicate<T> and(Predicate<? super T> other) {
      Objects.requireNonNull(other);
      return (t) -> test(t) && other.test(t);
    }

    default Predicate<T> negate() {
      return (t) -> !test(t);
    }

    default Predicate<T> or(Predicate<? super T> other) {
      Objects.requireNonNull(other);
      return (t) -> test(t) || other.test(t);
    }

    static <T> Predicate<T> isEqual(Object targetRef) {
      return (null == targetRef)
                ? Objects::isNull
                : object -> targetRef.equals(object);
    }

  }
  ```

  ```
  interface Function<T, R> {

    R apply(T t);

    default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
      Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }

    default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
      Objects.requireNonNull(after);
      return (T t) -> after.apply(apply(t));
    }

    static <T> Function<T, T> identity() {
      return t -> t;
    }

  }
  ```

  ```
  interface BiFunction<T, U, R> {

    R apply(T t, U u);

    default <V> BiFunction<T, U, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t, U u) -> after.apply(apply(t, u));
    }

  }
  ```

  ```
  interface BinaryOperator<T> extends BiFunction<T,T,T> {

    public static <T> BinaryOperator<T> minBy(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
    }

    public static <T> BinaryOperator<T> maxBy(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
    }

  }
  ```

  ***Comsumer<T\>*** & ***BiConsumer<T, U\>***

  ```
  interface Consumer<T> {

    void accept(T t);

    default Consumer<T> andThen(Consumer<? super T> after) {
      Objects.requireNonNull(after);
      return (T t) -> { accept(t); after.accept(t); };
    }

  }
  ```

  ```
  interface BiConsumer<T, U> {

    void accept(T t, U u);

    default BiConsumer<T, U> andThen(BiConsumer<? super T, ? super U> after) {
        Objects.requireNonNull(after);

        return (l, r) -> {
            accept(l, r);
            after.accept(l, r);
        };
    }

  }
  ```

  ***Collector<T, A, R\>*** & ***Collectors***

  ```
  interface Collector<T, A, R> {

    Supplier<A> supplier();

    BiConsumer<A, T> accumulator();

    BinaryOperator<A> combiner();

    Function<A, R> finisher();

  }
  ```

  ```
  class Collectors {

    <T> Collector<T, ?, List<T>> toList();

    <T> Collector<T, ?, Set<T>> toSet();

    <T, K, U> Collector<T, ?, Map<K,U>> toMap(Function<? super T, ? extends K> keyMapper,
                                Function<? super T, ? extends U> valueMapper);

    <T, K, U> Collector<T, ?, Map<K,U>> toMap(Function<? super T, ? extends K> keyMapper,
                                Function<? super T, ? extends U> valueMapper,
                                BinaryOperator<U> mergeFunction);

    <T, K> Collector<T, ?, Map<K, List<T>>> groupingBy(Function<? super T,? extends K> classifier);

  }
  ```

  ```

  ```
  ***Reduce() Examples***

  >  ```
  > // Concat String，concat = "ABCD"
  > String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
  >
  > // Get min，minValue = -3.0
  > double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
  >
  > // Get sum，sumValue = 10, with initial value
  > int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
  > // Get sum，sumValue = 10, without initial value
  > int sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
  >  ```
  
  
  
- ### 4. Constructor
  + ##### Order and 'this'
  ```java
  public class Level0 {

    String name = "Level0";

    public Level0() {
      System.out.println("Level0 constructor starts");
      printName1();
      printName2();
      printName3();
      System.out.println("Level0 constructor ends");
    }

    void printName1() {
      System.out.println("\tLevel0 print1: " + this.name);
    }
    void printName2() {
      System.out.println("\tLevel0 print2: " + this.name);
    }
    void printName3() {
      System.out.println("\tLevel0 print3: " + this.name);
    }
  }
  ```
  
  ```java
  public class Level1 extends Level0 {

    String name = "Level1";

    public Level1() {
      System.out.println("Level1 constructor starts");
      printName2();
      printName3();
      System.out.println("Level1 constructor ends");
    }

    void printName2() {
      System.out.println("\tLevel1 print2: " + this.name);
    }
    void printName3() {
      System.out.println("\tLevel1 print3: " + this.name);
    }
  }
  ```
  
  ```java
  public class Level2 extends Level1 {

    String name = "Level2";

    public Level2() {
      System.out.println("Level2 constructor starts");
      printName3();
      System.out.println("Level2 constructor ends");
    }
    
    void printName3() {
      System.out.println("\tLevel2 print3: " + this.name);
    }
  }
  ```
  
  ```
  Result of new Level2:
  Level0 constructor starts
  	Level0 print1: Level0
  	Level1 print2: null
  	Level2 print3: null
  Level0 constructor ends
  Level1 constructor starts
  	Level1 print2: Level1
  	Level2 print3: null
  Level1 constructor ends
  Level2 constructor starts
  	Level2 print3: Level2
  Level2 constructor ends
  ```
  **'this' always points to child firstly.**