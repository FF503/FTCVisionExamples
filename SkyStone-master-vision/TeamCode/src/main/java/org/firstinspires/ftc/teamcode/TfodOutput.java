package org.firstinspires.ftc.teamcode;

public class TfodOutput {
    public int pos;
    public double rot;

    public TfodOutput(){
        this.pos = 0;
        this.rot = 0;
    }

    public TfodOutput(int pos, double rot){
        this.pos = pos;
        this.rot = rot;
    }

    public TfodOutput(TfodOutput tf){
        this.pos = tf.pos;
        this.rot = tf.rot;
    }
}
