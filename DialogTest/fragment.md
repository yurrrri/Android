# 프래그먼트
> - 한 화면에 여러 부분 화면을 넣는 방식
> - 부분화면을 독립적으로 넣어주는 방식

## 액티비티 화면에 프래그먼트 추가
액티비티를 추가하는 방식과 유사하다.
프래그먼트를 구성할 xml 레이아웃 파일과 자바 클래스 필요(Fragment 상속)

 1. Fragment.onCreateView() : 이 메소드에서 프래그먼트의 xml 레이아웃 인플레이션 실행(inflater.inflate)
 => 뷰 그룹 반환
 2.  xml 레이아웃에 직접 fragment 추가 혹은 자바 소스 코드에서 추가

**자바 소스 코드에서 프래그먼트 추가하기**

 xml 레이아웃과 프래그먼트 클래스 생성 후 -> 
 생성자로 프래그먼트 객체를 만들고, getSupportFragmentManager().beginTransaction().add() 메소드로 프래그먼트 추가

- getSupportFragmentManager().beginTransaction() :
프래그먼트를 변경하기 위한 트랜잭션 시작
트랜잭션 시작 후 프래그먼트 추가(add), 교체(replace), 삭제(remove) 가능

=> commit() 메소드를 호출 해야만 실제로 실행 ★

**추가한 fragment 객체 가져오기**

FragmentManager 객체 생성 후,
FragmentManager.findFragmentById() 메소드로 추가한 fragment를 찾아줌

## 프래그먼트의 버튼 클릭 시 다른 프래그먼트 전환

※ 프래그먼트도 액티비티와 같이 수명주기가 있음.
그러나 액티비티와는 다르게 onAttach() 와 onDetach() 수명주기 메소드가 존재하는데,
**프래그먼트는 반드시 액티비티에 올라와야만(onAttach) 프래그먼트의 기능이 동작하므로 onAttach() 메소드를 추가해야함**

MainActivity.java
```java
public class MainActivity extends AppCompatActivity {  
  MainFragment mainFragment;
  MenuFragment menuFragment;  
  
  @Override  
  protected void onCreate(Bundle savedInstanceState) {  
      super.onCreate(savedInstanceState);  
	  setContentView(R.layout.activity_main);  
  
	  mainFragment = new MainFragment();  //메인과 메뉴 프래그먼트 객체 생성
	  menuFragment = new MenuFragment();  
	  ...중략
}

//프래그먼트의 고유 번호를 받아 프래그먼트를 전환하는 메소드 생성
    public void onFragmentChange(int index){  
        if (index==0){  
            getSupportFragmentManager().beginTransaction().replace(R.id.container, menuFragment).commit();  
  }  
        else if (index==1){  
            getSupportFragmentManager().beginTransaction().replace(R.id.container, mainFragment).commit();  
  }  
    }  
}
```

MainFragment.java / MenuFragment.java
```java
public class MainFragment extends Fragment {  
   MainActivity activity;  
  Button btnMenu;  
  
  //액티비티에서 떨어져나가므로 activity 정보 삭제
  @Override  
  public void onDetach() {  
        super.onDetach();  
  
	  activity = null;  
  }  
  
  //액티비티에 올라올때 실행되는 메소드
    @Override  
  public void onAttach(@NonNull Context context) {  
        super.onAttach(context);  
  
  //getActivity() : 프래그먼트가 올라와있는 액티비티 반환
	  activity = (MainActivity) getActivity();  
  }  
  
    @Nullable  
	 @Override  
	 public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {  
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);  
  
	  btnMenu = rootView.findViewById(R.id.btnMenu);  
	  btnMenu.setOnClickListener(new View.OnClickListener() {  
        @Override  
	  public void onClick(View v) {
		  //인덱스를 0이라고 지정하여 액티비티 메소드의 매개변수로 전달
          activity.onFragmentChange(0);
//menu fragment일 경우 activity.onFragmentChange(1);
  }  
        });  
	 return rootView;  
  }  
}
```

