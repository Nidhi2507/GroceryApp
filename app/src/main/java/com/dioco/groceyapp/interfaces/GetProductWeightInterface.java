package com.dioco.groceyapp.interfaces;

import com.dioco.groceyapp.pojo.ResGetProductList;
import com.dioco.groceyapp.pojo.ResGetProductWeight;

import java.util.List;

public interface GetProductWeightInterface {

    void processFinishGetProductWeight(List<ResGetProductWeight> resGetProductWeightList);
}
