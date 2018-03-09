package com.sel.asset.service;

import com.sel.asset.model.Asset;

import java.util.List;

/**
 * Created by sasisu on 3/7/2018.
 */
public interface IAssetService {

    Asset findById(long id);

    Asset findByAssetName(String assetName);

    void saveAsset(Asset asset);

    void updateAsset(Asset asset);

    void deleteAssetById(long id);

    List<Asset> findAllAssets();

    void deleteAllAssets();

    boolean isAssetExist(Asset asset);
}
