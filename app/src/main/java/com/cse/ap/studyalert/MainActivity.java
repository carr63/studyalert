package com.cse.ap.studyalert;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static int stackId = 0;
    private int thisStackId;
    private Context context;
    Button newStack, newCard, submitCard;
    TableLayout table;
    TextView newStackName;
    EditText questionText, answerText;
    String question, answer;
    Stack newCardStack;
    static Stack currentStack;

    //CountdownToDate.updateAllCountdowns(CountdownToDate.countdowns);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //CountdownToDate.updateAllCountdowns(CountdownToDate.countdowns);
        context = this;
        /*setContentView(R.layout.stack_modify_screen);
        newCard = (Button) findViewById(R.id.addCard);
        setContentView(R.layout.question_edit_screen);
        submitCard = (Button) findViewById(R.id.cardSubmit);*/
        setContentView(R.layout.activity_main_screen);
        table = (TableLayout) findViewById(R.id.table);
        newStack = (Button) findViewById(R.id.newStackBtn);
        newStack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //intent i = new Intent();
                newStackOnClick(table);

            }
        });

        /*newCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.question_edit_screen);
                submitCard = (Button) findViewById(R.id.cardSubmit);
            }
        });*/

        /*submitCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionText = (EditText)findViewById(R.id.questionText);
                answerText = (EditText)findViewById(R.id.answerText);
                question = questionText.getText().toString();
                answer = answerText.getText().toString();
                newCardStack.add(question,answer);
                setContentView(R.layout.stack_modify_screen);
                newCard = (Button) findViewById(R.id.addCard);
                TableLayout stackScreen = (TableLayout)findViewById(R.id.stackEditScreen);
                TableRow newCardRow = new TableRow(getBaseContext());
                TextView newCardName = new TextView(getBaseContext());
                newCardName.setText(question);
                newCardRow.addView(newCardName,0);
                stackScreen.addView(newCardRow,2);
            }
        });*/


    }

    public void newStackOnClick(View v) {
        /*newStack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
            }
        });*/
        thisStackId = ++stackId;
        TableLayout layout = (TableLayout) v;
        TableRow newRow = new TableRow(getBaseContext());
        newStackName = new TextView(getBaseContext());
        final Stack newCardStack = new Stack("New Stack " + thisStackId);
        currentStack=newCardStack;
        newStackName.setId(thisStackId);
        newStackName.setTextSize(24);
        newStackName.setText(newCardStack.getName());
        newStackName.setTextColor(Color.BLACK);
        newRow.addView(newStackName,0);
        layout.addView(newRow,thisStackId);
        newStackName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Stack.saveStacks(MainActivity.this,"stackFile",newCardStack.getStacks());
                Intent i = new Intent(context,StackFolderActivity.class);
                context.startActivity(i);
            }

            //newCard = (Button) findViewById(R.id.addStackBtn);
        });
        //newStackName.setTextColor(testText.getCurrentTextColor());
        /*newStackName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.stack_modify_screen);
                newCard = (Button) findViewById(R.id.addCard);newCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //creat
                //e card screen
                        setContentView(R.layout.question_edit_screen);
                        submitCard = (Button) findViewById(R.id.cardSubmit);
                        submitCard.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                questionText = (EditText)findViewById(R.id.questionText);
                                answerText = (EditText)findViewById(R.id.answerText);
                                question = questionText.getText().toString();
                                answer = answerText.getText().toString();
                                newCardStack.add(question,answer);
                                setContentView(R.layout.stack_modify_screen);
                                TableLayout stackScreen = (TableLayout)findViewById(R.id.stackEditScreen);
                                TableRow newCardRow = new TableRow(getBaseContext());
                                TextView newCardName = new TextView(getBaseContext());
                                newCardName.setText(question);
                                newCardRow.addView(newCardName,0);
                                stackScreen.addView(newCardRow);
                            }
                        });
                        //create new card through Stack.add
                    }
                });
                //setContentView(R.layout."stack editor screen")
            }
        });
        //newRow.addView(newStackName, 0);
        //layout.addView(newRow,thisStackId);*/
    }
    //private void addCard(Stack stack)
}
