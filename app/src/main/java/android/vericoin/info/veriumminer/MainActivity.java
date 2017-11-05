package android.vericoin.info.veriumminer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String PREF_POOL = "pool";

    private int mPoolId;
    private String mPoolName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences poolPrefs = getSharedPreferences(PREF_POOL, 0);
        mPoolId = poolPrefs.getInt(PoolCredentialsActivity.POOL_ID_KEY, -1);
        mPoolName = poolPrefs.getString(PoolCredentialsActivity.POOL_NAME_KEY, "");

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startPoolsActivity();
            }
        });

        TextView noPoolText = findViewById(R.id.no_pool);
        Button selectPoolBtn = findViewById(R.id.btn_select_pool);
        selectPoolBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPoolsActivity();
            }
        });
        ConstraintLayout poolContainer = findViewById(R.id.pool_container);

        if (mPoolId >= 0) {
            poolContainer.setVisibility(View.GONE);
            noPoolText.setVisibility(View.VISIBLE);
            selectPoolBtn.setVisibility(View.VISIBLE);
        }
        else {
            poolContainer.setVisibility(View.VISIBLE);
            noPoolText.setVisibility(View.GONE);
            selectPoolBtn.setVisibility(View.GONE);
        }
    }

    private void startPoolsActivity(){
        Intent intent = new Intent(MainActivity.this, PoolsActivity.class);
        startActivity(intent);
    }
}