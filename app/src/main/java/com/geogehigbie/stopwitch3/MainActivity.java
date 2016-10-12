package com.geogehigbie.stopwitch3;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int seconds = 0;
    private int hundreth = 0;
    private boolean isRunning = false;
    private boolean isSaved;
    private boolean isClickedStart = false;
    private boolean wasRunning;

    //private TextView timeView = (TextView) findViewById(R.id.time_view);

//    int hours;
//    int minutes;
//    int secs;
//    int decas;


    String timeString;

//    private int hundreths;
//    private int deca;
//    int sec;
//    private int


    private ArrayList<String> savedStoppedTimeList = new ArrayList<String>();
    private ArrayList<TextView> savedTimeArrayList = new ArrayList<TextView>();

    private Calendar cal = Calendar.getInstance();
    private int second = cal.get(Calendar.SECOND);

    private int clickCounter1 = 0;
    private int clickCounter2 = 0;
    private int clickCounter3 = 0;
    private int clickCounter4 = 0;

    private String tapInstructions = "Tap2Save";


    private String savedTimeToSend1;
    private String savedTimeToSend2;
    private String savedTimeToSend3;
    private String savedTimeToSend4;


    private long millisStart;

    private int millis = 0;
    private int hundreths;
    private int decas;
    private int secs;
    private int mins;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if(savedInstanceState != null){
//            seconds = savedInstanceState.getInt("seconds");
//            secs = savedInstanceState.getInt("secs");
//            minutes = savedInstanceState.getInt("minutes");
//            hours = savedInstanceState.getInt("hours");
//            isRunning = savedInstanceState.getBoolean("isRunning");
//            timeString = savedInstanceState.getString("timeString");
//            isClickedStart = savedInstanceState.getBoolean("isClickedStart");
//            wasRunning = savedInstanceState.getBoolean("wasRunning");
//        }
        if(isRunning ){
            runTimer();
        }
        //flashTimer();
    }

//    @Override
//    protected void onSaveInstanceState(Bundle savedInstanceState) {
//        savedInstanceState.putInt("seconds", seconds);
//        savedInstanceState.putInt("secs", secs);
//        savedInstanceState.putInt("minutes", minutes);
//        savedInstanceState.putInt("hours", hours);
//        savedInstanceState.putBoolean("isRunning", isRunning);
//        savedInstanceState.putString("timeString", timeString);
//        savedInstanceState.putBoolean("isClickedStart", isClickedStart);
//        savedInstanceState.putBoolean("wasRunning", wasRunning);
//
//    }


//    @Override
//    protected void onPause() {
//        wasRunning = isRunning;
//    }

