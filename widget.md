# 위젯

## 토스트
> 메시지를 잠깐 보여주었다가 없어지는 뷰
> 
>![sdf](https://user-images.githubusercontent.com/37764504/56305276-f244a480-617a-11e9-8d80-2358eb3a6f6b.PNG)

- Toast.makeText(Context, Text, Toast.LENGTH_LONG/SHORT).show();

## 스낵바
> 아래에 잠깐 뜨는 상자
>
> ![sdgh](https://user-images.githubusercontent.com/37764504/56305486-61ba9400-617b-11e9-972c-0e242eec1ba1.PNG)

- 디자인 라이브러리 추가
File -> Project Structure.... -> app-> Dependencies -> + 아이콘 -> Library dependency -> com.android.support:design -> OK
- Snackbar.make(View, Text, Snackbar.LENGTH_LONG/SHORT).show(); 

## [대화상자](https://github.com/yurrrri/Android_study/new/master/dialog.md)

> ![hfch](https://user-images.githubusercontent.com/37764504/56305646-a1817b80-617b-11e9-83b0-9e0d82dbbaa9.PNG)

AlertDialog.Builder 클래스로 생성

## 프로그레스바
> 어떤 작업의 진행 상태를 보여줄 때 쓰는 위젯
> 
> 
> ![dfjdf](https://user-images.githubusercontent.com/37764504/56305836-fa511400-617b-11e9-9b0e-11e14285e9ec.PNG)
> 
> ![fgjfgj](https://user-images.githubusercontent.com/37764504/56305825-f6bd8d00-617b-11e9-952e-470060a2eb31.PNG)
>   


## 시크바
> ![dfhdf](https://user-images.githubusercontent.com/37764504/56454572-f1d02780-638d-11e9-958b-2210aea1a546.PNG)


## [어댑터 뷰](https://github.com/yurrrri/Android_study/blob/master/adapterview.md)
> - 여러 개의 아이템 중에서 하나를 선택하는 방식을 가진 위젯
> - 어댑터를 통해 각각의 아이템과 데이터를 관리하는 패턴
> - 리스트뷰, 스피너, 그리드뷰, 갤러리

![1 (1)](https://user-images.githubusercontent.com/37764504/58173324-07f73d80-7cd6-11e9-8425-99a9a7ae27df.jpg)


### 스피너

> ![spinner](https://user-images.githubusercontent.com/37764504/83966941-70708700-a8f8-11ea-99c3-1daa6fa71fd1.png)

## [드로어블](https://github.com/yurrrri/Android_study/blob/master/drawable.md)

> 뷰에 설정할 수 있는 객체이며 그 위에 그래픽 그리기 가능
