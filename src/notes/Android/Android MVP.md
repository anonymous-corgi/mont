# Android MVP Architecture

- ### 

  + #####
    +
    
    

- ### Template 

  + ##### Base 

    + ***BaseView***
    ```java
    public interface BaseView<T> {

    }
    ```

    + ***BasePresenter***
    ```java
    public interface BasePresenter<T> {

        /**
         * Binds presenter with a view when resumed. The Presenter will perform initialization here.
         *
         * @param view the view associated with this presenter
         */
        void takeView(T view);

        /**
         * Drops the reference to the view when destroyed
         */
        void dropView();

    }
    ```
    
    + ***Contract***
    ```java
    public interface Contract {

      /**
       * Defines all the View Operation APIs exposed to Presenter, through
       * which Presenter can operate the View.
       */
      interface View extends BaseView<Presenter> {
        
      }
      
      /**
       * Defines all the 'listener' APIs method to receive actions from View.
       * So that, Presenter is able to monitor any change in View.
       */
      interface Presenter extends BasePresenter<View> {

      }
    }
    ```  
    