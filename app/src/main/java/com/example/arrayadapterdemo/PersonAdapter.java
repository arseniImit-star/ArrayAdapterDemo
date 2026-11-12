
package com.example.arrayadapterdemo;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PersonAdapter extends ArrayAdapter<Person> {

    private Person selectedPerson;

    public PersonAdapter(Activity context, List<Person> persons) {
        super(context, 0, persons);
    }

    public void setSelectedPerson(Person person) {
        selectedPerson = person;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_person, parent, false);
        }

        Person person = getItem(position);

        TextView nameText = convertView.findViewById(
                R.id.personName
        );

        if (person != null) {
            nameText.setText(person.getFullName());

            if (person == selectedPerson) {
                nameText.setBackgroundColor(
                        Color.parseColor("#90CAF9")
                );
            } else {
                nameText.setBackgroundColor(Color.WHITE);
            }
        }

        return convertView;
    }
}