//   @Override
//   protected void onResume(){
//       if(wasRunning){
//           isRunning = true;
//       }
//   }



    public void onClickStart(View view){
        //millisStart = System.currentTimeMillis();
        playSoundEffects();
        isRunning = true;
        if(isClickedStart == false){
            runTimer();
        }
        isClickedStart = true;
    }

    public void onClickStop(View view){
        playSoundEffects();

        isRunning = false;

        isClickedStart = false;

        savedStoppedTimeList.add(timeString);

    }

    public void onClickReset(View view){
        playSoundEffects();
        isRunning = false;
        seconds = 0;

        clearTime();
        isClickedStart = false;

    }

    public void playSoundEffects(){

        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.click_on_sound);
        mediaPlayer.start();

    }

    public void playWitchLaugh(){

        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.witchlaugh);
        mediaPlayer.start();
    }




    private void runTimer() {
        final TextView timeView = (TextView) findViewById(R.id.time_view);
        timeView.setVisibility(View.VISIBLE);

        if (isRunning) {
            millis++;


            Handler handler2 = new Handler();
            Runnable runnable2 = new Runnable() {
                @Override
                public void run() {

                    //System.out.println("Timer: " + seconds);

                   // long millis = System.currentTimeMillis() - millisStart;


                    secs = millis % 1000;
                    hundreth = millis/1000;
                    decas = (millis / 100)%10;
                    mins = millis / (1000*60);



                    //long hundreths = nano/ 10000000;

//                    long start = System.nanoTime();
//                    long end = System.nanoTime();
//
//                    long elapsedTime = end - start;
//                    long elapsedTime = end - start;
//                    double seconds = (double)elapsedTime / 1000000000.0;
//
//                    hours = seconds/6000;
//                    minutes = seconds/100;
//                    secs = seconds%100;

                   // timeString = String.valueOf(millis);



                    timeString = String.format("%02d:%02d:%02d", mins, secs, hundreth);
                    timeView.setText(timeString);

                    runTimer();

                }

            };
            handler2.postDelayed(runnable2, 1);
        }
    }


    public void clearTime(){
        secs = 0;
        hundreth = 0;
        decas = 0;
        mins = 0;

        TextView timeView = (TextView) findViewById(R.id.time_view);

        timeString = String.format("%02d:%02d:%02d", mins, secs, decas);
        timeView.setText(timeString);

    }






    public void onClickSave1(View view){


        clickCounter1++;
        TextView savedTime1 = (TextView) findViewById(R.id.saved1);
        ImageView witchIcon1 = (ImageView) findViewById(R.id.witchIcon1);


        if(clickCounter1 % 2 == 0){
            multipleAnimation(witchIcon1, clickCounter1, savedTime1);
            savedTimeToSend1 = savedTime1.getText().toString();

        }

        else {
            mainTimeAnnimation();


            TextView timeView = (TextView) findViewById(R.id.time_view); //This is the source of the time.
            String lastSavedTime = timeView.getText().toString();

            savedTime1.setText(lastSavedTime);
        }



    }

    public void onClickSave2(View view){

        clickCounter2++;
        TextView savedTime2 = (TextView) findViewById(R.id.saved2);
        ImageView witchIcon2 = (ImageView) findViewById(R.id.witchIcon2);


        if(clickCounter2 % 2 == 0){
            multipleAnimation(witchIcon2, clickCounter2, savedTime2);
            savedTimeToSend2 = savedTime2.getText().toString();


        }
        else {
            mainTimeAnnimation();


            TextView timeView = (TextView) findViewById(R.id.time_view); //This is the source of the time.
            String lastSavedTime = timeView.getText().toString();

            savedTime2.setText(lastSavedTime);
        }

    }

    public void onClickSave3(View view){

        clickCounter3++;
        TextView savedTime3 = (TextView) findViewById(R.id.saved3);
        ImageView witchIcon3 = (ImageView) findViewById(R.id.witchIcon3);



        if(clickCounter3 % 2 == 0) {
            multipleAnimation(witchIcon3, clickCounter3, savedTime3);
            savedTimeToSend3 = savedTime3.getText().toString();


        }
        else{

            mainTimeAnnimation();


            TextView timeView = (TextView) findViewById(R.id.time_view); //This is the source of the time.
            String lastSavedTime = timeView.getText().toString();

            savedTime3.setText(lastSavedTime);

        }




    }

    public void onClickSave4(View view){

        clickCounter4++;
        TextView savedTime4 = (TextView) findViewById(R.id.saved4);
        ImageView witchIcon4 = (ImageView) findViewById(R.id.witchIcon4);


        if(clickCounter4 % 2 == 0) {
            multipleAnimation(witchIcon4, clickCounter4, savedTime4);
            savedTimeToSend4 = savedTime4.getText().toString();
            //sendEmail(savedTimeToSend4);

        }
        else{
            mainTimeAnnimation();
            //animatSmallTime(savedTime4, witchIcon4);

            TextView timeView = (TextView) findViewById(R.id.time_view); //This is the source of the time.
            String lastSavedTime = timeView.getText().toString();

            savedTime4.setText(lastSavedTime);
        }


    }


    public void sendEmail(){
        String savedTime = "00.00.00";

        if(clickCounter1 == 2){savedTime = savedTimeToSend1; clickCounter1 = 0;}
        if(clickCounter2 == 2){savedTime = savedTimeToSend2; clickCounter1 = 0;}
        if(clickCounter3 == 2){savedTime = savedTimeToSend3; clickCounter1 = 0;}
        if(clickCounter4 == 2){savedTime = savedTimeToSend4; clickCounter1 = 0;}


        String emailMessage = "The Stop Witch says your time was " + savedTime;
        String emailSubject = "Your Stop Witch time";

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("*/*");
        emailIntent.putExtra(Intent.EXTRA_TEXT, emailMessage);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);


        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }


    }


    public void soundAndAnimation(int clickCounter, TextView savedTime, ImageView imageView){
        final String intructionsFill = "Tap2Save";

        if(clickCounter %2 == 0){
            playWitchLaugh();

            randomAnimation(imageView, savedTime);

            savedTime.setText(intructionsFill);
        }
        else{
            playSoundEffects();
        }
    }

    public void mainTimeAnnimation() {

        final TextView timeView = (TextView) findViewById(R.id.time_view);

        Animation scaleAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.reverse_scale);
        scaleAnimation.setFillAfter(false);
        timeView.startAnimation(scaleAnimation);

        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Animation reverseGrow = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.grow_back);
                timeView.startAnimation(reverseGrow);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }


    public void multipleAnimation(final ImageView imageView, final int clickCounter, final TextView savedTime){

        if(clickCounter %2 == 0) {
            Animation alphaAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha_animation);
            imageView.startAnimation(alphaAnimation);

            savedTime.startAnimation(alphaAnimation);
            savedTime.setVisibility(View.INVISIBLE);

            alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    soundAndAnimation(clickCounter, savedTime, imageView);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
    }




    public void makeWitchGrow(final ImageView imageView, final TextView textView){

        ImageView bigWitch4 = (ImageView) findViewById(R.id.witchIconLarge1);
        bigWitch4.setVisibility(View.VISIBLE);
        bigWitch4.clearAnimation();

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        bigWitch4.setLayoutParams(layoutParams);

        Animation scaleAnimation = AnimationUtils.loadAnimation(
                getApplicationContext(), R.anim.scale_animation);
        scaleAnimation.setFillAfter(false);
        bigWitch4.startAnimation(scaleAnimation);

        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                imageView.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.INVISIBLE);

                sendEmail();

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ImageView bigWitch4 = (ImageView) findViewById(R.id.witchIconLarge1);
                Animation alphaAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alph_animation_long);
                bigWitch4.startAnimation(alphaAnimation);


                alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        ImageView bigWitch4 = (ImageView) findViewById(R.id.witchIconLarge1);
                        bigWitch4.setVisibility(View.INVISIBLE);

                        Animation reverseAlpha = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.reverse_alpha);
                        imageView.startAnimation(reverseAlpha);
                        textView.startAnimation(reverseAlpha);
                        imageView.setVisibility(View.VISIBLE);
                        textView.setVisibility(View.VISIBLE);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }

    //this is added to all of the random methods

    public void provideAnimationListener(final ImageView imageView, Animation animation, final TextView textView){
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                imageView.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Animation reverseAlpha = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.reverse_alpha);
                imageView.startAnimation(reverseAlpha);
                textView.startAnimation(reverseAlpha);
                imageView.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);

                sendEmail();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }



    public void flyStraightFromLeftSmall(final ImageView imageView, final TextView textView){

        ImageView bigWitch1 = (ImageView) findViewById(R.id.witchIconLarge1);
        bigWitch1.clearAnimation();

        bigWitch1.setMaxHeight(750);
        bigWitch1.setMaxWidth(750);
        bigWitch1.setVisibility(View.VISIBLE);

        TranslateAnimation animation1 = new TranslateAnimation(-1000.0f, 2100.0f, 100.0f, 100.0f);
        animation1.setDuration(1800);
        animation1.setRepeatCount(0);
        animation1.setRepeatMode(0);
        animation1.setFillAfter(true);
        bigWitch1.startAnimation(animation1);

        provideAnimationListener(imageView, animation1, textView);

    }

    public void flyStraightFromLeftBig(final ImageView imageView, final TextView textView){

        ImageView bigWitch1 = (ImageView) findViewById(R.id.witchIconLarge1);
        bigWitch1.clearAnimation();

        bigWitch1.setMaxHeight(2500);
        bigWitch1.setMaxWidth(2500);
        bigWitch1.setVisibility(View.VISIBLE);

        TranslateAnimation animation1 = new TranslateAnimation(-1000.0f, 2100.0f, 100.0f, 100.0f);
        animation1.setDuration(1800);
        animation1.setRepeatCount(0);
        animation1.setRepeatMode(0);
        animation1.setFillAfter(true);
        bigWitch1.startAnimation(animation1);

        provideAnimationListener(imageView, animation1, textView);
    }



    public void flyFromLowerLeftSmall(final ImageView imageView, final TextView textView){

        ImageView bigWitch2 = (ImageView) findViewById(R.id.witchIconLarge1);
        bigWitch2.clearAnimation();
        bigWitch2.setMaxHeight(600);
        bigWitch2.setMaxWidth(600);
        bigWitch2.setVisibility(View.VISIBLE);

        TranslateAnimation animation2 = new TranslateAnimation(-1000.0f, 2100.0f, 200.0f, -700.0f);
        animation2.setDuration(2000);
        animation2.setRepeatCount(0);
        animation2.setRepeatMode(0);
        animation2.setFillAfter(true);
        bigWitch2.startAnimation(animation2);

        provideAnimationListener(imageView, animation2, textView);

    }

    public void flyFromLowerLeftBig(final ImageView imageView, final TextView textView){

        ImageView bigWitch2 = (ImageView) findViewById(R.id.witchIconLarge1);
        bigWitch2.clearAnimation();
        bigWitch2.setMaxHeight(2600);
        bigWitch2.setMaxWidth(2600);
        bigWitch2.setVisibility(View.VISIBLE);

        TranslateAnimation animation2 = new TranslateAnimation(-1000.0f, 2100.0f, 200.0f, -700.0f);
        animation2.setDuration(2000);
        animation2.setRepeatCount(0);
        animation2.setRepeatMode(0);
        animation2.setFillAfter(true);
        bigWitch2.startAnimation(animation2);

        provideAnimationListener(imageView, animation2, textView);

    }

    public void flyFromUpperLeftSmall(final ImageView imageView, final TextView textView){

        ImageView bigWitch3 = (ImageView) findViewById(R.id.witchIconLarge1);
        bigWitch3.clearAnimation();

        bigWitch3.setMaxHeight(500);
        bigWitch3.setMaxWidth(500);
        bigWitch3.setVisibility(View.VISIBLE);

        TranslateAnimation animation3 = new TranslateAnimation(-1000.0f, 2100.0f, -600.0f, 900.0f);
        animation3.setDuration(2000);
        animation3.setRepeatCount(0);
        animation3.setRepeatMode(0);
        animation3.setFillAfter(true);
        bigWitch3.startAnimation(animation3);

        provideAnimationListener(imageView, animation3, textView);
    }

    public void flyFromUpperLeftBig(final ImageView imageView, final TextView textView){

        ImageView bigWitch3 = (ImageView) findViewById(R.id.witchIconLarge1);
        bigWitch3.clearAnimation();

        bigWitch3.setMaxHeight(3000);
        bigWitch3.setMaxWidth(3000);
        bigWitch3.setVisibility(View.VISIBLE);

        TranslateAnimation animation3 = new TranslateAnimation(-1000.0f, 2100.0f, -600.0f, 900.0f);
        animation3.setDuration(2000);
        animation3.setRepeatCount(0);
        animation3.setRepeatMode(0);
        animation3.setFillAfter(true);
        bigWitch3.startAnimation(animation3);

        provideAnimationListener(imageView, animation3, textView);

    }



    public void flyFromLowerRightBig(final ImageView imageView, final TextView textView){

        ImageView bigWitchBackward1 = (ImageView) findViewById(R.id.witchIconLargeBackWard1);
        bigWitchBackward1.clearAnimation();

        bigWitchBackward1.setMaxHeight(3500);
        bigWitchBackward1.setMaxWidth(3500);
        bigWitchBackward1.setVisibility(View.VISIBLE);

        TranslateAnimation animation1 = new TranslateAnimation(1000.0f, -2100.0f, 600.0f, -900.0f);
        animation1.setDuration(2000);
        animation1.setRepeatCount(0);
        animation1.setRepeatMode(0);
        animation1.setFillAfter(true);
        bigWitchBackward1.startAnimation(animation1);

        provideAnimationListener(imageView, animation1, textView);
    }

    public void flyFromLowerRightSmall(final ImageView imageView, final TextView textView){

        ImageView bigWitchBackward1 = (ImageView) findViewById(R.id.witchIconLargeBackWard1);
        bigWitchBackward1.clearAnimation();

        bigWitchBackward1.setMaxHeight(600);
        bigWitchBackward1.setMaxWidth(600);
        bigWitchBackward1.setVisibility(View.VISIBLE);

        TranslateAnimation animation1 = new TranslateAnimation(1000.0f, -2100.0f, 600.0f, -900.0f);
        animation1.setDuration(2000);
        animation1.setRepeatCount(0);
        animation1.setRepeatMode(0);
        animation1.setFillAfter(true);
        bigWitchBackward1.startAnimation(animation1);

        provideAnimationListener(imageView, animation1, textView);
    }

    public void flyFromUpperRightBig(final ImageView imageView, final TextView textView){

        ImageView bigWitchBackward1 = (ImageView) findViewById(R.id.witchIconLargeBackWard1);
        bigWitchBackward1.clearAnimation();

        bigWitchBackward1.setMaxHeight(3500);
        bigWitchBackward1.setMaxWidth(3500);
        bigWitchBackward1.setVisibility(View.VISIBLE);

        TranslateAnimation animation1 = new TranslateAnimation(1000.0f, -1100.0f, -900.0f, 500.0f);
        animation1.setDuration(2000);
        animation1.setRepeatCount(0);
        animation1.setRepeatMode(0);
        animation1.setFillAfter(true);
        bigWitchBackward1.startAnimation(animation1);

        provideAnimationListener(imageView, animation1, textView);
    }

    public void flyFromUpperRightSmall(final ImageView imageView, final TextView textView){

        ImageView bigWitchBackward1 = (ImageView) findViewById(R.id.witchIconLargeBackWard1);
        bigWitchBackward1.clearAnimation();

        bigWitchBackward1.setMaxHeight(500);
        bigWitchBackward1.setMaxWidth(500);
        bigWitchBackward1.setVisibility(View.VISIBLE);

        TranslateAnimation animation1 = new TranslateAnimation(1000.0f, -1100.0f, -900.0f, 500.0f);
        animation1.setDuration(2000);
        animation1.setRepeatCount(0);
        animation1.setRepeatMode(0);
        animation1.setFillAfter(true);
        bigWitchBackward1.startAnimation(animation1);

        provideAnimationListener(imageView, animation1, textView);
    }

    public void flyRightToLeftStraightBig(final ImageView imageView, final TextView textView){

        ImageView bigWitchBackward1 = (ImageView) findViewById(R.id.witchIconLargeBackWard1);
        bigWitchBackward1.clearAnimation();

        bigWitchBackward1.setMaxHeight(3000);
        bigWitchBackward1.setMaxWidth(3000);
        bigWitchBackward1.setVisibility(View.VISIBLE);

        TranslateAnimation animation1 = new TranslateAnimation(1000.0f, -2100.0f, 150.0f, 150.0f);
        animation1.setDuration(2000);
        animation1.setRepeatCount(0);
        animation1.setRepeatMode(0);
        animation1.setFillAfter(true);
        bigWitchBackward1.startAnimation(animation1);

        provideAnimationListener(imageView, animation1, textView);
    }

    public void flyRightToLeftStraightSmall(final ImageView imageView, final TextView textView){

        ImageView bigWitchBackward1 = (ImageView) findViewById(R.id.witchIconLargeBackWard1);
        bigWitchBackward1.clearAnimation();

        bigWitchBackward1.setMaxHeight(500);
        bigWitchBackward1.setMaxWidth(500);
        bigWitchBackward1.setVisibility(View.VISIBLE);

        TranslateAnimation animation1 = new TranslateAnimation(1000.0f, -2100.0f, 150.0f, 150.0f);
        animation1.setDuration(2000);
        animation1.setRepeatCount(0);
        animation1.setRepeatMode(0);
        animation1.setFillAfter(true);
        bigWitchBackward1.startAnimation(animation1);

        provideAnimationListener(imageView, animation1, textView);
    }




    public void randomAnimation(final ImageView imageView, final TextView textView){

        Random random = new Random();
        int number =  random.nextInt(20);


        switch (number){

            case 0:
                makeWitchGrow(imageView, textView);
                break;

            case 1:
                flyFromLowerLeftBig(imageView, textView);
                break;

            case 2:
                flyFromLowerLeftSmall(imageView, textView);
                break;

            case 3:
                flyFromLowerRightBig(imageView, textView);
                break;

            case 4:
                flyFromLowerRightSmall(imageView, textView);
                break;

            case 5:
                flyFromUpperLeftBig(imageView, textView);
                break;

            case 6:
                flyFromUpperLeftSmall(imageView, textView);
                break;

            case 7:
                flyFromUpperRightBig(imageView, textView);
                break;

            case 8:
                flyFromLowerRightSmall(imageView, textView);
                break;

            case 9:
                flyRightToLeftStraightBig(imageView, textView);
                break;

            case 10:
                flyRightToLeftStraightSmall(imageView, textView);
                break;

            case 11:
                flyStraightFromLeftBig(imageView, textView);
                break;

            case 12:
                flyStraightFromLeftBig(imageView, textView);
                break;

            case 13:
                flyFromUpperRightBig(imageView, textView);
                flyFromUpperLeftBig(imageView, textView);
                break;

            case 14:
                flyFromLowerLeftBig(imageView, textView);
                flyFromLowerRightBig(imageView, textView);
                break;

            case 15:
                flyFromUpperRightSmall(imageView, textView);
                flyFromUpperLeftSmall(imageView, textView);
                break;

            case 16:
                flyFromLowerLeftSmall(imageView, textView);
                flyFromLowerRightSmall(imageView, textView);
                break;

            case 17:
                flyFromUpperRightBig(imageView, textView);
                flyFromUpperLeftSmall(imageView,textView);
                break;

            case 18:
                flyFromLowerLeftBig(imageView,textView);
                flyFromLowerRightSmall(imageView,textView);
                break;

            case 19:
                flyFromLowerLeftBig(imageView, textView);
                flyRightToLeftStraightSmall(imageView,textView);
                break;

            case 20:
                flyFromLowerRightBig(imageView, textView);
                flyStraightFromLeftSmall(imageView, textView);
                break;

            default:
                makeWitchGrow(imageView, textView);
                break;

        }





    }





}