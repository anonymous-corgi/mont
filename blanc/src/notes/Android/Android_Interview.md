# Android Interview Questions:

- ### Android Component and Life-Cycle

  + ##### Activity:
    + 

  + ##### Activity Life-Cycle:
    + onCreate(): Call setContentView(); Initialize display layout; Initialize variables; Start background thread.
    + onStart(): Visible but not in the foreground. Reinitialize states.
    + onReStart(): 
    + onResume(): Register dynamic Boardcast Receivers; Restart animations; Restore variables.
    + onPause(): Unregister Boardcast Receivers; Stop animations; Save variables; Commit Changes.
    + onStop(): Close server connections; Free resources.
    + onDestory(): Close databases; Release remaining resources; Stop background thread.

  + ##### Service:

  + ##### Service Life-Cycle:
    + [Service Life-Cycle](https://blog.csdn.net/carson_ho/article/details/53160137)
    + onCreate: is called when the Service is created, and won't be called if the Service already exists.
    + onBind(): **the only method must be override**. is called when the Service is first time bound, and won't be called in the consective times.
    + onUnBind(): when the client(Activity, etc) is destroyed, it will automatically call unBind().

  + ##### BroadcastReceiver:

  + ##### LocalBroadcast & LocalBroadcastManager:
    + 
    consective
- ### Android Com
    
  + #####
    +
    
- ###
    
  + ##### Handling Screen roation:
    + 1. Set android:screenOrientation="portrait" or "landscape"
    + 2. Set android:configChanges="keyboardHidden|orientation|screenSize"
    + 3. onCreate(Bundle savedInstanceState)
    + 4. onRestoreInstanceState(Bundle savedInstanceState)


- ### Kotlin

  + ##### Advatages of Kotlin
    + Null-Safety: through nullable and non-nullable types ('?' '!!')
    + Concise Code: like 'for loop' and 
    + Kotlin Supports Full Java Interoperability
    + Lambda expressions: and is much faster than Java
    + Incremental compilation