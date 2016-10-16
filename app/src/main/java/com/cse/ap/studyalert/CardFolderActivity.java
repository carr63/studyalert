package com.cse.ap.studyalert;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TableRow;
import android.widget.TableLayout;
/**
 * Created by Ayush on 10/16/2016.
 */

public class CardFolderActivity extends AppCompatActivity {
    Button submitCard;
    EditText questionText, answerText;
    String question, answer;
    private static int cardId=0;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_edit_screen);
        context=this;
        submitCard = (Button) findViewById(R.id.cardSubmit);
        submitCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionText = (EditText)findViewById(R.id.questionText);
                answerText = (EditText)findViewById(R.id.answerText);
                question = questionText.getText().toString();
                answer = answerText.getText().toString();
                MainActivity.currentStack.add(question,answer);
                setContentView(R.layout.stack_modify_screen);
                TableLayout stackScreen = (TableLayout)findViewById(R.id.stackEditScreen);
                TableRow newCardRow = new TableRow(getBaseContext());
                TextView newCardName = new TextView(getBaseContext());
                newCardName.setText(question);
                newCardRow.addView(newCardName,0);
                stackScreen.addView(newCardRow,++cardId);
                Intent i = new Intent(context,StackFolderActivity.class);
                context.startActivity(i);
            }
        });
    }
}
