package com.rai.kurdmessenger.task;


import android.os.AsyncTask;

import com.rai.kurdmessenger.Utils.CodeListUtil;
import com.rai.kurdmessenger.callbacks.CodeInterface;
import com.rai.kurdmessenger.recycler.CodeModel;

import java.util.ArrayList;

/**
 * Created by DS on 10/26/2017.
 */

public class TaskLoadeCodes extends AsyncTask<Void, Void, ArrayList<CodeModel>> {

    private CodeInterface myComponent;

    public TaskLoadeCodes(CodeInterface myComponent) {
        this.myComponent = myComponent;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<CodeModel> doInBackground(Void... params) {

        ArrayList<CodeModel> CodeList = CodeListUtil.loadCodeList();

        return CodeList;
    }


    @Override
    protected void onPostExecute(ArrayList<CodeModel> codesDatas) {
        if (myComponent != null) {
            myComponent.onCodeListLoaded(codesDatas);
        }
    }
}
