package com.master.gullycricket;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    TextView textView,textView2,textView3,textView4,textView5;
    Button b1,b2,b3,b4,b6,b0,bwd,bnb,bw;
    int balls=0,star=0,wd=1,nb=1,wicket=0,runs=0,wickets=0,overruns=0,started=0;
    String summary="";
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.count);
        textView2=(TextView)findViewById(R.id.countw);
        textView3=(TextView)findViewById(R.id.counto);
        textView4=(TextView)findViewById(R.id.countr);
        textView5=(TextView)findViewById(R.id.board);
        b1=(Button)findViewById(R.id.b1);
        b2=(Button)findViewById(R.id.b2);
        b3=(Button)findViewById(R.id.b3);
        b4=(Button)findViewById(R.id.b4);
        b6=(Button)findViewById(R.id.b6);
        bwd=(Button)findViewById(R.id.bwd);
        b0=(Button)findViewById(R.id.b0);
        bnb=(Button)findViewById(R.id.bnb);
        bw=(Button)findViewById(R.id.bw);
        intent=getIntent();
        wd=intent.getIntExtra("wide",0);
        nb=intent.getIntExtra("noball",0);
        wicket=intent.getIntExtra("wicket",0);
    }
    public void dot(View view)
    {
        startcounter(runs,runs+0);
        runs+=0;
        balls++;
        setballs(balls);
        int startColor = getWindow().getStatusBarColor();
        int endColor = ContextCompat.getColor(this, R.color.colorPrimary);
        ObjectAnimator.ofArgb(getWindow(), "statusBarColor", startColor, endColor).start();
        update("0");
    }
    public void single(View view)
    {
        runs++;
        textView.setText(runs+"");
        balls++;
        setballs(balls);
        int startColor = getWindow().getStatusBarColor();
        int endColor = ContextCompat.getColor(this, R.color.colorPrimary);
        ObjectAnimator.ofArgb(getWindow(), "statusBarColor", startColor, endColor).start();
        update("1");
    }
    public void doub(View view)
    {
        startcounter(runs,runs+2);
        runs+=2;
        balls++;
        setballs(balls);
        int startColor = getWindow().getStatusBarColor();
        int endColor = ContextCompat.getColor(this, R.color.colorPrimary);
        ObjectAnimator.ofArgb(getWindow(), "statusBarColor", startColor, endColor).start();
        update("2");
    }
    public void three(View view)
    {
        startcounter(runs,runs+3);
        runs+=3;
        balls++;
        setballs(balls);
        int startColor = getWindow().getStatusBarColor();
        int endColor = ContextCompat.getColor(this, R.color.colorPrimary);
        ObjectAnimator.ofArgb(getWindow(), "statusBarColor", startColor, endColor).start();
        update("3");
    }
    public void four(View view)
    {
        startcounter(runs,runs+4);
        runs+=4;
        balls++;
        setballs(balls);
        int startColor = getWindow().getStatusBarColor();
        int endColor = ContextCompat.getColor(this, R.color.lime_green);
        ObjectAnimator.ofArgb(getWindow(), "statusBarColor", startColor, endColor).start();
        update("4");
    }
    public void six(View view)
    {
        startcounter(runs,runs+6);
        runs+=6;
        balls++;
        setballs(balls);
        int startColor = getWindow().getStatusBarColor();
        int endColor = ContextCompat.getColor(this, R.color.lime_green);
        ObjectAnimator.ofArgb(getWindow(), "statusBarColor", startColor, endColor).start();
        update("6");
    }
    public  void noball(View view)
    {
        runs+=nb;
        textView.setText(runs+nb+"");
        int startColor = getWindow().getStatusBarColor();
        int endColor = ContextCompat.getColor(this, R.color.hot_pink);
        ObjectAnimator.ofArgb(getWindow(), "statusBarColor", startColor, endColor).start();
        update("NB");
    }
    public void wide(View view)
    {
        runs+=wd;
        textView.setText(runs+wd+"");
        int startColor = getWindow().getStatusBarColor();
        int endColor = ContextCompat.getColor(this, R.color.orange);
        ObjectAnimator.ofArgb(getWindow(), "statusBarColor", startColor, endColor).start();
        update("WD");
    }
    public void wicket(View view)
    {
        startcounter1(wickets, wickets + 1);
        wickets++;
        balls++;
        setballs(balls);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.red));
        update("W");
        if(wickets==wicket)
        {
            disabe();
            Toast.makeText(getApplicationContext(),"Innings ends",Toast.LENGTH_SHORT).show();
        }
    }
    private void startcounter(int start,int end)
    {
        ValueAnimator animator = ValueAnimator.ofInt(start,end); //0 is min number, 600 is max number
        animator.setDuration(300); //Duration is in milliseconds
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation)
            {
                textView.setText(animation.getAnimatedValue().toString());
            }
        });
        animator.start();
    }
    private void startcounter1(int start,int end)
    {
        ValueAnimator animator = ValueAnimator.ofInt(start,end);
        animator.setDuration(100);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            public void onAnimationUpdate(ValueAnimator animation) {
                textView2.setText(animation.getAnimatedValue().toString());
            }
        });
        animator.start();
    }
    private void setballs(int ball)
    {
        if(ball%6==0)
        {
            textView3.setText(""+ball/6);
            star=0;
        }
        textView4.setText(""+ball%6);
    }
    private void update(String stat)
    {
        if(balls%6==0 && ((stat.equals("WD"))||(stat.equals("NB"))) && star==0)
        {
            if(started!=0)
            {
                summary+="\n"+"Over "+String.valueOf((balls/6)+1)+":   ";
                started+=1;
            }
            else
            {
                summary+="\n"+"Over "+String.valueOf((balls/6)+1)+":   ";
            }

            star=1;
            Toast.makeText(getApplicationContext(),overruns+" ",Toast.LENGTH_SHORT).show();
        }
        else if(balls%6==1 && star!=1)
        {
            if(started!=0)
            {
                summary+="\n"+"Over "+String.valueOf((balls/6)+1)+":   ";
                started+=1;
            }

            else
            {
                summary+="\n"+"Over "+String.valueOf((balls/6)+1)+":   ";
            }
            summary+="\n"+"Over "+String.valueOf((balls/6)+1)+":   ";
            star=1;
            Toast.makeText(getApplicationContext(),overruns+" ",Toast.LENGTH_SHORT).show();
        }
        summary+=stat+" ";
        textView5.setText(summary);
        if(balls%6==0)
        {
            Toast.makeText(getApplicationContext(), "Over Completed", Toast.LENGTH_SHORT).show();
        }
        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.clear:
                balls=0;
                star=0;
                runs=0;
                wickets=0;
                overruns=0;
                summary="";
                started=0;
                textView.setText("0");
                textView2.setText("0");
                textView3.setText("0");
                textView4.setText("0");
                textView5.setText("Summary Here");
                int startColor = getWindow().getStatusBarColor();
                int endColor = ContextCompat.getColor(this, R.color.colorPrimary);
                ObjectAnimator.ofArgb(getWindow(), "statusBarColor", startColor, endColor).start();
                enabe();
                break;
            case R.id.send:
                sendscore();
                break;
            case R.id.sends:
                sendsummary();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }
    private void sendscore()
    {
        Intent i=new Intent();
        i.setAction(Intent.ACTION_SEND);
        i.putExtra(Intent.EXTRA_TEXT,"Score "+textView.getText()+"/"+textView2.getText()+", Overs "+textView3.getText()+"."+textView4.getText());
        i.setType("text/plain");
        i.setPackage("com.whatsapp");
        startActivity(i);
    }
    private void sendsummary()
    {
        Intent i2=new Intent();
        i2.setAction(Intent.ACTION_SEND);
        i2.putExtra(Intent.EXTRA_TEXT,"Summary \n"+summary+"\n"+"Total Score "+textView.getText()+"/"+textView2.getText()+", Overs "+textView3.getText()+"."+textView4.getText());
        i2.setType("text/plain");
        i2.setPackage("com.whatsapp");
        startActivity(i2);
    }
    private void disabe()
    {
        b1.setEnabled(false);
        b2.setEnabled(false);
        b3.setEnabled(false);
        b4.setEnabled(false);
        b6.setEnabled(false);
        b0.setEnabled(false);
        bw.setEnabled(false);
        bwd.setEnabled(false);
        bnb.setEnabled(false);
    }
    private void enabe()
    {
        b1.setEnabled(true);
        b2.setEnabled(true);
        b3.setEnabled(true);
        b4.setEnabled(true);
        b6.setEnabled(true);
        b0.setEnabled(true);
        bw.setEnabled(true);
        bwd.setEnabled(true);
        bnb.setEnabled(true);
    }
}
