package pte.ttk.hu.tudaskozpont2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText bookId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookId = (EditText) findViewById(R.id.searchEditText);


    }

    public void clickedButton(View v) {
        Intent intent = new Intent(this, SearchActivity.class);
        if (bookId.getText() != null) {
            intent.putExtra("id", bookId.getText().toString());
            System.out.println(bookId.getText().toString());
        }
        startActivity(intent);

    }
}
