package listview.players;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CountryAdapter extends ArrayAdapter<Country> {
    private Context context;
    private List<Country> countries;

    public CountryAdapter(Context context, List<Country> countries) {
        super(context, 0, countries);
        this.context = context;
        this.countries = countries;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Country country = countries.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.country_item, parent, false);
        }

        ImageView imageFlag = convertView.findViewById(R.id.imageFlag);
        TextView textName = convertView.findViewById(R.id.textName);
        TextView rate = convertView.findViewById(R.id.rate);

        imageFlag.setImageResource(country.getFlagResId());
        textName.setText(country.getName());
        rate.setText(country.getRate());

        return convertView;
    }
}
