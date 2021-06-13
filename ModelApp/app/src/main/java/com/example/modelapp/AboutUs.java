package com.example.modelapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

//android:textColor="#636363"

public class AboutUs extends AppCompatActivity {

    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        //textviews
        TextView mess = (TextView) findViewById(R.id.textView);
        TextView text2= (TextView) findViewById(R.id.textView2);
        TextView text10 = (TextView) findViewById(R.id.textView10);
        TextView text12 = (TextView) findViewById(R.id.textView12);
        text12.setClickable(true);
        text12.setMovementMethod(LinkMovementMethod.getInstance());

        //buttons
        Button sympt =(Button) findViewById(R.id.button);
        Button remed =(Button) findViewById(R.id.button2);

        //database
        dataBaseHelper = new DataBaseHelper(AboutUs.this);


        Intent intent = getIntent();
        String st= intent.getStringExtra("message_key");

        if(st.equals("Click below to select a Image"))
        {mess.setText("");}
        else
        {mess.setText(st);}



        Toolbar myToolbar1 = (Toolbar) findViewById(R.id.my_toolbar1);
        setSupportActionBar(myToolbar1);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);





        sympt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text10.setText("");

                if(mess.getText().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"No disease detected",Toast.LENGTH_SHORT).show();

                }
                else
                {
//                    text10.setText("");
//
//                   dataBaseHelper.fetchDataD(st);
                    String res=setsymptom(st);
                    text10.setText(res);

                }

            }
        });

        remed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text12.setText("");

                if(mess.getText().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"No disease detected",Toast.LENGTH_SHORT).show();

                }
                else
                {
//                    text12.setText("");
//                    String res = dataBaseHelper.fetchDataR(st);
//                    Toast.makeText(getApplicationContext(),"result is"+ res,Toast.LENGTH_SHORT).show();
//                    text12.setText(res);
                    String res=setRemedies(st);
                    text12.setText(Html.fromHtml(res));
                }


            }
        });


    }


    //code for back button in toolbar..
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                this.finish();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
    }



    //Backend for symtoms
    public String setsymptom(String str){

        String s="";


        if(str.equals("Pepper_bell_Bacterial_spot"))
        {
            s="Liquid Copper,Seed treatment with hot water, soaking seeds for 30 minutes in water pre-heated to 125 F/51 C, is effective in reducing bacterial populations on the surface and inside the seeds";
        }

        if(str.equals("Pepper_bell_healthy"))
        {
            s="As the leave is healthy so no remedies";
        }
        if(str.equals("Potato_Early_blight"))
        {
            s="Azoxystrobin,Treatment of early blight includes prevention by planting potato varieties that are resistant to the disease; late maturing are more resistant than early maturing varieties. Avoid overhead irrigation and allow for sufficient aeration between plants to allow the foliage to dry as quickly as possible.";
        }
        if(str.equals("Potato_Late_blight"))
        {
            s="The severe late blight can be effectively managed with prophylactic spray of mancozeb at 0.25% followed by cymoxanil+mancozeb or dimethomorph+mancozeb at 0.3% at the onset of disease and one more spray of mancozeb at 0.25% seven days after application of systemic fungicides";
        }
        if(str.equals("Potato_healthy"))
        {
            s="As the leave is healthy so no remedies";
        }
        if(str.equals("Tomato_Bacterial_spot"))
        {
            s="Sodium Hypochlorite,Copper fungicides are the most commonly recommended treatment for bacterial leaf spot";
        }

        if(str.equals("Tomato_Early_blight"))
        {
            s="Hydroperoxyl,Bacillus_Subtillis,Thoroughly spray the plant (bottoms of leaves also) with Bonide Liquid Copper Fungicide concentrate or Bonide Tomato & Vegetable";
        }
        if(str.equals("Tomato_Late_blight"))
        {
            s="Actinovate,copper,Thoroughly spray the plant (bottoms of leaves also) with Bonide Liquid Copper Fungicide concentrate or Bonide Tomato & Vegetable";
        }
        if(str.equals("Tomato_Leaf_Mold"))
        {
            s="Seasameoil,Rosemaryoil,Use drip irrigation and avoid watering foliage. Use a stake, strings, or prune the plant to keep it upstanding and increase airflow in and around it. Remove and destroy (burn) all plants debris after the harvest.";
        }
        if(str.equals("Tomato_Septoria_leaf_spot"))
        {
            s="Chlorothalonil,macozeb,Removing infected leaves and be sure to wash your hands";
        }
        if(str.equals("Tomato_Spider_mites_Two_spotted_spider_mite"))
        {
            s="horticulture oil,insectisidal soap,Apply the miticide for control of two-spotted mites every 7 days ";
        }

        if(str.equals("Tomato_Target_Spot"))
        {
            s="Chlorothalonil,macozeb,Cage or stake tomato plants to keep the plants above the soil. Water tomato plants in the morning so the leaves have time to dry. Water at the base of the plant or use a soaker hose or drip system to keep the leaves dry. Apply a mulch to keep the fruit from coming in direct contact with the soil.";
        }
        if(str.equals("Tomato_YellowLeaf_Curl_Virus"))
        {
            s="Dinotefuran,imidacloprid";
        }
        if(str.equals("Tomato_mosaic_virus"))
        {
            s="Chlorothalonil,macozeb";
        }
        if(str.equals("Tomato_healthy"))
        {
            s="As the leave is healthy so no remedies";
        }
        return s;
    }


    //backend for remedies

    public String setRemedies(String str){

        String s="";


        if(str.equals("Pepper_bell_Bacterial_spot"))
        {
            s="<a href='https://www.google.com/search?q=pepper+bell+bacterial+spot+remedies&safe=active&rlz=1C1CHBF_enIN891IN892&sxsrf=ALeKk03mWXPHRfEXUni4ZPPOV_TWE6r_-A%3A1622647835318&ei=G6S3YIPjEo2z9QOL-rCwAg&oq=pepper+bell+bacterial+spot+remedies&gs_lcp=Cgdnd3Mtd2l6EAMyBggAEBYQHjoHCAAQRxCwAzoCCAA6CAghEBYQHRAeOgcIIRAKEKABOgQIIRAVUO8bWK4oYOMsaAFwAngAgAGoAogBlROSAQQyLTEwmAEAoAEBqgEHZ3dzLXdpesgBCMABAQ&sclient=gws-wiz&ved=0ahUKEwjD3MaeovnwAhWNWX0KHQs9DCYQ4dUDCA4&uact=5'>Links</a>";
        }

        if(str.equals("Pepper_bell_healthy"))
        {
            s="As the leave is healthy so no remedies";
        }
        if(str.equals("Potato_Early_blight"))
        {
            s="<a href='https://www.google.com/search?q=potato+early+blight+remedies&safe=active&rlz=1C1CHBF_enIN891IN892&sxsrf=ALeKk01sOwi2ZA9MZLXvczcn-NMbNKcCAQ%3A1622647959193&ei=l6S3YNG3C_iG4-EPwdWumAU&oq=potato+early+blight+remedies&gs_lcp=Cgdnd3Mtd2l6EAMyBQgAEM0CMgUIABDNAjoHCAAQRxCwAzoECCMQJzoCCAA6BggAEBYQHjoICCEQFhAdEB46BwghEAoQoAFQ0QhY5SBghixoAHADeACAAfwBiAGjHZIBBjAuMS4xNpgBAKABAaoBB2d3cy13aXrIAQjAAQE&sclient=gws-wiz&ved=0ahUKEwiR38_ZovnwAhV4wzgGHcGqC1MQ4dUDCA4&uact=5'>Links</a>";
        }
        if(str.equals("Potato_Late_blight"))
        {
            s="<a href='https://www.google.com/search?q=potato+late+blight+remedies&safe=active&rlz=1C1CHBF_enIN891IN892&sxsrf=ALeKk00fTH3osJUViZNVUNrCPWiDzWaDrQ%3A1622647966136&ei=nqS3YPntB_Cf4-EP3vCckAs&oq=potato+late+blight+remedies&gs_lcp=Cgdnd3Mtd2l6EAMyBQgAEM0CMgUIABDNAjIFCAAQzQIyBQgAEM0CMgUIABDNAjoHCAAQRxCwAzoGCAAQCBAeUInrAliI-QJgz4EDaAJwAngAgAHmAogBmBOSAQUyLTguMpgBAKABAaoBB2d3cy13aXrIAQjAAQE&sclient=gws-wiz&ved=0ahUKEwj5tPfcovnwAhXwzzgGHV44B7IQ4dUDCA4&uact=5'>Links</a>";
        }
        if(str.equals("Potato_healthy"))
        {
            s="As the leave is healthy so no remedies";
        }
        if(str.equals("Tomato_Bacterial_spot"))
        {
            s="<a href='https://www.google.com/search?q=tomato+bacterial+spot+remedies&safe=active&rlz=1C1CHBF_enIN891IN892&sxsrf=ALeKk03gbJgWLgf36Qw4WyybJQgzhy-A4g%3A1622648072535&ei=CKW3YOKPIIKY4-EPlv2gyAM&oq=tomato+bacterial+spot+remedies&gs_lcp=Cgdnd3Mtd2l6EAMyBggAEBYQHjIGCAAQFhAeOgcIABBHELADOgIIADoICCEQFhAdEB46BwghEAoQoAFQ4ARYiBZgqhloAHADeACAAYYCiAHhEJIBBTAuNS41mAEAoAEBqgEHZ3dzLXdpesgBCMABAQ&sclient=gws-wiz&ved=0ahUKEwjis9WPo_nwAhUCzDgGHZY-CDkQ4dUDCA4&uact=5'>Links</a>";
        }

        if(str.equals("Tomato_Early_blight"))
        {
            s="<a href='https://www.google.com/search?q=tomato+early+blight++remedies&safe=active&rlz=1C1CHBF_enIN891IN892&sxsrf=ALeKk02vr6h572BriPKcH9oecStYAAOhXg%3A1622648077062&ei=DaW3YLf0Arib4-EPlbOsqAE&oq=tomato+early+blight++remedies&gs_lcp=Cgdnd3Mtd2l6EAMyBQgAEM0CMgUIABDNAjIFCAAQzQIyBQgAEM0CMgUIABDNAjoHCAAQRxCwAzoGCAAQBxAeOgIIAFC3-gJY_6UDYJyyA2gHcAJ4AYAB2AaIAZAvkgELMi0xLjYuMS40LjGYAQCgAQGqAQdnd3Mtd2l6yAEIwAEB&sclient=gws-wiz&ved=0ahUKEwj3rumRo_nwAhW4zTgGHZUZCxUQ4dUDCA4&uact=5'>Links</a>";
        }
        if(str.equals("Tomato_Late_blight"))
        {
            s="<a href='https://www.google.com/search?q=tomato+late+blight+remedies&rlz=1C1CHBF_enIN891IN892&oq=tomato+late+blight+remedies&aqs=chrome..69i57j33i10i160.8591j0j7&sourceid=chrome&ie=UTF-8'>Linsk</a>";
        }
        if(str.equals("Tomato_Leaf_Mold"))
        {
            s="<a href='https://www.google.com/search?q=tomato+leaf+mold+remedies&safe=active&rlz=1C1CHBF_enIN891IN892&sxsrf=ALeKk01yMHJ7QguzfIcLkajS3MzQJjJ4jA%3A1622648209950&ei=kaW3YLu1OcqP9QOex4TQBw&oq=tomato+leaf+mold+remedies&gs_lcp=Cgdnd3Mtd2l6EAM6BwgAEEcQsAM6BggAEAcQHjoCCAA6BAgAEA06CAgAEAgQBxAeOggIABAIEA0QHjoGCAAQCBAeUIKPAliPvwJgx8YCaANwAngCgAH0CIgBuE6SAQ0yLTEuNS4yLjIuNS4ymAEAoAEBqgEHZ3dzLXdpesgBCMABAQ&sclient=gws-wiz&ved=0ahUKEwj7wZjRo_nwAhXKR30KHZ4jAXoQ4dUDCA4&uact=5'>Links</a>";
        }
        if(str.equals("Tomato_Septoria_leaf_spot"))
        {
            s="<a href='https://www.google.com/search?q=tomato+sepotaria+leaf+spot+remedies&safe=active&rlz=1C1CHBF_enIN891IN892&sxsrf=ALeKk01x5o3DJMgilmDD4e5OOeRKOHGBzg%3A1622648253606&ei=vaW3YOi8JIe9rQGtlq9g&oq=tomato+sepotaria+leaf+spot+remedies&gs_lcp=Cgdnd3Mtd2l6EAMyBQgAEM0CMgUIABDNAjoHCAAQRxCwA1Cc_QFY_68CYJy2AmgBcAJ4A4AB7wSIAdZXkgEKMi0zLjExLjYuN5gBAKABAaoBB2d3cy13aXrIAQjAAQE&sclient=gws-wiz&ved=0ahUKEwioj4Hmo_nwAhWHXisKHS3LCwwQ4dUDCA4&uact=5'>Links</a>";
        }
        if(str.equals("Tomato_Spider_mites_Two_spotted_spider_mite"))
        {
            s="<a href='https://www.google.com/search?q=Tomato_Spider_mites_Two_spotted_spider_mite+remedies&safe=active&rlz=1C1CHBF_enIN891IN892&sxsrf=ALeKk03KB8IIwm9k5ITEAcb_1AkdYAlhpQ%3A1622648294867&ei=5qW3YJbBNJWR9QOWoI2IDA&oq=Tomato_Spider_mites_Two_spotted_spider_mite+remedies&gs_lcp=Cgdnd3Mtd2l6EAM6BwgjELADECc6BwgAEEcQsAM6BAgjECc6CAgAELEDEJECOgYIABAHEB5Q4pQDWM2cA2CkpgNoAHABeACAAYAGiAGfDpIBCzItMS4xLjEuMC4xmAEAoAEBoAECqgEHZ3dzLXdpesgBCcABAQ&sclient=gws-wiz&ved=0ahUKEwiWzNf5o_nwAhWVSH0KHRZQA8EQ4dUDCA4&uact=5'>Links</a>";
        }

        if(str.equals("Tomato_Target_Spot"))
        {
            s="<a href='https://www.google.com/search?q=Tomato+target+spot+remedies&safe=active&rlz=1C1CHBF_enIN891IN892&sxsrf=ALeKk03QyxwjezJmZNOxeyKGXe9CulPvuQ%3A1622648399204&ei=T6a3YKj4C8GS9QOcuIXwDQ&oq=Tomato+target+spot+remedies&gs_lcp=Cgdnd3Mtd2l6EAMyBAgjECcyBggAEBYQHjoHCAAQRxCwA1CFB1iFB2D-DGgAcAN4AIAB7gKIAZAFkgEFMi0xLjGYAQCgAQGqAQdnd3Mtd2l6yAEIwAEB&sclient=gws-wiz&ved=0ahUKEwjo27erpPnwAhVBSX0KHRxcAd4Q4dUDCA4&uact=5'>Links</a>";
        }
        if(str.equals("Tomato_YellowLeaf_Curl_Virus"))
        {
            s="<a href='https://www.google.com/search?safe=active&rlz=1C1CHBF_enIN891IN892&sxsrf=ALeKk031zdS0BN3lXRTZlWMskTW4ECKXzQ:1622648447818&q=Tomato+__+Tomato+_+Yellow+Leaf+__+Curl+Virus+remedies&spell=1&sa=X&ved=2ahUKEwiV-c7CpPnwAhWLV30KHS47AUwQBSgAegQIARAx&biw=1536&bih=722'>Links</a>";
        }
        if(str.equals("Tomato_mosaic_virus"))
        {
            s="<a href='https://www.google.com/search?safe=active&rlz=1C1CHBF_enIN891IN892&sxsrf=ALeKk01cGyNNCZOieeJlBNhPl80XuuX6yw:1622648490144&q=Tomato+_+Tomato+_+mosaic+_+virus+remedies&spell=1&sa=X&ved=2ahUKEwiLqubWpPnwAhWvzDgGHb7sBzMQBSgAegQIARAx&biw=1536&bih=722'>Links</a>";
        }
        if(str.equals("Tomato_healthy"))
        {
            s="As the leave is healthy so no remedies";
        }
        return s;
    }



}