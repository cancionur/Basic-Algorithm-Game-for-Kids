package com.example.algoritmaoyunu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.os.Handler;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnDragListener, View.OnLongClickListener {

    private ImageView imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, KosanAdam;
    private LinearLayout ust_tasarim, alt_tasarim;
    private ArrayList<String> alinanVeriler = new ArrayList<>();
    private ArrayList<String> dogruVeriler = new ArrayList<>();
    private Button buttonCalistir, buttonSifirla,buttonMenu;
    private Animation birinci_hareket_hata,ikinci_hareket_hata,ucuncu_hareket_hata,dorduncu_hareket_hata,besinci_hareket_bitis;

    private static final String ASAGI = "ASAGI";
    private static final String YUKARI = "YUKARI";
    private static final String SAG = "SAG";
    private static final String SOL = "SOL";
    final Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        birinci_hareket_hata = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.birinci_hareket_hata);
        ikinci_hareket_hata = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.ikinci_hareket_hata);
        ucuncu_hareket_hata = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.ucuncu_hareket_hata);
        dorduncu_hareket_hata = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dorduncu_hareket_hata);
        besinci_hareket_bitis = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.besinci_hareket_bitis);

        imageView = findViewById(R.id.imageView);
        imageView.setTag(SOL);
        imageView2 = findViewById(R.id.imageView2);
        imageView2.setTag(YUKARI);
        imageView3 = findViewById(R.id.imageView3);
        imageView3.setTag(ASAGI);
        imageView4 = findViewById(R.id.imageView4);
        imageView4.setTag(SAG);
        imageView5 = findViewById(R.id.imageView5);
        imageView5.setTag(SAG);
        imageView6 = findViewById(R.id.imageView6);
        imageView6.setTag(YUKARI);
        imageView7 = findViewById(R.id.imageView7);
        imageView7.setTag(SAG);

        KosanAdam = findViewById(R.id.KosanAdam);

        buttonCalistir = findViewById(R.id.buttonCalistir);
        buttonSifirla = findViewById(R.id.buttonSifirla);
        buttonMenu = findViewById(R.id.buttonMenu);

        ust_tasarim = findViewById(R.id.ust_tasarim);
        alt_tasarim = findViewById(R.id.alt_tasarim);

        dogruVeriler.add("SAG");
        dogruVeriler.add("YUKARI");
        dogruVeriler.add("SAG");
        dogruVeriler.add("YUKARI");
        dogruVeriler.add("SAG");


        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GirisEkrani.class));
                finish();
            }
        });

        buttonCalistir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < ust_tasarim.getChildCount(); i++) {
                    View v = ust_tasarim.getChildAt(i);
                    alinanVeriler.add(v.getTag().toString());
                    //   Log.e("Veriler",v.getTag().toString() );
                    //   Log.e("Dogru Veriler",dogruVeriler.get(i) );
                }

                HareketEt(dogruVeriler, alinanVeriler, KosanAdam, alinanVeriler.size());

                alinanVeriler.clear();
            }
        });


        buttonSifirla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alinanVeriler.clear();

                Intent yeniIntent = new Intent(MainActivity.this, MainActivity.class);

                startActivity(yeniIntent);
                finish();
            }
        });

        imageView.setOnLongClickListener(this);
        imageView2.setOnLongClickListener(this);
        imageView3.setOnLongClickListener(this);
        imageView4.setOnLongClickListener(this);
        imageView5.setOnLongClickListener(this);
        imageView6.setOnLongClickListener(this);
        imageView7.setOnLongClickListener(this);

        ust_tasarim.setOnDragListener(this);
        alt_tasarim.setOnDragListener(this);

    }



    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        switch (dragEvent.getAction()) {

            case DragEvent.ACTION_DRAG_STARTED:
                return true;
            case DragEvent.ACTION_DRAG_ENTERED:
                return true;
            case DragEvent.ACTION_DRAG_LOCATION:
                return true;
            case DragEvent.ACTION_DRAG_EXITED:
                return true;
            case DragEvent.ACTION_DROP:

                View gorselNesne = (View) dragEvent.getLocalState();

                ViewGroup eskitasarimalani = (ViewGroup) gorselNesne.getParent();

                eskitasarimalani.removeView(gorselNesne);

                LinearLayout yeniTasarimAlani = (LinearLayout) view;

                yeniTasarimAlani.addView(gorselNesne);

                gorselNesne.setVisibility(View.VISIBLE);

                return true;
            case DragEvent.ACTION_DRAG_ENDED:
                return true;
            default:
                break;
        }
        return false;
    }

    @Override
    public boolean onLongClick(View view) {
        ClipData.Item item = new ClipData.Item((CharSequence) view.getTag());

        String[] mineTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

        ClipData clipData = new ClipData(view.getTag().toString(), mineTypes
                , item);

        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

        view.startDrag(clipData, shadowBuilder, view, 0);

        view.setVisibility(View.INVISIBLE);

        return true;
    }

    public void HataMesajiGoster(int boyut){
        AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);

        if(boyut == 0){
            ad.setMessage("Boş alana komutları sürükleyiniz!");
            ad.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            ad.create().show();
        }

         if (1<=boyut){
             ad.setMessage("Hedefe ulaşamadın, tekrar dene!");
             ad.setPositiveButton("Tekrar Dene", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialogInterface, int i) {
                     startActivity(new Intent(MainActivity.this,MainActivity.class));
                     finish();
                 }
             });
             ad.create().show();
         }
    }


    public void HareketEt(ArrayList<String> ParametreDogruVeriler, ArrayList<String> ParametreAlinanVeriler, ImageView view, int boyut)  {


        if (boyut == 0) {
            HataMesajiGoster(boyut);
        }
        //---------------------------------------------------------------------------------------------------------------------------------------
        else if (boyut == 1) {
            if (ParametreAlinanVeriler.get(0).equals(ParametreDogruVeriler.get(0))) {
               view.startAnimation(birinci_hareket_hata);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        HataMesajiGoster(boyut);
                    }
                }, 1200);
            }
            else {
                HataMesajiGoster(boyut);
            }
        }
        //---------------------------------------------------------------------------------------------------------------------------------------
        else if (boyut == 2) {
            if (ParametreDogruVeriler.get(0).equals(ParametreAlinanVeriler.get(0)) && ParametreDogruVeriler.get(1).equals(ParametreAlinanVeriler.get(1))) {
                view.startAnimation(ikinci_hareket_hata);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        HataMesajiGoster(boyut);
                    }
                }, 1500);
            }
             else if (ParametreAlinanVeriler.get(0).equals(ParametreDogruVeriler.get(0))) {
                view.startAnimation(birinci_hareket_hata);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        HataMesajiGoster(boyut);
                    }
                }, 1200);

            } else {
                HataMesajiGoster(boyut);
            }
        }
        //---------------------------------------------------------------------------------------------------------------------------------------
        else if (boyut == 3) {
            if (ParametreDogruVeriler.get(0).equals(ParametreAlinanVeriler.get(0)) && ParametreDogruVeriler.get(1).equals(ParametreAlinanVeriler.get(1))
                    && ParametreDogruVeriler.get(2).equals(ParametreAlinanVeriler.get(2))) {
                view.startAnimation(ucuncu_hareket_hata);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        HataMesajiGoster(boyut);
                    }
                }, 2200);
            }
            else if (ParametreDogruVeriler.get(0).equals(ParametreAlinanVeriler.get(0)) && ParametreDogruVeriler.get(1).equals(ParametreAlinanVeriler.get(1))) {
                view.startAnimation(ikinci_hareket_hata);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        HataMesajiGoster(boyut);
                    }
                }, 1500);
            }
            else if (ParametreAlinanVeriler.get(0).equals(ParametreDogruVeriler.get(0))) {
                view.startAnimation(birinci_hareket_hata);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        HataMesajiGoster(boyut);
                    }
                }, 1200);

            } else {
                HataMesajiGoster(boyut);
            }
        }

        //---------------------------------------------------------------------------------------------------------------------------------------
        else if(boyut == 4){
            if (ParametreDogruVeriler.get(0).equals(ParametreAlinanVeriler.get(0)) && ParametreDogruVeriler.get(1).equals(ParametreAlinanVeriler.get(1))
                    && ParametreDogruVeriler.get(2).equals(ParametreAlinanVeriler.get(2)) && ParametreDogruVeriler.get(3).equals(ParametreAlinanVeriler.get(3))) {
                view.startAnimation(dorduncu_hareket_hata);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        HataMesajiGoster(boyut);
                    }
                }, 2700);
            }
            else if (ParametreDogruVeriler.get(0).equals(ParametreAlinanVeriler.get(0)) && ParametreDogruVeriler.get(1).equals(ParametreAlinanVeriler.get(1))
                    && ParametreDogruVeriler.get(2).equals(ParametreAlinanVeriler.get(2))) {
                view.startAnimation(ucuncu_hareket_hata);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        HataMesajiGoster(boyut);
                    }
                }, 2200);
            }
            else if (ParametreDogruVeriler.get(0).equals(ParametreAlinanVeriler.get(0)) && ParametreDogruVeriler.get(1).equals(ParametreAlinanVeriler.get(1))) {
                view.startAnimation(ikinci_hareket_hata);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        HataMesajiGoster(boyut);
                    }
                }, 1500);
            }
            else if (ParametreAlinanVeriler.get(0).equals(ParametreDogruVeriler.get(0))) {
                view.startAnimation(birinci_hareket_hata);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        HataMesajiGoster(boyut);
                    }
                }, 1200);
            } else {
                HataMesajiGoster(boyut);
            }
        }

        //---------------------------------------------------------------------------------------------------------------------------------------

        else {
            if (ParametreDogruVeriler.get(0).equals(ParametreAlinanVeriler.get(0)) && ParametreDogruVeriler.get(1).equals(ParametreAlinanVeriler.get(1))
                    && ParametreDogruVeriler.get(2).equals(ParametreAlinanVeriler.get(2)) && ParametreDogruVeriler.get(3).equals(ParametreAlinanVeriler.get(3))
                    && ParametreDogruVeriler.get(4).equals(ParametreAlinanVeriler.get(4))) {
                view.startAnimation(besinci_hareket_bitis);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
                        ad.setMessage("!!! KAZANDINIZ, TEBRİKLER !!!");
                        ad.setPositiveButton("Ana Menüye Dön", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(MainActivity.this,GirisEkrani.class));
                                finish();
                            }
                        });

                        ad.setNegativeButton("Tekrar Oyna", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(MainActivity.this,MainActivity.class));
                                finish();
                            }
                        });
                        ad.create().show();
                    }
                }, 3000);

            }
            else if (ParametreDogruVeriler.get(0).equals(ParametreAlinanVeriler.get(0)) && ParametreDogruVeriler.get(1).equals(ParametreAlinanVeriler.get(1))
                    && ParametreDogruVeriler.get(2).equals(ParametreAlinanVeriler.get(2)) && ParametreDogruVeriler.get(3).equals(ParametreAlinanVeriler.get(3))) {
                view.startAnimation(dorduncu_hareket_hata);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        HataMesajiGoster(boyut);
                    }
                }, 2700);
            }
            else if (ParametreDogruVeriler.get(0).equals(ParametreAlinanVeriler.get(0)) && ParametreDogruVeriler.get(1).equals(ParametreAlinanVeriler.get(1))
                    && ParametreDogruVeriler.get(2).equals(ParametreAlinanVeriler.get(2))) {
                view.startAnimation(ucuncu_hareket_hata);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        HataMesajiGoster(boyut);
                    }
                }, 2200);
            }
            else if (ParametreDogruVeriler.get(0).equals(ParametreAlinanVeriler.get(0)) && ParametreDogruVeriler.get(1).equals(ParametreAlinanVeriler.get(1))) {
                view.startAnimation(ikinci_hareket_hata);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        HataMesajiGoster(boyut);
                    }
                }, 1500);
            }
            else if (ParametreAlinanVeriler.get(0).equals(ParametreDogruVeriler.get(0))) {
                view.startAnimation(birinci_hareket_hata);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        HataMesajiGoster(boyut);
                    }
                }, 1200);
            } else {
                HataMesajiGoster(boyut);
            }
        }

    }



    public void onBackPressed(){
        super.onBackPressed();

        startActivity(new Intent(MainActivity.this, GirisEkrani.class));
    }
}
