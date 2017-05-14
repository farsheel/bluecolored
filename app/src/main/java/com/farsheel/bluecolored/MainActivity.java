package com.farsheel.bluecolored;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button copy;
    EditText txt,res;
    String[] bhex = new String[]{"\ud83c\udde6", "\ud83c\udde7", "\ud83c\udde8", "\ud83c\udde9", "\ud83c\uddea", "\ud83c\uddeb", "\ud83c\uddec", "\ud83c\udded", "\ud83c\uddee", "\ud83c\uddef", "\ud83c\uddf0", "\ud83c\uddf1", "\ud83c\uddf2", "\ud83c\uddf3", "\ud83c\uddf4", "\ud83c\uddf5", "\ud83c\uddf6", "\ud83c\uddf7", "\ud83c\uddf8", "\ud83c\uddf9", "\ud83c\uddfa", "\ud83c\uddfb", "\ud83c\uddfc", "\ud83c\uddfd", "\ud83c\uddfe", "\ud83c\uddff"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        copy=(Button)findViewById(R.id.button);
        txt=(EditText)findViewById(R.id.editText);
        res=(EditText)findViewById(R.id.editText2);
        txt.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setBlue();
                if (txt.getText().toString().length() == 0) {
                    res.setText(null);

                }
            }

            public void afterTextChanged(Editable s) {
            }
        });
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txt.getText().toString().length() == 0) {
                    Toast.makeText(MainActivity.this, "No Text Copied",Toast.LENGTH_SHORT).show();
                    return;
                }

                setClipboard(getApplicationContext(), res.getText().toString());
                Toast.makeText(MainActivity.this, "Text Copied ", Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void setClipboard(Context context, String s) {
        if (Build.VERSION.SDK_INT < 11) {
            ((ClipboardManager) getSystemService(CLIPBOARD_SERVICE)).setText(s);
        } else {
            ((android.content.ClipboardManager) getSystemService(CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("Copied Text", s));
        }

    }

    private void setBlue() {
        String s = txt.getText().toString();
        char[] c = s.toCharArray();
        String cc = " ";
        int i = 0;
        while (i < s.length()) {
            if (c[i] >= 'a' && c[i] <= 'z') {
                cc = cc + " " + this.bhex[c[i] - 97];
            } else if (c[i] >= 'A' && c[i] <= 'Z') {
                cc = cc + " " + this.bhex[c[i] - 65];
            } else if (c[i] == ' ') {
                cc = cc + " ";
            } else {
                cc = cc + c[i];
            }
            i++;
        }
        res.setText(cc);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_about:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setMessage("Blue Colored is Developed by Farsheel Rahman.It is a Simple App which Converts Normal Texts into Blue Colored Texts.You Can Copy and Paste This Texts in to Anywhere Like Facebook,Whatsapp,..etc");
                builder1.setTitle("About The Developer");
                builder1.setIcon(R.mipmap.ic_launcher);
                builder1.setCancelable(false);
                builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                builder1.create().show();
                return true;
            case R.id.menu_rate:
                String url = "https://play.google.com/store/apps/details?id=com.farsheel.bluecolored";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
