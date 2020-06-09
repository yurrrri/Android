
# 이벤트 처리

1. 상속 메소드 재정의 : View가 가지고 있는 기본적인 이벤트 처리 메소드 재정의
2. 리스너 인터페이스 구현 ★
3. XML의 속성에 메소드 명 등록 후 메소드 정의

## 리스너 Interface 구현

![onClickListener](https://user-images.githubusercontent.com/37764504/56054885-fba4ca00-5d92-11e9-8950-33ddfeeec2d4.PNG)

- View.onTouchListener : 터치할 때 발생하는 이벤트 처리
- View.onKeyListener : 키패드나 하드웨어 버튼 입력 시 발생하는 이벤트 처리
- View.onClickListener : 클릭 시 발생하는 이벤트 처리
- View.onLongClickListener : 롱 클릭 시 발생하는 이벤트 처리

### 터치 이벤트 리스너

```java
public class MainActivity extends AppCompatActivity {  
--- 중략 ---
	  
	     View view = findViewById(R.id.view); 
	      //리스너 등록		//리스너 객체 구현
	     view.setOnTouchListener(new View.OnTouchListener() { 
	         @Override  
		public boolean onTouch(View view, MotionEvent motionEvent) {
			//터치 액션 구분
	                int action = motionEvent.getAction();  
			//터치된 X, Y좌표
			float curX = motionEvent.getX();  
			float curY = motionEvent.getY();  
	  
			 if (action == MotionEvent.ACTION_DOWN) {  //눌렸을 때
				 println("손가락 눌림 : " + curX + ", " + curY);  
			  } else if (action == MotionEvent.ACTION_MOVE) {  //눌린 채로 움직임
				 println("손가락 움직임 : " + curX + ", " + curY);  
			  } else if (action == MotionEvent.ACTION_UP) {  //떼졌을 때
				 println("손가락 뗌 : " + curX + ", " + curY);  
			  }  

		   	return true;  
		}  
        });  
  
  }  
  
    public void println(String data) {  
        textView.append(data + "\n");  
  }  
}
```

## 제스처 이벤트 리스너

- GestureDectector : 제스처 이벤트를 처리해주는 클래스
dectetor = new GestureDector(this, new GestureDectector.OnGestureListener() { ... ) 에서 메소드 정의
- 터치 이벤트 전달 시 GestureDectector가 상황에 맞게 메소드 호출


```java
public class MainActivity extends AppCompatActivity {  
 ---중략---
  
  GestureDetector detector; //제스처 디텍터 객체  

  detector = new GestureDetector(this, new GestureDetector.OnGestureListener() {  
  @Override
  //화면이 눌렸을 경우
  public boolean onDown(MotionEvent e) {  
  
         println("onDown() 호출됨.");  
         return true;  
  }  
  
  //화면이 눌렸다가 떼어지는 경우
   @Override  
  public void onShowPress(MotionEvent e) {  
          
          println("onShowPress() 호출됨.");  
  }  
  
  
  //화면이 한 손가락으로 눌렸다 떼어지는 경우
  @Override  
  public boolean onSingleTapUp(MotionEvent e) {   
                  
           println("onSingleTapUp() 호출됨.");  
            return true;  
  }  
  
  //눌린 채 스크롤되는 경우
  @Override  
  public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {  
                  
           println("onScroll() 호출됨 : " + distanceX + ", "+distanceY);  
           return true;  
  }  
  
 ---- 중략 ---- 
 
  View view2 = findViewById(R.id.view2);  
  view2.setOnTouchListener(new View.OnTouchListener() {  
	  @Override  
	  public boolean onTouch(View v, MotionEvent event) {  
		//터치 이벤트 발생 시 GestureDectector 객체에서 메소드 호출하여 처리           
               detector.onTouchEvent(event);
		return true;  
		}  
        });
  }  
      
```

## 키 이벤트 리스너

- View의 이벤트 처리 메소드 재정의를 통해 이벤트를 처리함
- boolean onKeyDown(int keyCode, KeyEvent event)
- boolean onKey (View v, int keyCode, KeyEvent event)

MainActivity.java

```java
-- -중략 ---

@Override //재정의
public boolean onKeyDown(int keyCode, KeyEvent event){
	if (keyCode == KeyEvent.KEYCODE_BACK){
		
		...
	return true;
	}
	return false;
}
... 
```
