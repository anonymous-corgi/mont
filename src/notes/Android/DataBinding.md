# DataBinding

- ###

  + #####
    +


- ### Work with observable data objects
  
  + ##### Why using observable?
    Any plain-old object can be used for data binding, but **modifying the object doesn't automatically cause the UI to update**.
  
  + ##### 

  
- ### Two-way data binding

|  Symbol   |   Object   | View→Value | Value→View |
| --------- |:----------:|:----------:|:----------:|
| @{value}  |    POJO    |     ✘      |     ✘      |
| @{value}  | Observable |     ✘      |     ✔      |
| @={value} |    POJO    |     ✔      |     ✘      |
| @={value} | Observable |     ✔      |     ✔      |
| @Bindable |    POJO    |     ✔      |     ✔      | 
  