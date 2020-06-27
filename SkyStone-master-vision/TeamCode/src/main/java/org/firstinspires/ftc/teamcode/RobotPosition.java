package org.firstinspires.ftc.teamcode;

public class RobotPosition {

    public double x, y, rot;

    public RobotPosition(){
        this.x = 0;
        this.y = 0;
        this.rot = 0;
    }

    public RobotPosition(double x, double y, double rot){
        this.x = x;
        this.y = y;
        this.rot = rot;

    }

    public RobotPosition(RobotPosition rPos){
        this.x = rPos.x;
        this.y = rPos.y;
        this.rot = rPos.rot;
    }


}