## 프래그먼트로 이미지뷰어 구성
버튼 프래그먼트, 이미지뷰 프래그먼트 각각 구성 후 액티비티의 레이아웃에 프래그먼트 추가

ListFragment.java
```java
...중략
public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {  
    ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list, container, false);  
  
	  btnCream = rootView.findViewById(R.id.btnCream);  
	  btnVanilla = rootView.findViewById(R.id.btnVanilla);  
	  btnGrapeFruit = rootView.findViewById(R.id.btnGrapeFruit);  
  
	  btnCream.setOnClickListener(new View.OnClickListener() {  
	        @Override  
	  public void onClick(View v) {  
	            activity.onImageChange(0);  
	  }  
	    });  
  
	  btnVanilla.setOnClickListener(new View.OnClickListener() {  
	        @Override  
	  public void onClick(View v) {  
	            activity.onImageChange(1);  
	  }  
	    });  
  
	  btnGrapeFruit.setOnClickListener(new View.OnClickListener() {  
	        @Override  
	  public void onClick(View v) {  
	           activity.onImageChange(2);  
	  }  
	    });  
  
	 return rootView;  
}
```

MainActivity.java
```java
@Override  
protected void onCreate(Bundle savedInstanceState) {  
    super.onCreate(savedInstanceState);  
	 setContentView(R.layout.activity_main);  
  
  //매니저 객체 생성 후 fragment 찾아서 객체로 만들기
  manager = getSupportFragmentManager();  
  
  listFragment = (ListFragment) manager.findFragmentById(R.id.listFragment);  
  viewerFragment = (ViewerFragment) manager.findFragmentById(R.id.viewerFragment);  
  
}  
  
public void onImageChange(int index){  
    viewerFragment.setImage(index);  
}
```

ViewerFragment.java
```java
public void setImage(int index){  
    if (index==0){  
//setImageResource : imageView에 보여지는 이미지 설정
      imageView.setImageResource(R.drawable.starbucks_1);  
  }  
    else if (index==1){  
        imageView.setImageResource(R.drawable.starbucks_2);  
  }  
    else if (index==2){  
        imageView.setImageResource(R.drawable.starbucks_3);  
  }  
}
```

## 액션바
> 앱의 제목이 보이는 윗부분
> - 옵션메뉴
> - 컨텍스트 메뉴

