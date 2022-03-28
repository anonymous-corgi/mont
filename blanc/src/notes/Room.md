# Room

- ### 

  + #####
    +

- ### Reference Websites:
  
  + [Save data in a local database using Room](https://developer.android.com/training/data-storage/room/#kotlin)
- ### 3 Major Components

  + ##### Database:
    Contains the database holder and serves as the main access point for the underlying connection to your app's persisted, relational data.
    The class that's annotated with @Database should satisfy the following conditions:
    + Be an abstract class that extends RoomDatabase.
    + Include the list of entities associated with the database within the annotation.
    + Contain an abstract method that has 0 arguments and returns the class that is annotated with @Dao.  
    
    At runtime, you can acquire an instance of Database by calling Room.databaseBuilder() or Room.inMemoryDatabaseBuilder().
  
  + ##### Entity:
    Represents a table within the database.
  + ##### DAO:
    Contains the methods used for accessing the database.
  
- ### [Accessing data using Room DAOs](https://developer.android.com/training/data-storage/room/accessing-data)
  Note: Room doesn't support database access on the main thread unless you've called allowMainThreadQueries() on the builder because it might lock the UI for a long period of time. Asynchronous queries—queries that return instances of LiveData or Flowable—are exempt from this rule because they asynchronously run the query on a background thread when needed.
  
  + ##### Observable queries
  ```kotlin
  @Dao
  interface MyDao {
    @Query("SELECT first_name, last_name FROM user WHERE region IN (:regions)")
    fun loadUsersFromRegionsSync(regions: List<String>): LiveData<List<User>>
  }
  ```

  ```kotlin
  @Dao
  interface ConcertDao {
    // The Int type parameter tells Room to use a PositionalDataSource
    // object, with position-based loading under the hood.
    @Query("SELECT * FROM concerts ORDER BY date DESC")
    fun concertsByDate(): DataSource.Factory<Int, Concert>
  }
  ```
  
  + ##### Reactive queries with RxJava

    + @Query methods: Room supports return values of type Publisher, Flowable, and Observable.
    + @Insert, @Update, and @Delete methods: Room 2.1.0 and higher supports return values of type Completable, Single<T>, and Maybe<T>.
    
  ```kotlin
  @Dao
  interface MyDao {
    @Query("SELECT * from user where id = :id LIMIT 1")
    fun loadUserById(id: Int): Flowable<User>

    // Emits the number of users added to the database.
    @Insert
    fun insertLargeNumberOfUsers(users: List<User>): Maybe<Int>

    // Makes sure that the operation finishes successfully.
    @Insert
    fun insertLargeNumberOfUsers(varargs users: User): Completable

    /* Emits the number of users removed from the database. Always emits at
       least one user. */
    @Delete
    fun deleteAllUsers(users: List<User>): Single<Int>
  }
  ```
  

  
  
  
   