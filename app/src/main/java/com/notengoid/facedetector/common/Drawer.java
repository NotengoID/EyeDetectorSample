package com.notengoid.facedetector.common;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;

public class Drawer {
    private static final Scalar COLOR_GREEN = new Scalar(0, 255, 0, 255);
    private static final Scalar COLOR_RED = new Scalar(255, 0, 0, 255);
    private static final Scalar COLOR_WHITE = new Scalar(255, 255, 255, 255);
    private static final Scalar COLOR_YELLOW = new Scalar(255, 255, 0,255);

    Mat draw;

    public Drawer(){
        this.draw = new Mat();
    }

    public void setDraw(Mat draw){
        this.draw = draw;
    }

    public Mat getDraw(){
        return draw;
    }

    public void release (){
        this.draw.release();
    }

    public void drawFace(FaceDetected face){
        Core.rectangle(draw, face.tl(), face.br(), COLOR_GREEN, 3);
        Point center = face.getCenterPoint();

        Core.circle(draw, center, 10, COLOR_RED, 3);

        Core.putText(draw, "[" + center.x + "," + center.y + "]",
                new Point(center.x + 20, center.y + 20),
                Core.FONT_HERSHEY_SIMPLEX,
                0.7,
                COLOR_WHITE);
    }
    //draweye Window
    public void drawEye(Rect eye){
        Core.rectangle(draw, eye.tl(), eye.br(), COLOR_RED, 2);
    }

    public void drawIris(Point tl, Point br){
        Core.rectangle(draw, tl, br, COLOR_YELLOW);
    }

    public Mat submat(Rect rect){
        return draw.submat(rect);
    }

    public Mat submat(int rowStart, int rowEnd, int colStart, int colEnd){
        return draw.submat(rowStart, rowEnd, colStart, colEnd);
    }

}
