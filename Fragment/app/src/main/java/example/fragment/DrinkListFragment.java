package example.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import example.fragment.data.DrinkData;
import example.fragment.model.Drink;

public class DrinkListFragment extends Fragment {
    
    public interface OnDrinkSelectedListener {
        void onDrinkSelected(int drinkId);
    }
    
    private OnDrinkSelectedListener listener;
    
    public DrinkListFragment() {
        // Required empty constructor
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_drink_list, container, false);
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        ListView listView = view.findViewById(R.id.drinkListView);
        
        DrinkAdapter adapter = new DrinkAdapter(requireContext(), DrinkData.drinks);
        listView.setAdapter(adapter);
        
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Drink drink = DrinkData.drinks.get(position);
                if (listener != null) {
                    listener.onDrinkSelected(drink.getId());
                }
            }
        });
    }
    
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnDrinkSelectedListener) {
            listener = (OnDrinkSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnDrinkSelectedListener");
        }
    }
    
    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
    
    private static class DrinkAdapter extends ArrayAdapter<Drink> {
        
        public DrinkAdapter(Context context, List<Drink> drinks) {
            super(context, 0, drinks);
        }
        
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.drink_list_item, parent, false);
            }
            
            Drink drink = getItem(position);
            if (drink != null) {
                ImageView imageView = view.findViewById(R.id.imageView);
                TextView nameTextView = view.findViewById(R.id.nameTextView);
                TextView priceTextView = view.findViewById(R.id.priceTextView);
                
                imageView.setImageResource(drink.getImageResId());
                nameTextView.setText(drink.getName());
                priceTextView.setText("Giá: " + drink.getPrice() + " đồng/thùng");
            }
            
            return view;
        }
    }
} 