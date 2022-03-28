# Picasso

- ### Reference Websites:
  
  + [Picasso源码解析](http://www.voidcn.com/article/p-qkcxuekj-pa.html)

- ### Dependency
  
  + ##### build.gradle(Module:app)
  ```
  dependencies {
    implementation 'com.squareup.picasso:picasso:2.5.2'
  }
  ```
  > **Picasso.with(Context context)** has been deprecated.
  > Replaced by **Picasso.get()**
  
- ### SourceCode Analysis

  + ##### Elements:

    + ***Request***
      Contains the URL and all Modification requirement of the Image.
      Using Request can create a ***requestKey***.
      
    + ***(String) requestKey***
      Consists of  1. Uri or resId 2. rotationDegrees 3. targetSize(width, height) 4. centerCrop or centerInside
    
    + ***RequestCreator***
      Similar to Builder mode.
    
    + ***ImageViewAction extends Action\<ImageView\>***
      Contains the Callback action, which is to set the image to the target display destination. Also contains a **Request**, a **requestKey**, targetView(Destination), and other settings.
      Has flag **cancel** indicating the validation of this action. If cancel is true, this action won't be excuted in the final stage.
    
    + ***BitmapHunter implements Runnable***
      A thread used to perform actual down action.
      Each **BitmapHunter** corresponds to a **requestKey**.
      Picasso will prevent generating a **BitmapHunter** with an existed **requestKey**.
      Contains an Action(ImageViewAction)
  
- ### Note:

  + ##### Asynchronous Problem in RecyclerView
    1.Picasso uses the *BitmapHunterMap* and requestKey to prevent repeating download the same image in same size requirement.

    2.The mothod **into()** will finally call method **enqueueAndSubmit()**. enqueueAndSubmit() will call **cancelExistingRequest(target)**. The target is ImageView or target diplace destination. The previous pending actions that intent to set the same target(ImageView) will be set **cancel** true.  
    
    3.Althrough an Action will be canceled, the Image will be still downloaded.
    
    4.Picasso.get() will return a **Singleton**, carring all the Cache in Memory.  

  + ##### Image's ID - requestKey
    
    Every image download intention inside Picasso is assigned as Request. So, Images with different sizes but same URL are different.

- ### Basic Utilization

  + ##### ImageView
  ```java
  Picasso.get()
         .load(url)
         .placeholder(R.drawable.tab_item_bg)
         .into(imageView);
  ```
  
  + ##### Customed View implements Target interface
  ```java
  Picasso.get()
         .load(url)
         .placeholder(R.drawable.tab_item_bg)
         .into(target);
  ```

- ### Other Utilization
  
  + ##### Load()
| Method                 | Description                        |
|:-----------------------|:-----------------------------------|
| load(int resId)        | load()                             |
| load(File file)        | load()                             |
| load(String url)       | load()                             |
| placeholder(int resId) | Default Image                      |
| error(int resId)       | Alternative to error network image |
| noPlaceholder()        | Cancel    the default placeholder  |

  + ##### Modification

| Method                        | Description                                                         |
|:----------------------------- |:------------------------------------------------------------------- |
| resize(int hori, int vert)    | Fit cannot be used with resize                                      | 
| resizeDimen(resHori, resVert) | Retrieve size from xml Dimen                                        |
| fit()                         | Equal to calling resize() and setting targetSize to the View's size |
| scaleDown(x, y)               |                                                                     |
| onlyScaleDown()               |                                                                     |
| centerCrop()                  | Crop to fully fill into ImageView *Must Call resize()               |
| centerInside()                | Both hori and vert are smaller than ImageView *Must Call resize()   |

| Method     | Description                      |
|:-----------|:---------------------------------|
| priority() | Picasso.Priority.HIGH/MEDIUM/LOW |

  + ##### Cache Strategy
  Search: Memory -> Network
  
  ```java
  memoryPolicy()
  ```
| MemoryPolicy          | Description               |
|:--------------------- |:------------------------- |
| MemoryPolicy.NO_CACHE | Not search in Memory      |
| MemoryPolicy.NO_STORE | Image not store in Memory |

  ```java
  networkPolicy()
  ```
| NetworkPolicy          | Description         |
|:---------------------- |:------------------- |
| NetworkPolicy.NO_CACHE | Not store in Disk   |
| NetworkPolicy.OFFLINE  | Only search in Disk |

 
- ### Demo:

  ```java
  Picasso  
      .get()
      .load(firstImageUrl)
      .placeholder(R.mipmap.ic_launcher) 
      .into(imageView, new Callback() {
          @Override
          public void onSuccess() {
              Picasso
                 .get()
                 .load(secondImageUrl)
                 .noPlaceholder() 
                 .into(imageView);
          }
          @Override
          public void onError() {
          }
      });
  ```
  
  ```java
  Picasso  
    .get()
    .load(UsageExampleListViewAdapter.eatFoodyImages[0])
    .tag("Profile ListView") // can be any Java object
    .into(imageViewWithTag);

  //onScrollStateChanged()  
  public class SampleScrollListener implements AbsListView.OnScrollListener {
    
    private final Context context;

    public SampleScrollListener(Context context) {
      this.context = context;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
      final Picasso picasso = PicassoProvider.get();
      //final Picasso picasso = Picasso.get();
      
      if (scrollState == SCROLL_STATE_IDLE || scrollState == SCROLL_STATE_TOUCH_SCROLL) {
        picasso.resumeTag(context);
      } else {
        picasso.pauseTag(context);
      }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                         int totalItemCount) {
      // Do nothing.
    }
  }

  //AbsListView.OnScrollListener
  listView.setOnScrollListener(new SampleScrollListener(context));
  ```
  
  ```java
  ivPic11 = new ImageView(QuanPicActivity.this);
  //  ImageView imageView = listImg.get(position);
  //  Log.e("******listImg****",listImg.size()+":onClick");
  Picasso.with(QuanPicActivity.this).load(pic.get(position).toString()).into(ivPic11, new com.squareup.picasso.Callback() {
      @Override
      public void onSuccess() {
          Bitmap bmap = ((BitmapDrawable) ivPic11.getDrawable()).getBitmap();
          if (bmap == null) {
              Log.e("******bmap****", "null");
          }
          if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
              Log.e("bitmap", "success***********");
              String sdCardPath = Environment.getExternalStorageDirectory().getPath();
              File file = new File(sdCardPath + "/123");
              if (!file.exists()) {
                  file.mkdirs();
              }
              File imageFile = new File(file.getAbsolutePath(), "one" + ".jpg");
              FileOutputStream outStream = null;
              try {
                  outStream = new FileOutputStream(imageFile);
                  bmap.compress(Bitmap.CompressFormat.PNG, 90, outStream);
                  outStream.flush();
                  outStream.close();
              } catch (Exception e) {
                  e.printStackTrace();
              }
              File imageFile2 = new File(file.getAbsolutePath(), "one" + ".jpg");
              Bitmap bt=BitmapFactory.decodeFile(imageFile2.toString());
              ivPic.setImageBitmap(bt);
          }
      }
  ```
