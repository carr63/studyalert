package com.cse.ap.studyalert;

/**
 * Created by Ayush on 10/16/2016.
 */
import android.content.Context;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class StackFolderActivity extends AppCompatActivity {
    Button newCard;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stack_modify_screen);
        context = this;
        newCard = (Button) findViewById(R.id.addCard);
        newCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setContentView(R.layout.question_edit_screen);
                Intent i = new Intent(context,CardFolderActivity.class);
                context.startActivity(i);
            }
        });
    }
}
