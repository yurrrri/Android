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

![슬라이드1](https://user-images.githubusercontent.com/37764504/84190085-4b833c00-aad1-11ea-9912-9f3517c7c50e.PNG)

- 상단의 메뉴화면/메인화면을 누르면 각각의 화면을 보여주도록 구현
- 프래그먼트의 버튼을 눌렀을 경우에도 각각의 화면을 보여주도록 구현

1. 프래그먼트 각각의 레이아웃과 자바 소스 파일 생성
2. 메인 레이아웃 하단에 프래그먼트 배치

[**소스코드**](https://github.com/yurrrri/Android_study/tree/master/MyFragment)

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
	//menu fragment일 경우 
	//activity.onFragmentChange(1);
	}  
        });  
	 return rootView;  
  }  
}
```

## 프래그먼트로 이미지뷰어 구성

![imageviewer](https://user-images.githubusercontent.com/37764504/84190384-cfd5bf00-aad1-11ea-9171-47120706301e.PNG)

버튼 프래그먼트, 이미지뷰 프래그먼트 각각 구성 후 액티비티의 레이아웃에 프래그먼트 추가

[**소스코드**](https://github.com/yurrrri/Android_study/tree/master/MyFragment2)

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

## [액션바](https://github.com/yurrrri/Android_study/blob/master/actionbar.md)
> 앱의 제목이 보이는 윗부분
> - 옵션메뉴
> - 컨텍스트 메뉴

## [탭](https://github.com/yurrrri/Android_study/blob/master/tab.md)
### 상단탭
![tab](https://user-images.githubusercontent.com/37764504/84041959-6f665500-a9df-11ea-907b-e1680039b86f.PNG)

## [View Pager](https://github.com/yurrrri/Android_study/blob/master/viewpager.md)
> 좌우로 스크롤하여 넘겨볼 수 있는 위젯

## [바로가기 메뉴](https://github.com/yurrrri/Android_study/blob/master/navigation.md)

![drawer](https://user-images.githubusercontent.com/37764504/84054409-53b67b00-a9ee-11ea-99b3-5b8114fef784.PNG)
