package com.sel.asset.controller;
import com.sel.asset.model.Asset;
import com.sel.asset.service.IAssetService;
import com.sel.asset.util.ErrorCustomType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by sasisu on 3/7/2018.
 */

@RestController
@RequestMapping("/api")
public class RestApiController {
    public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

    @Autowired
    IAssetService assetService;

    /**
     * Retrieve all the Assets
     * @return on success list of all the assets and the status code
     *
     */


    @RequestMapping(value = "/asset/", method = RequestMethod.GET)
    public ResponseEntity<List<Asset>> listAllAssets() {
        List<Asset> assets = assetService.findAllAssets();
        if (assets.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
        return new ResponseEntity<List<Asset>>(assets, HttpStatus.OK);
    }

    /**
     * Retrieve a single asset
     * @param id
     * @return on the single asset by id
     */


    @RequestMapping(value = "/asset/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAsset(@PathVariable("id") long id) {
        logger.info("Fetching Asset with id {}", id);
        Asset asset = assetService.findById(id);
        if (asset == null) {
            logger.error("Asset with id {} not found.", id);
            return new ResponseEntity(new ErrorCustomType("Asset with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Asset>(asset, HttpStatus.OK);
    }

    /**
     * Create an asset with the payload
      * @param asset
     * @param ucBuilder
     * @return Http Status 201 response on success and header with location as full path of the newly created asset
     */

    @RequestMapping(value = "/asset/", method = RequestMethod.POST)
    public ResponseEntity<?> createAsset(@RequestBody Asset asset, UriComponentsBuilder ucBuilder) {
        logger.info("Creating Asset : {}", asset);

        if (assetService.isAssetExist(asset)) {
            logger.error("Unable to create. An asset with name {} already exist", asset.getAssetName());
            return new ResponseEntity(new ErrorCustomType("Unable to create. An asset with name " +
                    asset.getAssetName() + " already exist."), HttpStatus.CONFLICT);
        }
        assetService.saveAsset(asset);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/asset/{id}").buildAndExpand(asset.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }


    /**
     * Update an asset with the payload.
     * @param id
     * @param asset
     * @return the updated payload with 200 response.
     */

    @RequestMapping(value = "/asset/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateAsset(@PathVariable("id") long id, @RequestBody Asset asset) {
        logger.info("Updating Asset with id {}", id);

        Asset currentAsset = assetService.findById(id);

        if (currentAsset == null) {
            logger.error("Unable to update. Asset with id {} not found.", id);
            return new ResponseEntity(new ErrorCustomType("Unable to update. Asset with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentAsset.setAssetName(asset.getAssetName());
        currentAsset.setAssetType(asset.getAssetType());
        currentAsset.setDetails(asset.getDetails());

        assetService.updateAsset(currentAsset);
        return new ResponseEntity<Asset>(currentAsset, HttpStatus.OK);
    }

    /**
     * Delete an asset with specified id
     * @param id
     * @return 204 no content response.
     */


    @RequestMapping(value = "/asset/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAsset(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting Asset with id {}", id);

        Asset asset = assetService.findById(id);
        if (asset == null) {
            logger.error("Unable to delete. Asset with id {} not found.", id);
            return new ResponseEntity(new ErrorCustomType("Unable to delete. Asset with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        assetService.deleteAssetById(id);
        return new ResponseEntity<Asset>(HttpStatus.NO_CONTENT);
    }


    /**
     * Delete all assets
     * @return return 204 no content response.
     */

    @RequestMapping(value = "/asset/", method = RequestMethod.DELETE)
    public ResponseEntity<Asset> deleteAllAssets() {
        logger.info("Deleting All assets");

        assetService.deleteAllAssets();
        return new ResponseEntity<Asset>(HttpStatus.NO_CONTENT);
    }

}
