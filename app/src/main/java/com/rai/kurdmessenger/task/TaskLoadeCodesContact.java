package com.rai.kurdmessenger.task;

import android.os.AsyncTask;
import com.rai.kurdmessenger.Utils.CodeListUtil;
import com.rai.kurdmessenger.callbacks.CodesContactInterface;
import com.rai.kurdmessenger.recycler.CodeContacModel;

import java.util.ArrayList;
/**
 * Created Created by @rebwar_me.
 */

public class TaskLoadeCodesContact extends AsyncTask<Void, Void, ArrayList<CodeContacModel>> {
    private CodesContactInterface myComponent;
    private String Code;
    public TaskLoadeCodesContact(CodesContactInterface myComponent, String Code) {
        this.myComponent = myComponent;
        this.Code=Code;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<CodeContacModel> doInBackground(Void... params) {

        ArrayList<CodeContacModel> CodeList = CodeListUtil.loadCodeContactList(Code);

        return CodeList;
    }


    @Override
    protected void onPostExecute(ArrayList<CodeContacModel> codeContacModels) {
        if (myComponent != null) {
            myComponent.onCodeContactListLoaded(codeContacModels);
        }
    }
}
