package app.com.VatsalyaCoachingAcademy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    private ImageView workingimg;
    private TextView  workingtxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        workingimg = (ImageView)findViewById(R.id.ivWorking);
        workingtxt = (TextView)findViewById(R.id.tvworking);
    }
}
