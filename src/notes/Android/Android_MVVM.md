# Android MVVM Architecture

- ### Reference Websites:
  
  + [如何构建Android MVVM 应用框架](https://tech.meituan.com/android_mvvm.html)

- ### Difference from MVP:
  + For MVP: UI operations are mostly in View layout, so View layout needs to expose UI operations to Presenter. Presenter also needs to expose APIs to View in order to receive events from View. So View and Presenter need to sign Contract.
  + For MVVM: UI and ViewModel(data) can directly communicate with each other, because they are bound together


- ### Advatages:
  + Work with Observable or LiveData, Network Callback doesn't need to hold reference to the View, won't cause crashed if the View was destroyed.
  + Maintain the state(data) of the View by Keeping the data inside ViewModel while suffering configuration change.
  + Extends the Advatages of MVP
  + For Data Driven UI: Reduces much work used to have in View and Presenter.
  + For the Data Driven UI: Because UI update is only related to the data change, it can make the service logic much conciser and easier.
  + Extra benefit of Chain of response.
  + Comunication between two Fragments becomes easier.

- ### ViewModel
  + Similar Function to Presenter.
  + ViewModel Provides Data source for View to update. We can also insert Action(CallBack) while data changed to control logic for View. 
  + 
  + Is used to store the data linked to a View
  + And OnPropertyChangedCallback

  The Data Binding Library generates binding classes that are used to access the layout's variables and views. 