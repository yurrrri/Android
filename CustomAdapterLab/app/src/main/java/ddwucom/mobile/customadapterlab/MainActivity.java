package ddwucom.mobile.customadapterlab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DataManager dataManager;
    private ArrayList<townData> townDataList;
    private townAdapter townAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataManager = new DataManager();
        townDataList = dataManager.getSubjectList();

        townAdapter = new townAdapter(this, R.layout.custom_adapter_view, townDataList);
        listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(townAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dataManager.removeData(position);
                townAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}
