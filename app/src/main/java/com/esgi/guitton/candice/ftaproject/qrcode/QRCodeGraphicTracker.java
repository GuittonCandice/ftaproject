package com.esgi.guitton.candice.ftaproject.qrcode;

import android.content.Context;
import android.support.annotation.UiThread;

import com.esgi.guitton.candice.ftaproject.qrcode.ui.camera.GraphicOverlay;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.barcode.Barcode;

/**
 * Created by d.fernandes-antunes on 29/01/18.
 */

public class QRCodeGraphicTracker extends Tracker<Barcode> {
    private GraphicOverlay<QRCodeGraphic> mOverlay;
    private QRCodeGraphic mGraphic;

    private BarcodeUpdateListener mBarcodeUpdateListener;

    /**
     * Consume the item instance detected from an Activity or Fragment level by implementing the
     * BarcodeUpdateListener interface method onBarcodeDetected.
     */
    public interface BarcodeUpdateListener {
        @UiThread
        void onBarcodeDetected(Barcode barcode);
    }

    QRCodeGraphicTracker(GraphicOverlay<QRCodeGraphic> mOverlay, QRCodeGraphic mGraphic,
                          Context context) {
        this.mOverlay = mOverlay;
        this.mGraphic = mGraphic;
        if (context instanceof BarcodeUpdateListener) {
            this.mBarcodeUpdateListener = (BarcodeUpdateListener) context;
        } else {
            throw new RuntimeException("Hosting activity must implement BarcodeUpdateListener");
        }
    }

    /**
     * Start tracking the detected item instance within the item overlay.
     */
    @Override
    public void onNewItem(int id, Barcode item) {
        mGraphic.setId(id);
        mBarcodeUpdateListener.onBarcodeDetected(item);
    }

    /**
     * Update the position/characteristics of the item within the overlay.
     */
    @Override
    public void onUpdate(Detector.Detections<Barcode> detectionResults, Barcode item) {
        mOverlay.add(mGraphic);
        mGraphic.updateItem(item);
    }

    /**
     * Hide the graphic when the corresponding object was not detected.  This can happen for
     * intermediate frames temporarily, for example if the object was momentarily blocked from
     * view.
     */
    @Override
    public void onMissing(Detector.Detections<Barcode> detectionResults) {
        mOverlay.remove(mGraphic);
    }

    /**
     * Called when the item is assumed to be gone for good. Remove the graphic annotation from
     * the overlay.
     */
    @Override
    public void onDone() {
        mOverlay.remove(mGraphic);
    }
}
