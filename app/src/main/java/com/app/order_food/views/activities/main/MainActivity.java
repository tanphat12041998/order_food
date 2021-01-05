package com.app.order_food.views.activities.main;



import com.app.order_food.R;
import com.app.order_food.components.recyclerview.decorator.CustomDecorator;
import com.app.order_food.views.activities.BaseActivity;
import com.app.order_food.views.fragments.AccountFragment;
import com.app.order_food.views.fragments.CartFragment;
import com.app.order_food.views.fragments.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseActivity {


    @Override
    protected void initialViewComponent() {
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }
    @SuppressLint("NonConstantResourceId")
    private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
            menuItem -> {
                Fragment selectedFragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.nav_cart:
                        selectedFragment = new CartFragment();
                        break;
                    case R.id.nav_user:
                        selectedFragment = new AccountFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                return true;
            };

    @Override
    protected void initialVariables() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }


    Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.logo2);
    Bitmap circularBitmap = CustomDecorator.getRoundedCornerBitmap(bitmap, 100);

    ImageView circularImageView = (ImageView) findViewById(R.id.imgAvatarUser);
        circularImageView.();
}