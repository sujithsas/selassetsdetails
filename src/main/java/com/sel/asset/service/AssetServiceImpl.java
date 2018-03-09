package com.sel.asset.service;

import com.sel.asset.model.Asset;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by sasisu on 3/7/2018.
 */

@Service("assetService")
public class AssetServiceImpl implements IAssetService {

    private static final AtomicLong counter = new AtomicLong();
    private static List<Asset> assets;

    static {
        assets = populateDummyAssets();
    }

    public List<Asset> findAllAssets() {
        return assets;
    }

    public Asset findById(long id) {
        for (Asset asset : assets) {
            if (asset.getId() == id) {
                return asset;
            }
        }
        return null;
    }

    public Asset findByAssetName(String name) {
        for (Asset asset : assets) {
            if (asset.getAssetName().equalsIgnoreCase(name)) {
                return asset;
            }
        }
        return null;
    }

    public void saveAsset(Asset asset) {
        asset.setId(counter.incrementAndGet());
        assets.add(asset);
    }

    public void updateAsset(Asset asset) {
        int index = assets.indexOf(asset);
        assets.set(index, asset);
    }

    public void deleteAssetById(long id) {

        for (Iterator<Asset> iterator = assets.iterator(); iterator.hasNext(); ) {
            Asset asset = iterator.next();
            if (asset.getId() == id) {
                iterator.remove();
            }
        }
    }

    public boolean isAssetExist(Asset asset) {
        return findByAssetName(asset.getAssetName()) != null;
    }

    public void deleteAllAssets() {
        assets.clear();
    }

    /**
     * This method is created only for my testing to simulate the data.
     * In real environment,this should be coming from database.
     * @return assets
     */

    private static List<Asset> populateDummyAssets() {
        List<Asset> assets = new ArrayList<Asset>();
        assets.add(new Asset(counter.incrementAndGet(), "SEL-5045 acSELerator Team Software", "Collection", "Improve productivity, manage device data, and collect data quickly and efficiently with Team Software."));
        assets.add(new Asset(counter.incrementAndGet(), "SEL-2431 Voltage Regulator Control", "Distribution Control", "The SEL-2431 is compatible with most 32-step, single-phase voltage regulators manufactured in North America."));
        assets.add(new Asset(counter.incrementAndGet(), "SEL-5630 acSELerator Meter Reports", "Metering", "SEL-5630 Meter Reports enhances the visualization and analysis of data captured by SEL meters in your system"));
        assets.add(new Asset(counter.incrementAndGet(), "SEL-5630 acSELerator Meter Reports", "Metering", "SEL-5630 Meter Reports enhances the visualization and analysis of data captured by SEL meters in your system"));
        assets.add(new Asset(counter.incrementAndGet(), "SEL-2505 Remote I/O Module", "Remote I/O", "The SEL-2440 DPAC is a 48-point discrete programmable automation controller that is ideal for utility and industrial applications as a rugged and reliable I/O solution at an economical price."));
        assets.add(new Asset(counter.incrementAndGet(), "SEL-2600 RTD Module", "Protection Relay", "Measure and transmit data from up to 12 RTDs and a single contact status located in transformers, breakers, motors, generators, or other system apparatus."));
        return assets;
    }
}






