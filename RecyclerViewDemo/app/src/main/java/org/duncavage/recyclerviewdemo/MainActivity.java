package org.duncavage.recyclerviewdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import org.duncavage.recyclerviewdemo.fragments.ListPagerFragment;

/**
 * Created by brett on 5/21/15.
 */
public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageButton toolbarButtonRight;

    public void setToolbarRightButtonClickListener(View.OnClickListener listener) {
        if (toolbarButtonRight == null) {
            toolbarButtonRight = (ImageButton)toolbar.findViewById(R.id.button_right);
        }
        toolbarButtonRight.setOnClickListener(listener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(getResources().getColor(R.color.dark_text));
    }
}
