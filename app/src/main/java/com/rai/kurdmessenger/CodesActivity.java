package com.rai.kurdmessenger;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.rai.kurdmessenger.app.MyApplication;
import com.rai.kurdmessenger.callbacks.CodeInterface;
import com.rai.kurdmessenger.databinding.ActivityCodesBinding;
import com.rai.kurdmessenger.log.L;
import com.rai.kurdmessenger.recycler.CodeListAdapter;
import com.rai.kurdmessenger.recycler.CodeModel;
import com.rai.kurdmessenger.task.TaskLoadeCodes;

import java.util.ArrayList;
/**
 * Created Created by @rebwar_me.
 */

public class CodesActivity extends AppCompatActivity implements CodeInterface {

    private AppBarConfiguration appBarConfiguration;
    private ActivityCodesBinding binding;
    private RecyclerView rvcods;
    private CodeListAdapter codesAdapter;
    public ProgressDialog prDialog=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCodesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);


        getSupportActionBar().setTitle("کد ها ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Bundle b=getIntent().getExtras();
        String newCode= b.getString("code");

        if(!newCode.equals("0")) {
            L.t(CodesActivity.this, "" + newCode);
            MyApplication.getWritableDatabase().setTableOfCode(0,newCode);
        }
        rvcods=(RecyclerView) findViewById(R.id.rvcods);
        rvcods.setLayoutManager(new LinearLayoutManager(CodesActivity.this));
        codesAdapter=new CodeListAdapter(CodesActivity.this);
        rvcods.setAdapter(codesAdapter);
        prdialog();
        new TaskLoadeCodes(CodesActivity.this).execute();
    }


    public void prdialog(){
        prDialog=new ProgressDialog(CodesActivity.this,R.style.MyD);
        prDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        prDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        prDialog.setMessage("Loading...");
        prDialog.setCancelable(false);
        prDialog.show();

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MyApplication.getWritableDatabase().updateRmzf("2");
        startActivity(new Intent(CodesActivity.this,MainActivity.class));
        CodesActivity.this.finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
//       super.onSupportNavigateUp();
        onBackPressed();
        return true;
    }

    @Override
    public void onCodeListLoaded(ArrayList<CodeModel> codeModels) {
        if(!(codeModels.isEmpty())) {
            codesAdapter.setCodeList(codeModels);
        }
        prDialog.dismiss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_codes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.Createcode) {
            startActivity(new Intent(CodesActivity.this,CreateCodeActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}