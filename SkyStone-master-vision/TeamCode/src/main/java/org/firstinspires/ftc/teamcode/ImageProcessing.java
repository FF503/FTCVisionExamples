package org.firstinspires.ftc.teamcode;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class ImageProcessing {

    private int colorTolerance = 10;

    public int stonePosition(Mat input){

        Mat output = process(input);

        int left = getValueBySide(output, true);
        int right = getValueBySide(output, false);

        int stonePos = 5;

        if(Math.abs(left - right) < colorTolerance){
            stonePos = 6;

        }else if(right > left){
            stonePos = 4;

        }

        return stonePos;


    }

    private Mat process(Mat input){
        Mat blurOutput = new Mat();
        Mat output = new Mat();

        blur(input, BlurType.GAUSSIAN, 25, blurOutput);
        desaturate(blurOutput, output);

        return output;
    }

    private int getValueBySide(Mat input, boolean left){
        int imageWidth = input.width();
        int imageHeight = input.height();
        double sum = 0;
        int offset = left ? 0: imageWidth/2;
        int count = 0;

        for(int rows = 0; rows < imageHeight; rows += 5){
            for(int cols = offset; cols < ((imageWidth/2) + offset); cols += 5){
                sum += input.get(rows,cols)[0];
                count ++;
            }
        }

        return (int)(sum/count);

    }





    /**
     * An indication of which type of filter to use for a blur.
     * Choices are BOX, GAUSSIAN, MEDIAN, and BILATERAL
     */
    enum BlurType{
        BOX("Box Blur"), GAUSSIAN("Gaussian Blur"), MEDIAN("Median Filter"),
        BILATERAL("Bilateral Filter");

        private final String label;

        BlurType(String label) {
            this.label = label;
        }

        public static BlurType get(String type) {
            if (BILATERAL.label.equals(type)) {
                return BILATERAL;
            }
            else if (GAUSSIAN.label.equals(type)) {
                return GAUSSIAN;
            }
            else if (MEDIAN.label.equals(type)) {
                return MEDIAN;
            }
            else {
                return BOX;
            }
        }

        @Override
        public String toString() {
            return this.label;
        }
    }

    /**
     * Softens an image using one of several filters.
     * @param input The image on which to perform the blur.
     * @param type The blurType to perform.
     * @param doubleRadius The radius for the blur.
     * @param output The image in which to store the output.
     */
    private void blur(Mat input, BlurType type, double doubleRadius,
                      Mat output) {
        int radius = (int)(doubleRadius + 0.5);
        int kernelSize;
        switch(type){
            case BOX:
                kernelSize = 2 * radius + 1;
                Imgproc.blur(input, output, new Size(kernelSize, kernelSize));
                break;
            case GAUSSIAN:
                kernelSize = 6 * radius + 1;
                Imgproc.GaussianBlur(input,output, new Size(kernelSize, kernelSize), radius);
                break;
            case MEDIAN:
                kernelSize = 2 * radius + 1;
                Imgproc.medianBlur(input, output, kernelSize);
                break;
            case BILATERAL:
                Imgproc.bilateralFilter(input, output, -1, radius, radius);
                break;
        }
    }

    /**
     * Converts a color image into shades of grey.
     * @param input The image on which to perform the desaturate.
     * @param output The image in which to store the output.
     */
    private void desaturate(Mat input, Mat output) {
        switch (input.channels()) {
            case 1:
                // If the input is already one channel, it's already desaturated
                input.copyTo(output);
                break;
            case 3:
                Imgproc.cvtColor(input, output, Imgproc.COLOR_BGR2GRAY);
                break;
            case 4:
                Imgproc.cvtColor(input, output, Imgproc.COLOR_BGRA2GRAY);
                break;
            default:
                throw new IllegalArgumentException("Input to desaturate must have 1, 3, or 4 channels");
        }
    }




}
