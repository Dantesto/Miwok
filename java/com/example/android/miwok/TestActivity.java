package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

//Тестовый код. Показывает почти все возможности работы с ListView.
//Вместо TextView в test_list_item.xml можно использовать что угодно, но для данного примера без заморочек удобнее TextView.
//Не исользованы некоторые переопределения, атрибуты xml (такие как подстветка нажатия на элемент ListView, multipleChoice (требующий дополнительных действий в коде)) и может еще что-то

/*Для маленького облегчения работы с ListView, можно наследоваться от ListActivity (понадобится еще указать значение
android:id="@android:id/list")
 */
public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        fillListView();
    }

    private void fillListView() {
        //Массив строк можно задать в strings.xml
        //Также string-array может быть сразу указан в разметке test_activity.xml как android:entries
        String[] testArray = getResources().getStringArray(R.array.test);
        //А можно и в ArrayList
        ArrayList<String> test = new ArrayList<String>(Arrays.asList(testArray));
        //Задание собственного типа разметки для каждого элемента ListView. В данном случае используется test_list_item.xml
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, R.layout.test_list_item, test);
        ListView listView = (ListView) findViewById(R.id.test_list);
        //Переопределение эффекта от короткого нажатия на элемент ListView.
        /*Пишут, что иногда, если сначала установить адаптер, а потом переопределить следующие методы, переопределение не сработает.
        У меня не работает, если использую Button как разметку элемента ListView, не спасают даже атрибуты focusable и
        focusableInTouchMode равные false. Хотя с CheckBox помогать должно.
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                Toast.makeText(getApplicationContext(), ((TextView) itemClicked).getText(), Toast.LENGTH_SHORT).show();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                Toast.makeText(getApplicationContext(), "ASTANAVIS'!!!", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        /*Программно можно задать цвет и толщину разделителя элементов (пишут, что порядок задания важен):
            ColorDrawable divcolor = new ColorDrawable(Color.DKGRAY);
            listView.setDivider(divcolor);
            listView.setDividerHeight(10);
        В данном примере я задал данные параметры в activity_test.xml

        В данном примере я программно установил дополнительные нижний и верхний разделители.
        В xml файле это делается аттрибутами:
            android:footerDividersEnabled="true"
            android:headerDividersEnabled="true"
         */
        listView.setHeaderDividersEnabled(true);
        listView.setFooterDividersEnabled(true);
        listView.setAdapter(itemsAdapter);
        //Динамически (по ходу выполнения программы) добавляем элементы в список.
        test.add("dynamicAdditionUsingArrayList");
        itemsAdapter.notifyDataSetChanged();
        /*Здесь добавил непосредственно в адаптер, но не уведомил его об изменении данных. Тем не менее все работает.
        Работает, даже если закомментировать строку сверху.
         */
        itemsAdapter.add("dynamicAdditionUsingAdapterList");
    }
}