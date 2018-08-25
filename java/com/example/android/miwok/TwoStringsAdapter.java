package com.example.android.miwok;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class TwoStringsAdapter extends ArrayAdapter<TwoStrings> {

    private LayoutInflater inflater;
    private int layout;
    private List<TwoStrings> twoStringsList;

    public TwoStringsAdapter(Context context, int resource, List<TwoStrings> twoStringsList) {
        super(context, resource, twoStringsList);
        this.inflater = LayoutInflater.from(context);
        this.layout = resource;
        this.twoStringsList = twoStringsList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        /*Это условие позволяет использовать повторно один и тот же View (convertView), если он уже был создан.
        Также позволяет вычислить id элементов convertView единожды. Это необходимо, т.к. получение id - затратная операция
         */
        if (convertView == null) {
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else
            viewHolder = (ViewHolder) convertView.getTag();
        TwoStrings twoStrings = twoStringsList.get(position);
        viewHolder.string1.setText(twoStrings.getString1());
        viewHolder.string2.setText(twoStrings.getString2());
        return convertView;
    }

    private class ViewHolder {
        final TextView string1, string2;
        ViewHolder(View view) {
            string1 = (TextView) view.findViewById(R.id.string1);
            string2 = (TextView) view.findViewById(R.id.string2);
        }
    }
}