Java
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

- ### 2. Stream
  ```
  interface Iterable<T> {

    default void forEach(Consumer<? super T> action) {
      Objects.requireNonNull(action);
        for (T t : this) {
          action.accept(t);
        }
    }

  }

  interface Consumer<T> {

    void accept(T t);

    default Consumer<T> andThen(Consumer<? super T> after) {
      Objects.requireNonNull(after);
      return (T t) -> { accept(t); after.accept(t); };
    }

  }
  ```
  ```
  interface Stream<T> extends BaseStream<T, Stream<T>> {

    //Predicate<? super P_OUT> predicate: predicate.test(u)
    Stream<T> filter(Predicate<? super T> predicate);

    //Function<? super P_OUT, ? extends R> mapper: mapper.apply(u)
    <R> Stream<R> map(Function<? super T, ? extends R> mapper);

    //
    boolean anyMatch(Predicate<? super T> predicate);

    boolean allMatch(Predicate<? super T> predicate);

    boolean noneMatch(Predicate<? super T> predicate);

    //
    <R> R collect(Supplier<R> supplier,
              BiConsumer<R, ? super T> accumulator,
              BiConsumer<R, R> combiner);

    <R, A> R collect(Collector<? super T, A, R> collector);

  }
  ```
  *** Predicate<T> & Function<T, R> ***
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
  ***Collections***
  ```
  class Collectors {

    Collector<T, ?, List<T>> toList();

    Collector<T, ?, Set<T>> toSet();

    Collector<T, ?, Map<K,U>> toMap(Function<? super T, ? extends K> keyMapper,
                                Function<? super T, ? extends U> valueMapper);

    Collector<T, ?, Map<K,U>> toMap(Function<? super T, ? extends K> keyMapper,
                                Function<? super T, ? extends U> valueMapper,
                                BinaryOperator<U> mergeFunction);

    groupingBy(Function<? super T,? extends K> classifier);

  ```
