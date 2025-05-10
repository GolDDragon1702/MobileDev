package example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import example.fragment.data.DrinkData;
import example.fragment.model.Drink;

public class DrinkDetailFragment extends Fragment {
    
    private int drinkId = -1;
    
    private static final String ARG_DRINK_ID = "drink_id";
    
    public DrinkDetailFragment() {
        // Required empty constructor
    }
    
    public static DrinkDetailFragment newInstance(int drinkId) {
        DrinkDetailFragment fragment = new DrinkDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_DRINK_ID, drinkId);
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            drinkId = getArguments().getInt(ARG_DRINK_ID);
        }
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_drink_detail, container, false);
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        if (drinkId != -1) {
            updateDrinkDetail(drinkId);
        }
    }
    
    public void updateDrinkDetail(int drinkId) {
        this.drinkId = drinkId;
        
        Drink drink = DrinkData.getDrinkById(drinkId);
        if (drink == null) {
            return;
        }
        
        View view = getView();
        if (view != null) {
            ImageView imageView = view.findViewById(R.id.detailImageView);
            TextView nameTextView = view.findViewById(R.id.detailNameTextView);
            TextView priceTextView = view.findViewById(R.id.priceDetailTextView);
            TextView originTextView = view.findViewById(R.id.originTextView);
            TextView manufacturerTextView = view.findViewById(R.id.manufacturerTextView);
            TextView volumeTextView = view.findViewById(R.id.volumeTextView);
            TextView countryTextView = view.findViewById(R.id.countryTextView);
            
            imageView.setImageResource(drink.getImageResId());
            nameTextView.setText(drink.getName());
            priceTextView.setText("Giá: " + drink.getPrice() + " đồng/thùng");
            originTextView.setText("Nguồn gốc: " + drink.getOrigin());
            manufacturerTextView.setText("Hãng sản xuất: " + drink.getManufacturer());
            volumeTextView.setText("Dung tích(ml)/lon: " + drink.getVolume());
            countryTextView.setText("Xuất xứ: " + drink.getCountry());
        }
    }
} 