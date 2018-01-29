package com.esgi.guitton.candice.ftaproject.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.esgi.guitton.candice.ftaproject.R;
import com.esgi.guitton.candice.ftaproject.qrcode.QRCodeCaptureActivity;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;


public class QrCodeFragment extends Fragment implements View.OnClickListener {

    private CompoundButton autoFocus;
    private CompoundButton useFlash;
    private TextView statusMessage;
    private TextView barcodeValue;

    private static final int RC_BARCODE_CAPTURE = 9001;
    private static final String TAG = "BarcodeMain";

    public QrCodeFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static QrCodeFragment newInstance(String param1, String param2) {
        QrCodeFragment fragment = new QrCodeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qr_code, container, false);

        statusMessage = view.findViewById(R.id.status_message);
        barcodeValue = view.findViewById(R.id.barcode_value);

        autoFocus = view.findViewById(R.id.auto_focus);
        useFlash = view.findViewById(R.id.use_flash);

        view.findViewById(R.id.read_barcode).setOnClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.read_barcode) {
            Intent intent = new Intent(this.getContext(), QRCodeCaptureActivity.class);
            intent.putExtra(QRCodeCaptureActivity.AutoFocus, autoFocus.isChecked());
            intent.putExtra(QRCodeCaptureActivity.UseFlash, useFlash.isChecked());

            startActivityForResult(intent, RC_BARCODE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_BARCODE_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(QRCodeCaptureActivity.BarcodeObject);
                    statusMessage.setText("Le QRCode a bien été lu !");
                    barcodeValue.setText(barcode.displayValue);
                    Log.d(TAG, "Barcode read: " + barcode.displayValue);
                } else {
                    statusMessage.setText("Le QR Code n'a pas pu être lu !");
                    Log.d(TAG, "No barcode captured, intent data is null");
                }
            } else {
                statusMessage.setText("Erreur de lecture...");
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
