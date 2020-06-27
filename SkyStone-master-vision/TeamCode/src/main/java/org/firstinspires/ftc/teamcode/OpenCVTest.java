package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.opencv.core.Mat;


@TeleOp(name="OpenCV Test")
public class OpenCVTest extends LinearOpMode {

    Camera camera = null;
    ImageProcessing imageProcessing = new ImageProcessing();

    @Override
    public void runOpMode() throws InterruptedException {
        camera = new Camera(hardwareMap);
        camera.initialize();

        waitForStart();

        sleep(2500);

        telemetry.addLine("starting scanning now");

        telemetry.update();

        Mat takenPhoto = camera.getMat();

        int stonePos = imageProcessing.stonePosition(takenPhoto);

        telemetry.addData("Stone Position", stonePos);

    }
}
