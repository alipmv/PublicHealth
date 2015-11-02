package ir.saymantech.alipmv.listingtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
//import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class EquipmentViewerActivity extends AppCompatActivity {
    TextView mainTextView;
    ImageView mImageView;
    Equipment[] mEquipment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_viewer);

        mEquipment=new Equipment[]{
                new Equipment(R.string.barfix_description,R.drawable.barfix,R.string.barfix_qrcode),
                new Equipment(R.string.halter_description,R.drawable.halter,R.string.halter_qrcode)
        };

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace your own action here", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mainTextView=(TextView)findViewById(R.id.scan_result_text_view);
        mImageView=(ImageView) findViewById(R.id.equipment_image);
        new IntentIntegrator(EquipmentViewerActivity.this).initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Intent returnIntent=new Intent().putExtra("operation_result","000");
                setResult(RESULT_OK,returnIntent);
                finish();
            } else {
                //Intent startEquipmentViewerIntent = new Intent(this,EquipmentViewerActivity.class);
                //startEquipmentViewerIntent.putExtra("scan_result",result.getContents());
                //String scan_result = getIntent().getExtras().getString("scan_result");
                int i=0;
                while((mEquipment.length > i) && !(result.getContents().equals(getResources().getString(mEquipment[i].getQRcode())))){i++;}
                if(i<mEquipment.length){
                    mainTextView.setText(mEquipment[i].getDescription());
                    mImageView.setImageResource(mEquipment[i].getPicture());
                }else {
                    Intent returnIntent=new Intent().putExtra("operation_result","404");
                    setResult(RESULT_OK,returnIntent);
                    finish();
                }
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
