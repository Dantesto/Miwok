package com.example.android.miwok;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class WordAdapter extends ArrayAdapter<Word> {

    private LayoutInflater inflater;
    private int layout;
    private List<Word> wordList;
    private int colorResourceId;

    public WordAdapter(Context context, int resource, List<Word> wordList, int colorResourceId) {
        super(context, resource, wordList);
        this.inflater = LayoutInflater.from(context);
        this.layout = resource;
        this.wordList = wordList;
        this.colorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else
            viewHolder = (ViewHolder) convertView.getTag();
        Word word = wordList.get(position);
        viewHolder.miwokTextView.setText(word.getMiwokTranslation());
        viewHolder.defaultTextView.setText(word.getDefaultTranslation());
        if (word.hasIcon()) {
            viewHolder.iconImageView.setImageResource(word.getIconResourceId());
            //На всякий случай, если до этого ImageView было невидимо
            viewHolder.iconImageView.setVisibility(View.VISIBLE);
        }
        else
            viewHolder.iconImageView.setVisibility(View.GONE);
        viewHolder.textContainer.setBackgroundColor(ContextCompat.getColor(getContext(), colorResourceId));
        /*Для работы ripple effect нужно добавить в ListView параметр android:drawSelectorOnTop="true".
        Если вместо цвета передавать сам ripple effect:
            viewHolder.textContainer.setBackground(ContextCompat.getDrawable(getContext(), colorResourceId));
        и не указывать параметр drawSelectorOnTop, то ripple effect будет начинаться всегда с одного и того же места,
        а не там, где я поставил палец.
        Однако и используемый метод работает не как запланировано. В activity_main.xml ripple effect
        начинается от моего пальца и гоняется за ним, а в ListView ripple effect делает то же, но за пальцем
        не гоняется. Надо найти способ решить эту проблему.
         */
        return convertView;
    }

    private class ViewHolder {
        final TextView miwokTextView, defaultTextView;
        final ImageView iconImageView;
        final LinearLayout textContainer;
        ViewHolder(View view) {
            miwokTextView = (TextView) view.findViewById(R.id.miwok_text_view);
            defaultTextView = (TextView) view.findViewById(R.id.default_text_view);
            iconImageView = (ImageView) view.findViewById(R.id.image);
            textContainer = (LinearLayout) view.findViewById(R.id.text_container);
        }
    }
}