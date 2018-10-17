# Java
- ### 1. Generics:

  + ##### PECS(Producer Extends, Consumer Super)
  + ##### If ***RedApple*** extends ***Apple*** extends ***Foods***
    + ###### ? super Apple -> Apple, Foods
    + ###### ? extends Apple -> Apple, RedApple

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
