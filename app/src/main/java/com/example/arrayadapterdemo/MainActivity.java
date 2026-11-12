
package com.example.arrayadapterdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends Activity {

    private List<Person> persons;
    private PersonAdapter adapter;

    private String[] firstNames;
    private String[] lastNames;

    private final Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView personList = findViewById(R.id.personList);
        Button addButton = findViewById(R.id.addButton);

        firstNames = getResources().getStringArray(
                R.array.names
        );

        lastNames = getResources().getStringArray(
                R.array.last_names
        );

        // Создаём коллекцию пользователей
        persons = new ArrayList<>();

        // Генерируем начальный список из 20 человек
        for (int i = 0; i < 20; i++) {
            persons.add(generatePerson());
        }

        // Создаём и назначаем адаптер
        adapter = new PersonAdapter(this, persons);
        personList.setAdapter(adapter);

        // Обработка выбора пользователя
        personList.setOnItemClickListener(
                (parent, view, position, id) -> {
                    Person selected = persons.get(position);
                    adapter.setSelectedPerson(selected);
                }
        );

        // Добавление нового случайного пользователя
        addButton.setOnClickListener(v -> {
            persons.add(generatePerson());
            adapter.notifyDataSetChanged();
        });
    }

    // Генерация случайного пользователя
    private Person generatePerson() {

        String firstName = firstNames[
                random.nextInt(firstNames.length)
                ];

        String lastName = lastNames[
                random.nextInt(lastNames.length)
                ];

        return new Person(firstName, lastName);
    }
}
