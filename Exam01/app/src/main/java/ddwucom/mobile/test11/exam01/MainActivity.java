package ddwucom.mobile.test11.exam01;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter adapter;
    ArrayList<Food> foodList;
    FoodManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = new FoodManager();

        listView = findViewById(R.id.listView);

        // DataManager 적용해 볼 것
        foodList = manager.getFoodList();

        // Food 객체의 toString() 메소드가 호출되어 하나의 문자열로 처리됨
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, foodList);

        listView.setAdapter(adapter);

        // listView 롱클릭 이벤트 추가
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final int pos = position;
                String food = foodList.get(pos).getFood();

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("음식 삭제")
                       .setIcon(R.mipmap.ic_launcher)
                        .setMessage(food+" (을)를 삭제 하시겠습니까?")
                        .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                manager.removeFood(pos);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("취소", null)
                        .setCancelable(false)
                        .show();
                return true;
            }
        });
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.button:
                final ConstraintLayout addfood_layout = (ConstraintLayout) View.inflate(this, R.layout.addfood_layout, null);

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("음식 추가")
                        .setView(addfood_layout)
                        .setPositiveButton("추가", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText edtName = addfood_layout.findViewById(R.id.edtName);
                                EditText edtNation = addfood_layout.findViewById(R.id.edtNation);

                                manager.addFood(new Food(edtName.getText().toString(), edtNation.getText().toString()));
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("취소", null)
                        .show();
        }
    }

}
