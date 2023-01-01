package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button trueButton;
    Button falseButton;
    TextView answerTextview;
    ImageButton nextButton;
    ImageButton prevButton;
    Button resultButton;
    int currentQuestionIndex;
     question[] questionBank=new question[]{
             new question(R.string.question_amendment,false),
             new question(R.string.question_constitution,true),
             new question(R.string.question_declaration,true),
             new question(R.string.question_independence_rights,true),

             new question(R.string.question_government,false),

             new question(R.string.question_government_feds,false),

             new question(R.string.question_government_senators,true),



     };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

//        question question = new question(R.string.my_test_questions,true);
        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        prevButton = findViewById(R.id.prev_button);
        answerTextview = findViewById(R.id.answer_text_view);
        resultButton=findViewById(R.id.result_button);
        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);
        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.true_button:
               checkAnswer(true);
                break;
                case R.id.false_button:
                   checkAnswer(false );
                    break;
            case R.id.next_button:
                //go to next question
                currentQuestionIndex=(currentQuestionIndex+1) % questionBank.length;
//                Log.d("Current","onClick:"+currentQuestionIndex);
//                answerTextview.setText(questionBank[currentQuestionIndex].getAnswerResId());
                updateQuestion();
                break;
            case  R.id.prev_button:
                if(currentQuestionIndex>0) {
                    currentQuestionIndex = (currentQuestionIndex - 1) % questionBank.length;
//                Log.d("Current","onClick:"+currentQuestionIndex);
//                answerTextview.setText(questionBank[currentQuestionIndex].getAnswerResId());
                    updateQuestion();
                }

            }
        }
        private void updateQuestion(){
            Log.d("Current","onClick:"+currentQuestionIndex);
            answerTextview.setText(questionBank[currentQuestionIndex].getAnswerResId());
        }
         private void checkAnswer(boolean userChooseCorrect){
        boolean answerIsTrue=questionBank[currentQuestionIndex].isAnswerTrue();
        int toastMessageId;
//       int count=0;
//       int i;
        if (userChooseCorrect==answerIsTrue){
            toastMessageId=R.string.correct_answer;
        }
        else{
            toastMessageId=R.string.wrong_answer;

        }
             Toast.makeText(MainActivity.this, toastMessageId, Toast.LENGTH_SHORT).show();

         }



    }
