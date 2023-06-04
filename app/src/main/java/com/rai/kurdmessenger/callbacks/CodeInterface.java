package com.rai.kurdmessenger.callbacks;

import com.rai.kurdmessenger.recycler.CodeModel;

import java.util.ArrayList;

public interface CodeInterface {
    void onCodeListLoaded(ArrayList<CodeModel> codeModels);
}
