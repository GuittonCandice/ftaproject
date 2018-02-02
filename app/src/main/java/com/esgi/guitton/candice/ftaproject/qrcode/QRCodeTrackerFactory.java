package com.esgi.guitton.candice.ftaproject.qrcode;

import android.content.Context;

import com.esgi.guitton.candice.ftaproject.qrcode.ui.camera.GraphicOverlay;
import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.barcode.Barcode;

/**
 * Created by d.fernandes-antunes on 29/01/18.
 */

public class QRCodeTrackerFactory implements MultiProcessor.Factory<Barcode> {
    private GraphicOverlay<QRCodeGraphic> mGraphicOverlay;
    private Context mContext;

    public QRCodeTrackerFactory(GraphicOverlay<QRCodeGraphic> mGraphicOverlay,
            Context mContext) {
            this.mGraphicOverlay = mGraphicOverlay;
            this.mContext = mContext;
            }

    @Override
    public Tracker<Barcode> create(Barcode barcode) {
            QRCodeGraphic graphic = new QRCodeGraphic(mGraphicOverlay);
            return new QRCodeGraphicTracker(mGraphicOverlay, graphic, mContext);
            }

        }
