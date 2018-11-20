# Android MVVM Architecture

- ### Reference Websites:
  
  + [如何构建Android MVVM 应用框架](https://tech.meituan.com/android_mvvm.html)

- ### Difference from MVP:
  + For MVP: UI operations are mostly in View layout, so View layout needs to expose UI operations to Presenter. Presenter also needs to expose APIs to View in order to receive events from View. So View and Presenter need to sign Contract.
  + For MVVM: UI and ViewModel(data) can directly communicate with each other, because they are bound together


- ### Advatages:
  + Maintain the state and data of the view by Keeping the data inside ViewModel while suffering configuration change.
  + Prevent crashing due to expired view reference. Network Callback doesn't need to hold reference to the View, won't cause crashed if the View was destroyed.
  + ViewModel Can be reused in similar View, reducing much work.
  + 
  + Extends the Advatages of MVP
  + For the Data Driven UI: Reduces much work used to have in View and Presenter, because UI update is only related to the data change, it can make the service logic much conciser and easier.
  + Data sharing and Comunication between two Fragments becomes easier.

- ### ViewModel
  + The ViewModel class is designed to store and manage UI-related data in a lifecycle conscious way. The ViewModel class allows data to survive configuration changes such as screen rotations.
  + Similar Function to Presenter.
  + ViewModel Provides Data source for View to update. We can also insert Action(CallBack) while data changed to control logic for View. 
  + The Data Binding Library generates binding classes that are used to access the layout's variables and views. 