## 바로가기 메뉴

![drawer](https://user-images.githubusercontent.com/37764504/84054409-53b67b00-a9ee-11ea-99b3-5b8114fef784.PNG)

툴바 레이아웃, 바로가기 메뉴시 보이는 프로필 화면 레이아웃, 프로필 화면 아래에 보일 메뉴 레이아웃 준비

activity_main.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<!-- 큰틀은 DrawerLayout -->
<androidx.drawerlayout.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"  
  xmlns:app="http://schemas.android.com/apk/res-auto"  
  xmlns:tools="http://schemas.android.com/tools"  
  android:id="@+id/drawer_layout"  
  android:layout_width="match_parent"  
  android:layout_height="match_parent"  
  android:fitsSystemWindows="true"  
  tools:openDrawer="start">  
  
	 <androidx.coordinatorlayout.widget.CoordinatorLayout  android:layout_width="match_parent"  
	  android:layout_height="match_parent" >  
		  <!-- 액션바 부분의 레이아웃 -->
		 <com.google.android.material.appbar.AppBarLayout 
		  android:layout_width="match_parent"  
		  android:layout_height="wrap_content"  
		  android:theme="@style/AppTheme.AppBarOverlay">  
  
			 <androidx.appcompat.widget.Toolbar  
			 android:id="@+id/toolbar"  
			  android:layout_width="match_parent"  
			  android:layout_height="?attr/actionBarSize"  
			  android:background="?attr/colorPrimary"  
			  app:popupTheme="@style/AppTheme.PopupOverlay" />  
  
		 </com.google.android.material.appbar.AppBarLayout>  
		 <!-- 프래그먼트가 보여질 레이아웃 -->
		 <FrameLayout  android:id="@+id/container"  
		  android:layout_width="match_parent"  
		  android:layout_height="match_parent"  
		  app:layout_behavior="@string/appbar_scrolling_view_behavior">  
		 </FrameLayout>  
 </androidx.coordinatorlayout.widget.CoordinatorLayout>  
	 <com.google.android.material.navigation.NavigationView
	 android:id="@+id/nav_view"  
	  android:layout_width="wrap_content"  
	  android:layout_height="match_parent"  
	  android:layout_gravity="start"  
	  android:fitsSystemWindows="true"
	  <!-- 프로필 화면 레이아웃 -->
	  app:headerLayout="@layout/nav_header_main" 
	  <!-- 프로필 화면 아래에 보여질 메뉴 레이아웃 -->
	  app:menu="@menu/activity_main_drawer" />  
  
</androidx.drawerlayout.widget.DrawerLayout>
```
MainActivity.java

```java
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FragmentCallback {  
  
  //바로가기 메뉴 항목 클릭시 각각 보여질 프래그먼트
	   Fragment1 fragment1;  
	  Fragment2 fragment2;  
	  Fragment3 fragment3;  
  
  //바로가기 메뉴 : drawerlayout
	  DrawerLayout drawer;  
	  Toolbar toolbar;  
  
  @Override  
  protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
	  setContentView(R.layout.activity_main);  
  
	  toolbar = findViewById(R.id.toolbar);
	  //상단 기본 액션바를 없애고 사용자가 만든 toolbar를 액션바로 지정
	  setSupportActionBar(toolbar);  
  
	  drawer = findViewById(R.id.drawer_layout);  
		  ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(  
	                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);  
	  drawer.addDrawerListener(toggle);  
	  toggle.syncState();  
  
	  NavigationView navigationView = findViewById(R.id.nav_view);  
	  navigationView.setNavigationItemSelectedListener(this);  
  
	  fragment1 = new Fragment1();  
	  fragment2 = new Fragment2();  
	  fragment3 = new Fragment3();  
  
	  getSupportFragmentManager().beginTransaction().add(R.id.container, fragment1).commit();  
  
  }  
  
  //뒤로가기 눌렀을때 실행하는 메소드
    @Override  
  public void onBackPressed() {
  //바로가기 메뉴가 열려진 상태에서 누르면 바로가기 메뉴를 닫고,
        if (drawer.isDrawerOpen(GravityCompat.START)) {  
            drawer.closeDrawer(GravityCompat.START);  
  } else { 
  //닫혀져있는 상태면 뒤로가기 버튼 누르기
            super.onBackPressed();  
  }  
    }  
    @Override  
  public boolean onNavigationItemSelected(MenuItem item) {
	  //item의 아이디로 구분
        int id = item.getItemId();  
  
	 if (id == R.id.menu1) {   
	  onFragmentSelected(0, null);  
	  } else if (id == R.id.menu2) {  
	  onFragmentSelected(1, null);  
	  } else if (id == R.id.menu3) { 
	  onFragmentSelected(2, null);  
	  }  
  
  //프래그먼트를 보여주면서 바로가기 메뉴 닫기
     drawer.closeDrawer(GravityCompat.START);  
  
	 return true;  
}  
  
    @Override  
  public void onFragmentSelected(int position, Bundle bundle) {  
        Fragment curFragment = null;  
  
		 if (position == 0) {  
		  curFragment = fragment1;  
		  toolbar.setTitle("첫번째 화면");  
		  } else if (position == 1) {  
		    curFragment = fragment2;  
		  toolbar.setTitle("두번째 화면");  
		  } else if (position == 2) {  
		        curFragment = fragment3;  
		  toolbar.setTitle("세번째 화면");  
		  }  
  
 getSupportFragmentManager().beginTransaction().replace(R.id.container, curFragment).commit();  
  }  
  
}
```

FragmentCallback.java
=> 어떤 프래그먼트를 보여줄지에 대한 메소드를 포함하는 인터페이스
```java
public interface FragmentCallback {  
    public void onFragmentSelected(int position, Bundle bundle);  
}
```