### 옵션메뉴
![action_bar](https://user-images.githubusercontent.com/37764504/83974206-8eed7700-a926-11ea-9542-74fee2ba8abe.GIF)



1.  메뉴 구성
	- onCreateOptionsMenu 메소드의 매개변수 menu의 메소드로 직접 코드에서 구성
	- res/menu 폴더 추가 후 menu xml 별도로 작성 후 inflation 수행 ★
2. public boolean onCreateOptionsMenu(Menu menu)  : 옵션메뉴 생성

```java
@Override
public boolean onCreateOptionsMenu(Menu menu) {
//menu 폴더에 별도의 menu.xml 파일 생성 후 inflation 수행
    getMenuInflater().inflate(R.menu.memu_main, menu);  
 return true;
 }
```
3. 옵션 메뉴 항목 선택 처리
-   public boolean onOptionsItemSelected(@NonNull MenuItem item) : 옵션 메뉴 선택 시 실행하는 메소드
```java
@Override  
public boolean onOptionsItemSelected(@NonNull MenuItem item) {  
    switch(item.getItemId()){  //item.getItemId()로 메뉴 항목 구분

...중략 ...
    return true;  
}
```
-  XML 레이아웃에서 메뉴 선택시의 메소드 명 지정 => onOptionsItemSelected() 메소드보다 높은 우선순위를 가짐

### 컨텍스트 메뉴
> 특정 뷰를 롱클릭 시 메뉴 출력
> 
![context_menu](https://user-images.githubusercontent.com/37764504/83974267-28b52400-a927-11ea-8a66-58da2e6e4e1f.PNG)

1. Context Menu를 사용하는 View를 Activity에 등록 
=> 보통 Activity의 onCreate()에서 등록
2. onCreateContextMenu(ContextMenu menu, (View v, ContextMenu.ContextMenuInfo menuInfo)) 로 컨텍스트 메뉴 구현
=> View v : 여러 뷰에서 같은 컨텍스트 메뉴를 사용할 경우 v.getId()로 구분
3. onContextItemSelected(MenuItem item)으로 컨텍스트 메뉴 항목 시 동작 메소드 구현
=> item.getItemId()로 아이템 구분

MainActivity.java

```java
@Override  
protected void onCreate(Bundle savedInstanceState) {  
    super.onCreate(savedInstanceState);  
	setContentView(R.layout.activity_main);  
  
  myCircle = findViewById(R.id.myCircle);  
  r = myCircle.getR();  
  
  //컨텍스트 메뉴를 사용하는 액티비티에 컨텍스트 메뉴 등록
  registerForContextMenu(myCircle);  
}

//컨텍스트 메뉴 생성
@Override  
public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {  
    switch(v.getId()){  
        case R.id.myCircle:
        //menu xml 파일 별도 생성 후 인플레이션
            getMenuInflater().inflate(R.menu.menu_context, menu);  
			  onContextItemSelected(menu.getItem(id));  
		 break;  
	}  
}

//컨텍스트 메뉴의 아이템 선택시 동작하는 메소드 구현
@Override  
public boolean onContextItemSelected(@NonNull MenuItem item) {  
    switch(item.getItemId()){  
        case R.id.red:  
            id = 0;  
			  myCircle.setColor(Color.RED);  
			  myCircle.invalidate();  
			  item.setChecked(true);  
			 break; 
		... 중략 ...
	}  
    return super.onContextItemSelected(item);  
}
```
### 액션바에 아이콘 및 검색창 띄우기

![action_bar_icon](https://user-images.githubusercontent.com/37764504/83974852-3e2c4d00-a92b-11ea-816f-70c3a6022746.PNG)


menu_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>  
<menu xmlns:app="http://schemas.android.com/apk/res-auto"  
  xmlns:android="http://schemas.android.com/apk/res/android"  
  xmlns:xmnls="http://schemas.android.com/tools">  
  
	 <item  android:id="@+id/menu_refresh"  
	 <!-- icon 지정 -->
	  android:icon="@drawable/menu_refresh"  
	  android:title="새로고침"
	  <!-- icon이 항상 보이게 지정 -->
	  app:showAsAction="always" />  
	 <item  android:id="@+id/menu_search"  
	  android:icon="@drawable/menu_search"  
	  android:title="검색"  
	  app:showAsAction="always|withText"
	  <!-- 검색창 레이아웃 지정 -->
	  app:actionLayout="@layout/search_layout"/>  
	 <item  android:id="@+id/menu_settings"  
	  android:icon="@drawable/menu_settings"  
	  android:title="설정"  
	  app:showAsAction="always" />  
  
</menu>
```
MainActivity.java
```java
---중략---
@Override  
public boolean onCreateOptionsMenu(Menu menu) {
	//menu_main.xml 인플레이션  
    getMenuInflater().inflate(R.menu.menu_main, menu);  
	 return true;
}
```

※ **메뉴 항목의 showAsAction 속성**
=> 액션바에서 메뉴 항목을 어떻게 표시할 것인가?
- ifRoom : 여백이 있을 때에만 표시
- withText : 아이템을 텍스트와 표시
- never : 메뉴 아이콘을 눌렀을때만 표시
- always : 항상 액션바에 표시
- collapseActionView : 아이템에 커스텀 뷰가 지정되었을 때 축소되어 표시

## 탭
### 상단탭
![tab](https://user-images.githubusercontent.com/37764504/84041959-6f665500-a9df-11ea-907b-e1680039b86f.PNG)

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

### 하단탭

![tab2](https://user-images.githubusercontent.com/37764504/84042156-b0f70000-a9df-11ea-946c-58b20fdaccf4.PNG)

- BottomNavigationView 위젯 사용
- 상단탭과 같이 탭을 선택할때마다 보여줄 프래그먼트 xml과 자바 소스파일 생성 후, 리스너를 구현

MainActivity.java
```java
public class MainActivity extends AppCompatActivity {  
  
  Fragment1 fragment1;  
  Fragment2 fragment2;  
  Fragment3 fragment3;  
  
  @Override  
  protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
  setContentView(R.layout.activity_main);  
  
	  fragment1 = new Fragment1();  
	  fragment2 = new Fragment2();  
	  fragment3 = new Fragment3();  
	  
  getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();  
  
	  BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);  
	  bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {  
      @Override  
	  public boolean onNavigationItemSelected(@NonNull MenuItem item) {  
                switch (item.getItemId()) {  
                    case R.id.tab1: 
				  getSupportFragmentManager().beginTransaction()  
                               .replace(R.id.container,fragment1).commit();  
  
					 return true; 
					 case R.id.tab2:  
				 getSupportFragmentManager().beginTransaction()  
                               .replace(R.id.container,fragment2).commit();  
				  return true;
				  --- 중략 ---
                return false;  
  }  
        });  
  }  
}
```

## View Pager
> 좌우로 스크롤하여 넘겨볼 수 있는 위젯

![1](https://user-images.githubusercontent.com/37764504/84042455-0cc18900-a9e0-11ea-9f56-d6ebd856ed47.png)

1. 레이아웃에 뷰 페이저와 타이틀 스트립(전체 프래그먼트는 몇개이며 현재 프래그먼트는 무엇인지 보여주는 위젯) 배치
2. 프래그먼트 xml 파일과 자바 소스파일 생성
3. FragmentPagerAdapter를 상속하여 View Pager 어댑터 클래스 생성
=> Fragment를 원소로 가지는 ArrayList 생성 
4. 어댑터를 만들어 어댑터로 프래그먼트를 추가하고 뷰페이저 객체에 아답터를 연결

MainActivity.java
```java
public class MainActivity extends AppCompatActivity {  
  
	  ViewPager pager;  
	  Fragment1 fragment1;  
	  Fragment2 fragment2;  
	  Fragment3 fragment3;  
  
  @Override  
  protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
	  setContentView(R.layout.activity_main);  
  
	  pager = (ViewPager) findViewById(R.id.pager);  
	  //미리 로딩할 아이템의 개수 설정  
	  pager.setOffscreenPageLimit(3);  
  
	  //뷰페이저 아답터 객체 : 뷰페이지에 보여줄 각 프래그먼트 관리  
	  MyAdapter adapter = new MyAdapter(getSupportFragmentManager());  
  
	  //아답터로 프래그먼트 추가
	  fragment1 = new Fragment1();  
	  adapter.addItem(fragment1);  
	  
	  fragment2 = new Fragment2();  
	  adapter.addItem(fragment2);  
	  
	  fragment3 = new Fragment3();  
	  adapter.addItem(fragment3);  
  
	  //뷰페이저에 아답터 연결  
	  pager.setAdapter(adapter);  
  }  
  
 class MyAdapter extends FragmentPagerAdapter{  
        //프래그먼트를 담아둘 ArrayList 객체 생성  
	  ArrayList<Fragment> items = new ArrayList<Fragment>();  
	
	//생성자
	 public MyAdapter(@NonNull FragmentManager fm) {  
            super(fm);  
	  }  
  
    public void addItem(Fragment item){  
            items.add(item);  
  }  
  
	 @NonNull  
	 @Override
	 //아래 두개는 필수로 생성해야 하는 메소드
	 public Fragment getItem(int position) {  
            return items.get(position);  
  }  
  
	 @Override  
	  public int getCount() {  
            return items.size();  
  }  
  
      //타이틀스트립 : 전체 프래그먼트의 개수 및 현재의 프래그먼트를 알려주는 역할  
	  @Nullable  
	@Override
	public CharSequence getPageTitle(int position) {  
            return "페이지 "+position;  
  }  
    }  
}
```
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
