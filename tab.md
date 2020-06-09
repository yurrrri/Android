## 탭
### 상단탭
![tab](https://user-images.githubusercontent.com/37764504/84041959-6f665500-a9df-11ea-907b-e1680039b86f.PNG)

[**소스코드**](https://github.com/yurrrri/Android_study/tree/master/MyTab)

1. 탭을 포함하는 레이아웃 구성
activity_main.xml
```xml
<?xml version="1.0" encoding="utf-8"?>  
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"  
  xmlns:app="http://schemas.android.com/apk/res-auto"  
  android:layout_width="match_parent"  
  android:layout_height="match_parent">  
  
 <androidx.coordinatorlayout.widget.CoordinatorLayout  android:layout_width="match_parent"  
  android:layout_height="match_parent">  
  
	 <com.google.android.material.appbar.AppBarLayout  android:layout_width="match_parent"  
	  android:layout_height="wrap_content"  
	  android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar">  
	  
		 <androidx.appcompat.widget.Toolbar  android:id="@+id/toolbar"  
		  android:layout_width="match_parent"  
		  android:layout_height="wrap_content"  
		  android:background="@color/colorPrimaryDark"  
		  android:elevation="1dp"  
		  android:theme="@style/ThemeOverlay.AppCompat.Dark">  
	  
			 <TextView  android:id="@+id/titleText"  
			  android:layout_width="wrap_content"  
			  android:layout_height="wrap_content"  
			  android:text="타이틀"  
			  android:textAppearance="@style/Base.TextAppearance.Widget.AppCompat.Toolbar.Title" />  
	  
		 </androidx.appcompat.widget.Toolbar>  
		 <com.google.android.material.tabs.TabLayout  android:id="@+id/tabs"  
		  android:layout_width="match_parent"  
		  android:layout_height="wrap_content"  
		  android:background="@android:color/background_light"  
		  android:elevation="1dp"  
		  app:tabGravity="fill"  
		  app:tabMode="fixed"  
		  app:tabSelectedTextColor="@color/colorAccent"  
		  app:tabTextColor="@color/colorPrimary" />  
	   </com.google.android.material.appbar.AppBarLayout>  
	   <FrameLayout  android:id="@+id/container"  
		  android:layout_width="match_parent"  
		  android:layout_height="match_parent"  
		  app:layout_behavior="@string/appbar_scrolling_view_behavior">  
		  
		</FrameLayout> 
	 </androidx.coordinatorlayout.widget.CoordinatorLayout>
 </RelativeLayout>
 ```
 
 2. 탭을 누를때마다 보여줄 프래그먼트 xml파일과 자바 소스파일 생성
 3. res/styles.xml에서 < style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar"> 로 수정
 4. MainActivity.java 에서 탭 선택했을시의 리스너 구현
```java
public class MainActivity extends AppCompatActivity {  
  
  Toolbar toolbar;  
  
  Fragment1 fragment1;  
  Fragment2 fragment2;  
  Fragment3 fragment3;  
  
  @Override  
  protected void onCreate(Bundle savedInstanceState) {  
       super.onCreate(savedInstanceState);  
	  setContentView(R.layout.activity_main);  
  
	  toolbar = findViewById(R.id.toolbar);
	  //styles.xml에서 액션바를 없앴으므로, 직접 만든 툴바를 ActionBar로 지정
	  setSupportActionBar(toolbar);  
  
	  ActionBar actionBar = getSupportActionBar();
	  //프로젝트 이름 보이게 하지 않기
	  actionBar.setDisplayShowTitleEnabled(false);   
  
	  //탭마다 보여줄 프래그먼트 객체 생성
	  fragment1 = new Fragment1();  
	  fragment2 = new Fragment2();  
	  fragment3 = new Fragment3();  
  
	  //화면을 처음 띄웠을때 보여줄 fragment 지정 -> 여기서는 fragment1
	 getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();  
  
	  TabLayout tabs = findViewById(R.id.tabs);
	  //소스코드 자체 내에서 탭 추가 
	  tabs.addTab(tabs.newTab().setText("통화기록"));  
	  tabs.addTab(tabs.newTab().setText("스팸기록"));  
	  tabs.addTab(tabs.newTab().setText("연락처"));  
  tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {  
            @Override  
	  public void onTabSelected(TabLayout.Tab tab) {  
            int position = tab.getPosition();  
  
			 Fragment selected = null;  
			 if (position == 0) {  
			                    selected = fragment1;  
			  } else if (position == 1) {  
			                    selected = fragment2;  
			  } else if (position == 2) {  
			                    selected = fragment3;  
			  }  
             getSupportFragmentManager().beginTransaction()  
                        .replace(R.id.container, selected).commit();  
		  }  
  }  
  
}
```
