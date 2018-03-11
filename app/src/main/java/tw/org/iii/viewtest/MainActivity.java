package tw.org.iii.viewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private MyView2 myView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myView2 = findViewById(R.id.myview);
    }

    public void clear(View view) {
        myView2.clear();
    }

    public void undo(View view) {
        myView2.undo();
    }

    public void redo(View view) {
        myView2.redo();
    }
}