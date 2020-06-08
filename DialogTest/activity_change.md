
# 화면 전환
## 레이아웃 인플레이션
>  XML 레이아웃의 내용이 메모리에 객체화되는 과정
> 즉, XML 문서의 텍스트 형태의 뷰가 실제 자바 객체화 하는 것
>  자바 객체로 변환되면, 자동으로 R에 id가 등록됨

-  setContentView(int layoutResId or View view) : View 를 전달받아 내부에서 inflation 수행 후 Activity에 등록하는 역할 수행 

![gho](https://user-images.githubusercontent.com/37764504/56455304-9b67e680-6397-11e9-9044-da25043c68f7.PNG)

- 부분화면을 구성할 경우:
부분 화면의 레이아웃 파일 생성 -> inflation으로 java 객체화 -> 메인 레이아웃에 추가

```java
@Override  
protected void onCreate(Bundle savedInstanceState) {  
  //부분화면을 띄울 뷰 생성
	  container = findViewById(R.id.container);  
	  
	  Button button = findViewById(R.id.button);  
	  button.setOnClickListener(new View.OnClickListener() {  
  @Override  
  public void onClick(View v) {
  	   //inflater 객체 생성 후,
          LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	   //인플레이션 할 레이아웃, 레이아웃이 담길 뷰, 뷰에 덧붙여지는지 여부 지정
	   inflater.inflate(R.layout.sub1, container, true);  
	  }  
    });  
}
```

## 인텐트
> 
> **※  앱의 구성요소** 
> ![1 (2)](https://user-images.githubusercontent.com/37764504/58582265-4f5c6b80-828c-11e9-91ff-4f98ab7822a8.jpg)
> - 액티비티 : 화면
> - 서비스 : 기능
> - 브로드캐스트 수신자 : 브로드캐스팅  메시지를 받는 역할
> - 내용 제공자: 한 앱에서 관리하는 데이터를 다른 앱도 접근하게 하도록 도와주는 역할

※ 액티비티 스택
![3](https://user-images.githubusercontent.com/37764504/83196380-c3b73c80-a176-11ea-87f2-e1255270410b.jpg)

쌓아두었다가 상위의 액티비티가 없어지면 그 전의 액티비티가 화면에 보여지는 방식
즉, 이전의 화면들은 화면 뒤에 쌓여져 있는 방식이다.

### 인텐트의 역할? 
> 어플리케이션 구성요소 (액티비티, 서비스, 브로드캐트 수신자, 내용 제공자) 간에 작업 수행을 위한 정보를 전달하는 역할

1. 새로운 액티비티 만들기
 - XML 레이아웃 파일과 자바 소스 파일 생성 필요
2. 메인 액티비티에서 새 액티비티 띄우기
- startActivity() 메소드로 띄우기
- 액티비티간에 응답을 주고받아야 할때				**startActivityForResult()** 사용
3. 새로운 액티비티에서 응답 보내기
- setResult() 메소드로 응답 보내기
4. 응답 처리하기
- 메인 액티비티에서 onActivityResult() 메소드를 재정의한 후 새 액티비티에서 받은 응답 처리

- MenuActivity(새 액티비티)
```java
@Override  
protected void onCreate(Bundle savedInstanceState) {  
	  super.onCreate(savedInstanceState);  
	  setContentView(R.layout.activity_menu);  
	  
	  Button button = findViewById(R.id.button);  
	  button.setOnClickListener(new View.OnClickListener() {  
  @Override  
  public void onClick(View v) {  
      Intent intent = new Intent();  
	  intent.putExtra("name", "mike");  
//인텐트 객체에 key와 value값 형태로 데이터 전달  
	  setResult(RESULT_OK, intent);  
	  //새로 띄운 액티비티에서 이전 액티비티로 인텐트 전달  
	  finish();  
	  //현재 액티비티를 화면에서 없앰
	  }  
    });  
}
```

- MainActivity
```java
public class MainActivity extends AppCompatActivity {  
    public static final int REQUEST_CODE_MENU = 101;  //액티비티를 구분하기 위한 코드 임의 지정
  
  @Override  //재정의할 메소드
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {  
        super.onActivityResult(requestCode, resultCode, data); 
  // data : 새 액티비티로부터 전달 받은 인텐트  
  
	  if (requestCode==REQUEST_CODE_MENU){  
            Toast.makeText(getApplicationContext(), "onActivityResult 메서드 	호출됨. 요청 코드 : "+requestCode+", 결과 코드 : "+resultCode, 	Toast.LENGTH_LONG).show();  
  
	 if (resultCode==RESULT_OK){  
                //RESULT_OK : 새 액티비티에서 처리한 결과가 정상인 경우
  String name = data.getStringExtra("name");  
  Toast.makeText(getApplicationContext(), "응답으로 전달된 name : "+name, Toast.LENGTH_LONG).show();  
  }  
        }  
    }  
  
    @Override  
  protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
	  setContentView(R.layout.activity_main);  
  
	  Button button = findViewById(R.id.button);  
	  button.setOnClickListener(new View.OnClickListener() {  
   
  
  @Override  
  public void onClick(View v) { 
	  //인텐트 객체 생성
     Intent intent = new Intent(getApplicationContext(),MenuActivity.class);  
	  startActivityForResult(intent, REQUEST_CODE_MENU);  
	  //startActivity와는 달리 새 액티비티로부터 응답을 받을 수 있음  
  }  
        });  
  }  
}
```

### 인텐트 메소드
- startActivity() : 새 액티비티를 띄울 때
- startActivityForResult() : 새 액티비티를 띄우고, 그로부터 응답을 받을 때
- startService() : 서비스를 시작할 때
- broadcastIntent() : 인텐트 객체를 브로드캐스팅 방식으로 전달할 때

### 인텐트 구성요소
- 액션 : 수행할 기능
- 데이터 : 수행될 대상의 데이터

### 인텐트 종류
- 명시적 인텐트 : 클래스나 컴포넌트를 지정하여 호출할 대상을 확실히 알 수 있는 경우
- 암시적 인텐트 : 호출할 대상이 달라질 수 있는 경우 -> 요청한 정보를 처리할 수 있는 적절한 컴포넌트를 찾은 다음 알아서 사용자에게 보여주는 과정을 거침

### 암시적 인텐트
- 범주 : 액션이 실행되는 데 필요한 추가적인 정보 제공
- 타입 : 인텐트에 들어가는 데이터의 MIME 타입
- 컴포넌트 : 인텐트에 사용될 컴포넌트 이름
- 부가 데이터 : 추가적인 정보를 넣을 수 있도록 번들 객체 포함

예시 1 - TEXT의 번호 대로 전화거는 화면
```java
String data = editText.getText().toString();  
  
Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(data));  
//전화번호 입력 화면으로 넘어가는 인텐트  
  
startActivity(intent);
```
## 플래그
> 시스템은 인텐트 별로 새 액티비티를 띄우기 때문에 중복된 액티비티가 나타날 수 있음
=> 이를 방지하기 위한 것이 플래그(Flag)

**1. FLAG_ACTIVITY_SINGLE_TOP**
새로 실행하려는 액티비티와 화면에 보이는 액티비티가 동일한 액티비티인 경우 메모리에 새로 만들지 않고 화면에 보이는 액티비티를 그대로 보여줌

![2](https://user-images.githubusercontent.com/37764504/83196958-b8b0dc00-a177-11ea-99ae-0d0b1cc7b4fd.png)

액티비티를 재사용할때, onNewIntent() 메소드를 이용

**2. FLAG_ACTIVITY_CLEAR_TOP**

![캡처](https://user-images.githubusercontent.com/37764504/83197456-776cfc00-a178-11ea-9542-a4ead9f0c55a.PNG)

이 액티비티 외의 다른 모든 액티비티를 종료시킴

## 부가데이터

> 다른 액티비티를 띄울 때 데이터를 전달해야하는 경우, 전달하는 인텐트 안에 부가 데이터를 넣어 전달함

![4](https://user-images.githubusercontent.com/37764504/83199947-2d3a4980-a17d-11ea-939b-aafdff227d6c.png)

인텐트 안의 번들 객체가 부가데이터를 넣고 빼는 기능 수행
- putExtra(Key, Value)
- get자료형Extra(자료형 name)

※ 객체 자료형의 경우 객체 자체를 전달할 수 없으므로 Serializable 객체를 구현해서 직렬화한 다음 전달해야함

**Parcelable 인터페이스**
> 객체를 전달할때 사용되는 인터페이스

- SimpleData.java
```java
public class SimpleData implements Parcelable {  
  
   int number;  
  String message;  
  
 public SimpleData(int number, String message) {  
     this.number = number;  
	 this.message = message;  
  }  
  
  //Parcel 객체에서 읽기
    public SimpleData(Parcel src){  
        number = src.readInt();  
	  message = src.readString();  
  }  
  
    //Parcel 객체에서 데이터를 읽어서 객체 생성  
  //반드시 static final로 선언되어야함!  
  public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){  
        public SimpleData createFromParcel(Parcel src){  
            return new SimpleData(src);  
  }  
  
        public SimpleData[] newArray(int size){  
            return new SimpleData[size];  
  }  
    };  
  
  //직렬화하려는 객체의 유형 구분하기 위한 메소드
  @Override  
  public int describeContents() {  
        return 0;  
  }  
  
    //객체의 데이터를 Parcel객체로 만들어줌  
  @Override  
  public void writeToParcel(Parcel dest, int flags) {  
        dest.writeInt(number);  
  dest.writeString(message);  
  }  
}
```

## 수명 주기
> 액티비티의 상태정보는 대표적으로 실행, 일시 정지, 중지가 있는데 이 상태정보의 변화가 수명주기

![png](https://user-images.githubusercontent.com/37764504/83895901-4a2ed800-a78e-11ea-9652-2a8d4007a3bd.png)

**호출되는 시점?**

- onCreate() : 액티비티가 처음 만들어졌을 때
- onStart() : 액티비티가 화면에 보이기 바로 전에
- onResume() : 액티비티가 사용자와 상호작용하기 전에 
- onPause() : 또 다른 액티비티를 시작하려고 할 때
- onStop() : 액티비티를 중지할 때
- onDestroy() : 액티비티가 소멸되어 없어지기 전에 

### SharedPreferences
> 앱에서 데이터를 저장하거나 복원할 때 쓰이는 인터페이스
- SharedPreferences.Editor = pref.edit() 호출 후,
- putOOO() 메소드로 데이터를 설정하거나
- clear()로 지울 수 있음

```java
public class MainActivity extends AppCompatActivity {    
  @Override  
  protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
  setContentView(R.layout.activity_main);  
  
... 중략
  
    @Override  
  protected void onResume() {  
        super.onResume();  
  
  //Toast.makeText(this, "onResume() 호출됨", Toast.LENGTH_SHORT).show();  
  
  SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);  
 if (pref!=null){  
            String name = pref.getString("name", "");
//SharedPreferences에 키 name으로 저장된 값 가져오기

  Toast.makeText(this, "복구된 이름 :"+name, Toast.LENGTH_SHORT).show();  
  }  
    }  
  
    @Override  
  protected void onStart() {  
        super.onStart();  
  
  Toast.makeText(this, "onStart() 호출됨", Toast.LENGTH_SHORT).show();  
  }  
  
    @Override  
  protected void onPause() {  
        super.onPause();  
  
  Toast.makeText(this, "onPause() 호출됨", Toast.LENGTH_SHORT).show();  
  SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);  
  SharedPreferences.Editor editor = pref.edit();  
  editor.putString("name", "yrrrrr");  //키를 name으로 해서 yrrrrr 이라는 문자열 저장
  editor.commit(); //저장할 때 이 메소드를 호출해야함  
  }  
  
    @Override  
  protected void onDestroy() {  
        super.onDestroy();  
  
  Toast.makeText(this, "onDestroy() 호출됨", Toast.LENGTH_SHORT).show();  
  }  
}
```
