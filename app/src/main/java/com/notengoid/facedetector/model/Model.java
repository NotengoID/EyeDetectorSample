package com.notengoid.facedetector.model;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import org.opencv.objdetect.CascadeClassifier;
import org.opencv.samples.facedetect.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Model {
    private static final String TAG = "FaceDetection::Model";
    private Activity activity;

    public Model(Activity activity){
        this.activity = activity;
    }

    public CascadeClassifier getCascadeClassifier(){
        CascadeClassifier classifier = null;
        try{
            InputStream is = activity.getResources().openRawResource(R.raw.lbpcascade_frontalface);
            File cascadeDir = activity.getDir("cascade", Context.MODE_PRIVATE);
            File mCascadeFile = new File(cascadeDir,"lbpcascade_frontalface.xml");
            FileOutputStream os = new FileOutputStream(mCascadeFile);

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            is.close();
            os.close();

            classifier = new CascadeClassifier( mCascadeFile.getAbsolutePath());
            if (classifier.empty()) {
                Log.e(TAG, "Failed to load cascade classifier");
                classifier = null;
            } else{
                Log.i(TAG, "Loaded cascade classifier from " + mCascadeFile.getAbsolutePath());
            }
            cascadeDir.delete();
        } catch (IOException e){
            e.printStackTrace();
        }

        return classifier;
    }

    public CascadeClassifier getCascadeEyeClassifier(){
        CascadeClassifier classifier = null;
        try{
            InputStream iser = activity.getResources().openRawResource(R.raw.haarcascade_lefteye_2splits);
            File cascadeDirER = activity.getDir("cascadeER", Context.MODE_PRIVATE);
            File cascadeFileER = new File(cascadeDirER,"haarcascade_eye_right.xml");
            FileOutputStream oser = new FileOutputStream(cascadeFileER);

            byte[] bufferER = new byte[4096];
            int bytesReadER;
            while ((bytesReadER = iser.read(bufferER)) != -1) {
                oser.write(bufferER, 0, bytesReadER);
            }
            iser.close();
            oser.close();

            classifier = new CascadeClassifier(cascadeFileER.getAbsolutePath());
            if (classifier.empty()) {
                Log.e(TAG, "Failed to load cascade classifier");
                classifier = null;
            } else{
                Log.i(TAG, "Loaded cascade classifier from " + cascadeFileER.getAbsolutePath());
            }
            cascadeFileER.delete();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "Failed to load cascade. Exception thrown: " + e);
        }

        return classifier;
    }
}
