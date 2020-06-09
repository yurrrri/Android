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
			  item.setChecked(true);  
			 break; 
		... 중략 ...
	}  
    return super.onContextItemSelected(item);  
}
```
### 액션바에 아이콘 및 검색창 띄우기

![action_bar_icon](https://user-images.githubusercontent.com/37764504/83974852-3e2c4d00-a92b-11ea-816f-70c3a6022746.PNG)

[**소스코드**](https://github.com/yurrrri/Android_study/tree/master/MyActionBar)

- res폴더에 menu 폴더 생성 후 메뉴 레이아웃
- 검색창 레이아웃 필요!

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
