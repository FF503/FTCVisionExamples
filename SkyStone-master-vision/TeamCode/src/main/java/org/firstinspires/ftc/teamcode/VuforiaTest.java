package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name="Vuforia Test")
public class VuforiaTest extends LinearOpMode {

    Camera camera = null;

    RobotPosition robotPosition;

    @Override
    public void runOpMode() throws InterruptedException {
        camera = new Camera(hardwareMap);
        camera.initialize();
        camera.initTrackables();

        waitForStart();

        sleep(2500);

        telemetry.addLine("starting scanning now");

        telemetry.update();

        robotPosition = camera.getCurrentPosition(isStopRequested());

        telemetry.addData("X", robotPosition.x);
        telemetry.addData("Y", robotPosition.y);
        telemetry.addData("Rot", robotPosition.rot);


    }
}
