package space.kur.dnddiceandcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    EditText et;
    TextView r;
    EditText eq;
    EditText bon;
    TextView txx;
    TextView summa;
    Switch sw1;
    Switch sw2;
    int prem=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = findViewById(R.id.et);
        eq = findViewById(R.id.eq);
        bon = findViewById(R.id.bon);
        r = findViewById(R.id.result);
        txx = findViewById(R.id.textView4);
        summa = findViewById(R.id.textView);
        sw1 = findViewById(R.id.switch1);
        sw2 = findViewById(R.id.switch2);

        sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {         //преимущество
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sw2.setChecked(false);
                    prem=1;
                } else {
                    prem=0;
                }
            }
        });

        sw2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {        //помеха
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sw1.setChecked(false);
                    prem=2;
                } else {
                    prem=0;
                }
            }
        });
    }

    public void roll(Integer t){
        int k;
        int bonn;
        int chek=0;
        int sum=0;
        int sum2=0;
        int krit=0;
        int antikrit=0;
        if(et.getText().length()!=0){                                   //получаем кол-во бросков
        k=Integer.parseInt(et.getText().toString());}
        else{k=1;}
        if(bon.getText().length()!=0){                                   //получаем бонус к броску
            bonn=Integer.parseInt(bon.getText().toString());}
        else{bonn=0;}
        int a[]=new int[k];
        String str="";
        if(prem==0){
            for(int i=0;i<k;i++){                                        //роллим и считаем попадания и криты
                a[i]=(int)(Math.random()*t+1);
                sum+=a[i]+bonn;
                if(t==20){
                    if (a[i]==20){
                        krit+=1;
                        a[i]=a[i]+5;
                    }
                    if (a[i]==1){
                        antikrit+=1;
                        a[i]=a[i]-5;
                    }
                }
                if (i!=k-1) {
                    str+=(a[i]+bonn+", ");
                }
                else{
                    str+=(a[i]+bonn);
                }

            }
        }
        else if(prem==1){
            for(int i=0;i<k;i++){                                        //роллим с преимуществом и считаем попадания и криты
                int[] prostomassiv= {(int)(Math.random()*t+1), (int)(Math.random()*t+1)};
                Arrays.sort(prostomassiv);
                a[i]=prostomassiv[1];
                sum+=a[i]+bonn;
                if(t==20){
                    if (a[i]==20){
                        krit+=1;
                        a[i]=a[i]+5;
                    }
                    if (a[i]==1){
                        antikrit+=1;
                        a[i]=a[i]-5;
                    }
                }
                if (i!=k-1) {
                    str+=(a[i]+bonn+", ");
                }
                else{
                    str+=(a[i]+bonn);
                }

            }
        }
        else if(prem==2){
            for(int i=0;i<k;i++){                                        //роллим с помехой и считаем попадания и криты
                int[] prostomassiv= {(int)(Math.random()*t+1), (int)(Math.random()*t+1)};
                Arrays.sort(prostomassiv);
                a[i]=prostomassiv[0];
                sum+=a[i]+bonn;
                if(t==20){
                    if (a[i]==20){
                        krit+=1;
                        a[i]=a[i]+5;
                    }
                    if (a[i]==1){
                        antikrit+=1;
                        a[i]=a[i]-5;
                    }
                }
                if (i!=k-1) {
                    str+=(a[i]+bonn+", ");
                }
                else{
                    str+=(a[i]+bonn);
                }

            }
        }
        r.setText(str);
        int kkk;
        if(eq.getText().length()!=0){                            //сравнение сколько бросков больше кд
            kkk=Integer.parseInt(eq.getText().toString());
            for(int i=0;i<k;i++){
                if(a[i]+bonn>=kkk){
                    chek+=1;
                    sum2+=a[i]+bonn;
                }
            }
            txx.setText("больше у "+String.valueOf(chek)+" их сумма "+String.valueOf(sum2)+"\n"+"критов "+String.valueOf(krit)+", антикритов "+String.valueOf(antikrit));
        }
        else {
            txx.setText("критов "+String.valueOf(krit)+", антикритов "+String.valueOf(antikrit));
        }
        if(k>1){
            summa.setText("сумма "+ String.valueOf(sum));
        }
        else {
            summa.setText("");
        }
    }
    public void rolld4(View view){
        roll(4);
    }
    public void rolld6(View view){
        roll(6);
    }
    public void rolld8(View view){
        roll(8);
    }
    public void rolld10(View view){
        roll(10);
    }
    public void rolld12(View view){
        roll(12);
    }
    public void rolld20(View view){
        roll(20);
    }
    public void rolld100(View view){
        roll(100);
    }
}
