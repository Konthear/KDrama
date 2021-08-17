package sg.edu.rp.c346.id20003116.kdrama;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<KDrama> detailList;

    public CustomAdapter(Context context, int resource, ArrayList<KDrama> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        detailList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(layout_id, parent, false);

        TextView tvName = rowView.findViewById(R.id.textViewName);
        TextView tvDesc = rowView.findViewById(R.id.textViewDesc);
        TextView tvCast = rowView.findViewById(R.id.textViewCast);
        TextView tvYear = rowView.findViewById(R.id.textViewYear);
        ImageView ivNew = rowView.findViewById(R.id.imageView);
        RatingBar ratingBar = (RatingBar) rowView.findViewById(R.id.ratingBarStars);

        KDrama currentDrama = detailList.get(position);

        tvName.setText(currentDrama.getName());
        tvDesc.setText(currentDrama.getDesc());
        tvCast.setText(currentDrama.getCast());
        tvYear.setText(Integer.toString(currentDrama.getYearReleased()));

        String stars = "";
        for (int i = 0; i < currentDrama.getStars(); i++) {
            stars += " * ";
        }

        ratingBar.setRating(currentDrama.getStars());

        if (currentDrama.getYearReleased() >= 2019) {
            ivNew.setVisibility(View.VISIBLE);
            ivNew.setImageResource(R.drawable.newsong);
        } else {
            ivNew.setVisibility(View.INVISIBLE);
        }

        return rowView;
    }
}
