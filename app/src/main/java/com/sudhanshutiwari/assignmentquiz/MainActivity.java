package com.sudhanshutiwari.assignmentquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.app.Dialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sudhanshutiwari.assignmentquiz.models.QuestionModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView selectedTopicTitle, quizQuestionNumView, quizQuestionView;
    ImageView quizImage;
    Button nextButton , quizOptionOneBtn, quizOptionTwoBtn, quizOptionThreeBtn, quizOptionFourBtn;
    LinearLayout optionContainer;


    List<QuestionModel> questionModelList = new ArrayList<>()  ;

    private int count  = 0;
    private int position = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // textViews
        selectedTopicTitle = findViewById(R.id.selectedTopicTitle);
        quizQuestionNumView = findViewById(R.id.quizQuestionNum);
        quizQuestionView = findViewById(R.id.quizQuestion);
        optionContainer= findViewById(R.id.optionSection);

        // image
        quizImage = findViewById(R.id.questionImage);

        // buttons
        nextButton = findViewById(R.id.nextButton);
        quizOptionOneBtn = findViewById(R.id.option_one);
        quizOptionTwoBtn = findViewById(R.id.option_two);
        quizOptionThreeBtn = findViewById(R.id.option_three);
        quizOptionFourBtn = findViewById(R.id.option_four);

        // adding quiz question
        getQuestions();



        // if user click on any option
        for(int i = 0; i < 4; i++){
            optionContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer((Button) v);
                }
            });
        }

        playAnim(quizQuestionView,0,questionModelList.get(position).getQuestion());
        System.out.println(questionModelList.get(position).getQuestion());

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextButton.setEnabled(false);
                nextButton.setAlpha(0.7f);
                enableOption(true);
                position++;
                if(position == questionModelList.size()){
                    resultActivity();

                } else {
                    count = 0;
                    playAnim(quizQuestionView,0,questionModelList.get(position).getQuestion());
                }
            }
        });

    }

    private void getQuestions() {
        // creating quiz questions
        final QuestionModel questionModel1 = new QuestionModel("Number of primitive data types in Java are ?","1", "8" ,"9", "6", "8");
        final QuestionModel questionModel2 = new QuestionModel("What is the size of float and double in java ?","32 and 64", "32 and 32" ,"64 and 64", "64 and 32", "32 and 64");
        final QuestionModel questionModel3 = new QuestionModel("Automatic type conversion is possible in which of the possible cases ?","Byte to int", "Int to long" ,"Long to int", "Short to int", "Int to long");
        final QuestionModel questionModel4 = new QuestionModel("Select the valid statement.","char[] ch = new char()", "char[] ch = new char[5]" ,"char[] ch = new char(5)", "char[] ch = new char[]", "char[] ch = new char[5]");
        final QuestionModel questionModel5 = new QuestionModel("Array in Java are","Object reference", "object" ,"Primitive data type", "None", "object");
        final QuestionModel questionModel6 = new QuestionModel("Which operator from the following can be used to illustrate the feature of polymorphism?","Overloading <<", "Overloading &&" ,"Overloading | |", "Overloading +=", "Overloading <<");
        final QuestionModel questionModel7 = new QuestionModel("Which function best describe the concept of polymorphism in programming languages?","Class member function", "Virtual function" ,"inline function", "underline function", "Virtual function");
        final QuestionModel questionModel8 = new QuestionModel(" Which of the following feature is also known as run-time binding or late binding?","Dynamic typing", "Dynamic loading" ,"Dynamic binding", "data hiding", "Dynamic binding");
        final QuestionModel questionModel9 = new QuestionModel("Using the concept of encapsulation security of the data is ____","Ensured to some extent", "Purely ensured" ,"Not ensured", "very low", "Ensured to some extent");
        final QuestionModel questionModel10 = new QuestionModel("The object cannot be_____?","passed by copy", "passed as reference" ,"passed by value", "passed by function", "passed by function");

        // adding quiz question
        questionModelList.add(questionModel1);
        questionModelList.add(questionModel2);
        questionModelList.add(questionModel3);
        questionModelList.add(questionModel4);
        questionModelList.add(questionModel5);
        questionModelList.add(questionModel6);
        questionModelList.add(questionModel7);
        questionModelList.add(questionModel8);
        questionModelList.add(questionModel9);
        questionModelList.add(questionModel10);
    }



    private void resultActivity() {
        Dialog resultView = new Dialog(MainActivity.this);
        resultView.setContentView(R.layout.resultview);
        resultView.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        resultView.setCanceledOnTouchOutside(false);
        resultView.show();
        Button button = resultView.findViewById(R.id.okButton);
        TextView quizResult = resultView.findViewById(R.id.quizResult);
        quizResult.setText(score + "/" + questionModelList.size());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultView.dismiss();
                count = 0;
                score = 0;
                position = 0;
                playAnim(quizQuestionView,0,questionModelList.get(position).getQuestion());
                

            }
        });


        
    }

    private void playAnim(View view , int value, final String data){
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if(value == 0 && count < 4){
                    String option = "";
                    if(count == 0){
                        option = questionModelList.get(position).getOptionA();
                    }
                    else if(count == 1){
                        option = questionModelList.get(position).getOptionB();
                    }
                    else if(count == 2){
                        option = questionModelList.get(position).getOptionC();
                    }
                    else if(count == 3){
                        option = questionModelList.get(position).getOptionD();
                    }
                    playAnim(optionContainer.getChildAt(count),0,option);
                    count++;
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {

                if(value == 0){
                    try {
                        ((TextView)view).setText(data);
                        quizQuestionNumView.setText(position + 1 + "/" + questionModelList.size());

                    }
                    catch (ClassCastException e){
                        ((Button)view).setText(data);
                    }
                    view.setTag(data);
                    playAnim(view, 1,data);
                }


            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void checkAnswer(Button selectedOption){
        enableOption(false);
        nextButton.setEnabled(true);
        nextButton.setAlpha(1);
        if(selectedOption.getText().toString().equals(questionModelList.get(position).getCorrectAns())){
            // correct
            selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF72F118")));
            score += 1;

        }
        else {
            // incorrect
            selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFE83003")));
            Button correctOption = (Button) optionContainer.findViewWithTag(questionModelList.get(position).getCorrectAns());
            correctOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF72F118")));

        }
    }

    private void enableOption(boolean enable){
        for(int i = 0; i < 4; i++){
            optionContainer.getChildAt(i).setEnabled(enable);
            if(enable){
                optionContainer.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
                optionContainer.getChildAt(i).setBackgroundResource(R.drawable.optionsbg);
            }
        }
    }

    
}