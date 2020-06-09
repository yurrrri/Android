## View Pager
> 좌우로 스크롤하여 넘겨볼 수 있는 위젯

![1](https://user-images.githubusercontent.com/37764504/84042455-0cc18900-a9e0-11ea-9f56-d6ebd856ed47.png)
![mypager](https://user-images.githubusercontent.com/37764504/84191147-0829cd00-aad3-11ea-928f-8cb3dc43fade.PNG)

[**소스코드**](https://github.com/yurrrri/Android_study/tree/master/MyPager)

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
