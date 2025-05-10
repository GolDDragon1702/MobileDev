package example.fragment;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        if (savedInstanceState == null) {
            // Initial setup - load appropriate fragment based on current orientation
            updateFragmentForOrientation(getResources().getConfiguration().orientation);
        }
    }
    
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Handle orientation changes
        updateFragmentForOrientation(newConfig.orientation);
    }
    
    private void updateFragmentForOrientation(int orientation) {
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Landscape orientation
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new LandscapeFragment())
                .commit();
        } else {
            // Portrait orientation
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new PortraitFragment())
                .commit();
        }
    }
} 