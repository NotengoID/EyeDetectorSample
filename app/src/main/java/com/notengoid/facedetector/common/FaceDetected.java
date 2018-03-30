package com.notengoid.facedetector.common;

import org.opencv.core.Point;
import org.opencv.core.Rect;

public class FaceDetected extends Rect{

    public FaceDetected(Rect faceRegion){
        super(faceRegion.x,faceRegion.y,faceRegion.width,faceRegion.height);
    }

    public Rect calculateRightEyeArea(){
        return new Rect(x + width / 16,
                (int) (y + (height / 4.5)),
                (width - 2 * width / 16) / 2, (int) (height / 3.0));
    }

    public Rect calculateLeftEyeArea(){
        return new Rect(x + width / 16 + (width - 2 * width / 16) / 2,
                (int) (y + (height / 4.5)),
                (width - 2 * width / 16) / 2,
                (int) (height / 3.0));
    }

    public Point getCenterPoint(){
        double xCenter = (x + width + x) / 2;
        double yCenter = (y + y + height) / 2;
        return new Point(xCenter, yCenter);
    }
}
