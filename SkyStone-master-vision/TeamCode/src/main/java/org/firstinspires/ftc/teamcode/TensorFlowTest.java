package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TensorFlow Test")
public class TensorFlowTest extends LinearOpMode {
    Camera camera = new Camera(hardwareMap);

    TfodOutput tfodOutput;

    @Override
    public void runOpMode() throws InterruptedException {
        camera.initialize();

        waitForStart();

        sleep(2500);

        telemetry.addLine("starting scanning now");

        telemetry.update();

        tfodOutput = camera.runTfod(isStopRequested());

        telemetry.addData("Pos", tfodOutput.pos);
        telemetry.addData("Rot", tfodOutput.rot);


    }
}
