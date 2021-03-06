package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import static org.firstinspires.ftc.robotcontroller.internal.FieldPositionProgram.BalancingStone;

//back motor directions, 1 is reversed, -1 is how it used to be (before switching connectors)
@Autonomous
@Disabled
public class FullAutoNoVuforia extends LinearOpMode {
    DcMotor left;
    DcMotor right;
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor armUp;
    Servo servo;
    Servo servo2;
    Servo servoJewel;
    ColorSensor color;

    double servoClosePos;
    double servo2ClosePos;
    double servoOpenPos;
    double servo2OpenPos;
    float colorVal[];
    int backMotorDirections;

    @Override
    public void runOpMode() {
        left = hardwareMap.get(DcMotor.class, "left");
        right = hardwareMap.get(DcMotor.class, "right");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        armUp = hardwareMap.get(DcMotor.class, "armUp");
        servo = hardwareMap.get(Servo.class, "servo");
        servo2 = hardwareMap.get(Servo.class, "servo2");
        servoJewel = hardwareMap.get(Servo.class, "servoJewel");
        color = hardwareMap.get(ColorSensor.class, "color");

        servoClosePos = 0.15;
        servo2ClosePos = 0.85;
        servoOpenPos = 0.5;
        servo2OpenPos = 0.5;
        backMotorDirections = 1;

        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armUp.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armUp.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        servo.setPosition(servoClosePos);
        servo2.setPosition(servo2ClosePos);
        servoJewel.setPosition(1);

        color.enableLed(true);

        colorVal = new float[10];

        telemetry.addData("status", "initialized");
        telemetry.update();

        waitForStart();

        left.setPower(0.2);
        right.setPower(0.2);
        frontLeft.setPower(0.2);
        frontRight.setPower(0.2);
        armUp.setPower(0.4);

        armUp.setTargetPosition(armUp.getCurrentPosition() - 1000);

        while(opModeIsActive() && armUp.isBusy()){
            telemetry.addData("arm up position", armUp.getCurrentPosition());
            telemetry.update();
        }

        if (BalancingStone == 4){

            servoJewel.setPosition(0.4);

            sleep(1000);

            Color.RGBToHSV(color.red() * 8, color.green() * 8, color.blue() * 8, colorVal);

            if (colorVal[0] <= 20 || colorVal[0] >= 340){
                telemetry.addData("color", "red");
                telemetry.update();

                left.setTargetPosition(left.getCurrentPosition() - 305 * backMotorDirections);
                right.setTargetPosition(right.getCurrentPosition() - 305 * backMotorDirections);
                frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + 360);
                frontRight.setTargetPosition(frontRight.getCurrentPosition() + 360);

                while (opModeIsActive() && (left.isBusy() && right.isBusy() && frontLeft.isBusy() && frontRight.isBusy())) {
                    telemetry.addData("back right current position", right.getCurrentPosition());
                    telemetry.addData("back left current position", left.getCurrentPosition());
                    telemetry.addData("front right current position", frontRight.getCurrentPosition());
                    telemetry.addData("front left current position", frontLeft.getCurrentPosition());
                    telemetry.update();
                }

                left.setTargetPosition(left.getCurrentPosition() + 305 * backMotorDirections);
                right.setTargetPosition(right.getCurrentPosition() + 305 * backMotorDirections);
                frontLeft.setTargetPosition(frontLeft.getCurrentPosition() - 360);
                frontRight.setTargetPosition(frontRight.getCurrentPosition() - 360);

                while (opModeIsActive() && (left.isBusy() && right.isBusy() && frontLeft.isBusy() && frontRight.isBusy())) {
                    telemetry.addData("back right current position", right.getCurrentPosition());
                    telemetry.addData("back left current position", left.getCurrentPosition());
                    telemetry.addData("front right current position", frontRight.getCurrentPosition());
                    telemetry.addData("front left current position", frontLeft.getCurrentPosition());
                    telemetry.update();
                }

                servoJewel.setPosition(1);

            } else if (colorVal[0] >= 220 && colorVal[0] <= 260){
                telemetry.addData("color", "blue");
                telemetry.update();

                left.setTargetPosition(left.getCurrentPosition() + 305 * backMotorDirections);
                right.setTargetPosition(right.getCurrentPosition() + 305 * backMotorDirections);
                frontLeft.setTargetPosition(frontLeft.getCurrentPosition() - 360);
                frontRight.setTargetPosition(frontRight.getCurrentPosition() - 360);

                while (opModeIsActive() && (left.isBusy() && right.isBusy() && frontLeft.isBusy() && frontRight.isBusy())) {
                    telemetry.addData("back right current position", right.getCurrentPosition());
                    telemetry.addData("back left current position", left.getCurrentPosition());
                    telemetry.addData("front right current position", frontRight.getCurrentPosition());
                    telemetry.addData("front left current position", frontLeft.getCurrentPosition());
                    telemetry.update();
                }

                left.setTargetPosition(left.getCurrentPosition() - 305 * backMotorDirections);
                right.setTargetPosition(right.getCurrentPosition() - 305 * backMotorDirections);
                frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + 360);
                frontRight.setTargetPosition(frontRight.getCurrentPosition() + 360);

                while (opModeIsActive() && (left.isBusy() && right.isBusy() && frontLeft.isBusy() && frontRight.isBusy())) {
                    telemetry.addData("back right current position", right.getCurrentPosition());
                    telemetry.addData("back left current position", left.getCurrentPosition());
                    telemetry.addData("front right current position", frontRight.getCurrentPosition());
                    telemetry.addData("front left current position", frontLeft.getCurrentPosition());
                    telemetry.update();
                }

                servoJewel.setPosition(1);

            } else{
                telemetry.addData("color", "unknown");
                telemetry.update();

                servoJewel.setPosition(1);

                sleep(500);
            }

            left.setTargetPosition(left.getCurrentPosition() - 1701 * backMotorDirections);
            right.setTargetPosition(right.getCurrentPosition() + 1701 * backMotorDirections);
            frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + 1997);
            frontRight.setTargetPosition(frontRight.getCurrentPosition() - 1997);

            while (opModeIsActive() && (left.isBusy() && right.isBusy() && frontLeft.isBusy() && frontRight.isBusy())) {
                telemetry.addData("back right current position", right.getCurrentPosition());
                telemetry.addData("back left current position", left.getCurrentPosition());
                telemetry.addData("front right current position", frontRight.getCurrentPosition());
                telemetry.addData("front left current position", frontLeft.getCurrentPosition());
                telemetry.update();
            }

            left.setPower(0.4);
            right.setPower(0.4);
            frontLeft.setPower(0.4);
            frontRight.setPower(0.4);

            left.setTargetPosition(left.getCurrentPosition() - 669 * backMotorDirections);
            right.setTargetPosition(right.getCurrentPosition() - 669 * backMotorDirections);
            frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + 790);
            frontRight.setTargetPosition(frontRight.getCurrentPosition() + 790);

            while (opModeIsActive() && (left.isBusy() && right.isBusy() && frontLeft.isBusy() && frontRight.isBusy())) {
                telemetry.addData("back right current position", right.getCurrentPosition());
                telemetry.addData("back left current position", left.getCurrentPosition());
                telemetry.addData("front right current position", frontRight.getCurrentPosition());
                telemetry.addData("front left current position", frontLeft.getCurrentPosition());
                telemetry.update();
            }

            left.setPower(0.2);
            right.setPower(0.2);
            frontLeft.setPower(0.2);
            frontRight.setPower(0.2);

            left.setTargetPosition(left.getCurrentPosition() - 534 * backMotorDirections);
            right.setTargetPosition(right.getCurrentPosition() + 534 * backMotorDirections);
            frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + 630);
            frontRight.setTargetPosition(frontRight.getCurrentPosition() - 630);

            while (opModeIsActive() && (left.isBusy() && right.isBusy() && frontLeft.isBusy() && frontRight.isBusy())) {
                telemetry.addData("back right current position", right.getCurrentPosition());
                telemetry.addData("back left current position", left.getCurrentPosition());
                telemetry.addData("front right current position", frontRight.getCurrentPosition());
                telemetry.addData("front left current position", frontLeft.getCurrentPosition());
                telemetry.update();
            }

            servo.setPosition(servoOpenPos);
            servo2.setPosition(servo2OpenPos);

            left.setTargetPosition(left.getCurrentPosition() + 340 * backMotorDirections);
            right.setTargetPosition(right.getCurrentPosition() - 340 * backMotorDirections);
            frontLeft.setTargetPosition(frontLeft.getCurrentPosition() - 401);
            frontRight.setTargetPosition(frontRight.getCurrentPosition() + 401);

            while (opModeIsActive() && (left.isBusy() && right.isBusy() && frontLeft.isBusy() && frontRight.isBusy())) {
                telemetry.addData("back right current position", right.getCurrentPosition());
                telemetry.addData("back left current position", left.getCurrentPosition());
                telemetry.addData("front right current position", frontRight.getCurrentPosition());
                telemetry.addData("front left current position", frontLeft.getCurrentPosition());
                telemetry.update();
            }

            requestOpModeStop();

        } else if (BalancingStone == 1){
            servoJewel.setPosition(0.4);

            sleep(1000);

            Color.RGBToHSV(color.red() * 8, color.green() * 8, color.blue() * 8, colorVal);

            if (colorVal[0] >= 220 && colorVal[0] <= 260){
                telemetry.addData("color", "blue");
                telemetry.update();

                left.setTargetPosition(left.getCurrentPosition() - 305 * backMotorDirections);
                right.setTargetPosition(right.getCurrentPosition() - 305 * backMotorDirections);
                frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + 360);
                frontRight.setTargetPosition(frontRight.getCurrentPosition() + 360);

                while (opModeIsActive() && (left.isBusy() && right.isBusy() && frontLeft.isBusy() && frontRight.isBusy())) {
                    telemetry.addData("back right current position", right.getCurrentPosition());
                    telemetry.addData("back left current position", left.getCurrentPosition());
                    telemetry.addData("front right current position", frontRight.getCurrentPosition());
                    telemetry.addData("front left current position", frontLeft.getCurrentPosition());
                    telemetry.update();
                }

                left.setTargetPosition(left.getCurrentPosition() + 305 * backMotorDirections);
                right.setTargetPosition(right.getCurrentPosition() + 305 * backMotorDirections);
                frontLeft.setTargetPosition(frontLeft.getCurrentPosition() - 360);
                frontRight.setTargetPosition(frontRight.getCurrentPosition() - 360);

                while (opModeIsActive() && (left.isBusy() && right.isBusy() && frontLeft.isBusy() && frontRight.isBusy())) {
                    telemetry.addData("back right current position", right.getCurrentPosition());
                    telemetry.addData("back left current position", left.getCurrentPosition());
                    telemetry.addData("front right current position", frontRight.getCurrentPosition());
                    telemetry.addData("front left current position", frontLeft.getCurrentPosition());
                    telemetry.update();
                }

                servoJewel.setPosition(1);

            } else if (colorVal[0] <= 20 || colorVal[0] >= 340){
                telemetry.addData("color", "red");
                telemetry.update();

                left.setTargetPosition(left.getCurrentPosition() + 305 * backMotorDirections);
                right.setTargetPosition(right.getCurrentPosition() + 305 * backMotorDirections);
                frontLeft.setTargetPosition(frontLeft.getCurrentPosition() - 360);
                frontRight.setTargetPosition(frontRight.getCurrentPosition() - 360);

                while (opModeIsActive() && (left.isBusy() && right.isBusy() && frontLeft.isBusy() && frontRight.isBusy())) {
                    telemetry.addData("back right current position", right.getCurrentPosition());
                    telemetry.addData("back left current position", left.getCurrentPosition());
                    telemetry.addData("front right current position", frontRight.getCurrentPosition());
                    telemetry.addData("front left current position", frontLeft.getCurrentPosition());
                    telemetry.update();
                }

                left.setTargetPosition(left.getCurrentPosition() - 305 * backMotorDirections);
                right.setTargetPosition(right.getCurrentPosition() - 305 * backMotorDirections);
                frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + 360);
                frontRight.setTargetPosition(frontRight.getCurrentPosition() + 360);

                while (opModeIsActive() && (left.isBusy() && right.isBusy() && frontLeft.isBusy() && frontRight.isBusy())) {
                    telemetry.addData("back right current position", right.getCurrentPosition());
                    telemetry.addData("back left current position", left.getCurrentPosition());
                    telemetry.addData("front right current position", frontRight.getCurrentPosition());
                    telemetry.addData("front left current position", frontLeft.getCurrentPosition());
                    telemetry.update();
                }

                servoJewel.setPosition(1);

            } else{
                telemetry.addData("color", "unknown");
                telemetry.update();

                servoJewel.setPosition(1);

                sleep(500);
            }

            left.setTargetPosition(left.getCurrentPosition() - 1701 * backMotorDirections);
            right.setTargetPosition(right.getCurrentPosition() + 1701 * backMotorDirections);
            frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + 1997);
            frontRight.setTargetPosition(frontRight.getCurrentPosition() - 1997);

            while (opModeIsActive() && (left.isBusy() && right.isBusy() && frontLeft.isBusy() && frontRight.isBusy())) {
                telemetry.addData("back right current position", right.getCurrentPosition());
                telemetry.addData("back left current position", left.getCurrentPosition());
                telemetry.addData("front right current position", frontRight.getCurrentPosition());
                telemetry.addData("front left current position", frontLeft.getCurrentPosition());
                telemetry.update();
            }

            left.setTargetPosition(left.getCurrentPosition() - 669 * backMotorDirections);
            right.setTargetPosition(right.getCurrentPosition() - 669 * backMotorDirections);
            frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + 790);
            frontRight.setTargetPosition(frontRight.getCurrentPosition() + 790);

            while (opModeIsActive() && (left.isBusy() && right.isBusy() && frontLeft.isBusy() && frontRight.isBusy())) {
                telemetry.addData("back right current position", right.getCurrentPosition());
                telemetry.addData("back left current position", left.getCurrentPosition());
                telemetry.addData("front right current position", frontRight.getCurrentPosition());
                telemetry.addData("front left current position", frontLeft.getCurrentPosition());
                telemetry.update();
            }

            left.setTargetPosition(left.getCurrentPosition() - 534 * backMotorDirections);
            right.setTargetPosition(right.getCurrentPosition() + 534 * backMotorDirections);
            frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + 630);
            frontRight.setTargetPosition(frontRight.getCurrentPosition() - 630);

            while (opModeIsActive() && (left.isBusy() && right.isBusy() && frontLeft.isBusy() && frontRight.isBusy())) {
                telemetry.addData("back right current position", right.getCurrentPosition());
                telemetry.addData("back left current position", left.getCurrentPosition());
                telemetry.addData("front right current position", frontRight.getCurrentPosition());
                telemetry.addData("front left current position", frontLeft.getCurrentPosition());
                telemetry.update();
            }

            servo.setPosition(servoOpenPos);
            servo2.setPosition(servo2OpenPos);

            left.setTargetPosition(left.getCurrentPosition() + 340 * backMotorDirections);
            right.setTargetPosition(right.getCurrentPosition() - 340 * backMotorDirections);
            frontLeft.setTargetPosition(frontLeft.getCurrentPosition() - 401);
            frontRight.setTargetPosition(frontRight.getCurrentPosition() + 401);

            while (opModeIsActive() && (left.isBusy() && right.isBusy() && frontLeft.isBusy() && frontRight.isBusy())) {
                telemetry.addData("back right current position", right.getCurrentPosition());
                telemetry.addData("back left current position", left.getCurrentPosition());
                telemetry.addData("front right current position", frontRight.getCurrentPosition());
                telemetry.addData("front left current position", frontLeft.getCurrentPosition());
                telemetry.update();
            }

            requestOpModeStop();

        } else if (BalancingStone == 3){
            servoJewel.setPosition(0.4);

            sleep(1000);

            Color.RGBToHSV(color.red() * 8, color.green() * 8, color.blue() * 8, colorVal);

            if (colorVal[0] >= 220 && colorVal[0] <= 260){
                telemetry.addData("color", "blue");
                telemetry.update();

                left.setTargetPosition(left.getCurrentPosition() - 305 * backMotorDirections);
                right.setTargetPosition(right.getCurrentPosition() - 305 * backMotorDirections);
                frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + 360);
                frontRight.setTargetPosition(frontRight.getCurrentPosition() + 360);

                while (opModeIsActive() && (left.isBusy() && right.isBusy() && frontLeft.isBusy() && frontRight.isBusy())) {
                    telemetry.addData("back right current position", right.getCurrentPosition());
                    telemetry.addData("back left current position", left.getCurrentPosition());
                    telemetry.addData("front right current position", frontRight.getCurrentPosition());
                    telemetry.addData("front left current position", frontLeft.getCurrentPosition());
                    telemetry.update();
                }

                left.setTargetPosition(left.getCurrentPosition() + 305 * backMotorDirections);
                right.setTargetPosition(right.getCurrentPosition() + 305 * backMotorDirections);
                frontLeft.setTargetPosition(frontLeft.getCurrentPosition() - 360);
                frontRight.setTargetPosition(frontRight.getCurrentPosition() - 360);

                while (opModeIsActive() && (left.isBusy() && right.isBusy() && frontLeft.isBusy() && frontRight.isBusy())) {
                    telemetry.addData("back right current position", right.getCurrentPosition());
                    telemetry.addData("back left current position", left.getCurrentPosition());
                    telemetry.addData("front right current position", frontRight.getCurrentPosition());
                    telemetry.addData("front left current position", frontLeft.getCurrentPosition());
                    telemetry.update();
                }

                servoJewel.setPosition(1);

            } else if (colorVal[0] <= 20 || colorVal[0] >= 340){
                telemetry.addData("color", "red");
                telemetry.update();

                left.setTargetPosition(left.getCurrentPosition() + 305 * backMotorDirections);
                right.setTargetPosition(right.getCurrentPosition() + 305 * backMotorDirections);
                frontLeft.setTargetPosition(frontLeft.getCurrentPosition() - 360);
                frontRight.setTargetPosition(frontRight.getCurrentPosition() - 360);

                while (opModeIsActive() && (left.isBusy() && right.isBusy() && frontLeft.isBusy() && frontRight.isBusy())) {
                    telemetry.addData("back right current position", right.getCurrentPosition());
                    telemetry.addData("back left current position", left.getCurrentPosition());
                    telemetry.addData("front right current position", frontRight.getCurrentPosition());
                    telemetry.addData("front left current position", frontLeft.getCurrentPosition());
                    telemetry.update();
                }

                left.setTargetPosition(left.getCurrentPosition() - 305 * backMotorDirections);
                right.setTargetPosition(right.getCurrentPosition() - 305 * backMotorDirections);
                frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + 360);
                frontRight.setTargetPosition(frontRight.getCurrentPosition() + 360);

                while (opModeIsActive() && (left.isBusy() && right.isBusy() && frontLeft.isBusy() && frontRight.isBusy())) {
                    telemetry.addData("back right current position", right.getCurrentPosition());
                    telemetry.addData("back left current position", left.getCurrentPosition());
                    telemetry.addData("front right current position", frontRight.getCurrentPosition());
                    telemetry.addData("front left current position", frontLeft.getCurrentPosition());
                    telemetry.update();
                }

                servoJewel.setPosition(1);

            } else{
                telemetry.addData("color", "unknown");
                telemetry.update();

                servoJewel.setPosition(1);

                sleep(500);
            }

            left.setTargetPosition(left.getCurrentPosition() + 1701 * backMotorDirections);
            right.setTargetPosition(right.getCurrentPosition() - 1701 * backMotorDirections);
            frontLeft.setTargetPosition(frontLeft.getCurrentPosition() - 1997);
            frontRight.setTargetPosition(frontRight.getCurrentPosition() + 1997);

            while (opModeIsActive() && (left.isBusy() && right.isBusy() && frontLeft.isBusy() && frontRight.isBusy())) {
                telemetry.addData("back right current position", right.getCurrentPosition());
                telemetry.addData("back left current position", left.getCurrentPosition());
                telemetry.addData("front right current position", frontRight.getCurrentPosition());
                telemetry.addData("front left current position", frontLeft.getCurrentPosition());
                telemetry.update();
            }

            left.setTargetPosition(left.getCurrentPosition() - 669 * backMotorDirections);
            right.setTargetPosition(right.getCurrentPosition() - 669 * backMotorDirections);
            frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + 790);
            frontRight.setTargetPosition(frontRight.getCurrentPosition() + 790);

            while (opModeIsActive() && (left.isBusy() && right.isBusy() && frontLeft.isBusy() && frontRight.isBusy())) {
                telemetry.addData("back right current position", right.getCurrentPosition());
                telemetry.addData("back left current position", left.getCurrentPosition());
                telemetry.addData("front right current position", frontRight.getCurrentPosition());
                telemetry.addData("front left current position", frontLeft.getCurrentPosition());
                telemetry.update();
            }

            left.setTargetPosition(left.getCurrentPosition() - 534 * backMotorDirections);
            right.setTargetPosition(right.getCurrentPosition() + 534 * backMotorDirections);
            frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + 630);
            frontRight.setTargetPosition(frontRight.getCurrentPosition() - 630);

            while (opModeIsActive() && (left.isBusy() && right.isBusy() && frontLeft.isBusy() && frontRight.isBusy())) {
                telemetry.addData("back right current position", right.getCurrentPosition());
                telemetry.addData("back left current position", left.getCurrentPosition());
                telemetry.addData("front right current position", frontRight.getCurrentPosition());
                telemetry.addData("front left current position", frontLeft.getCurrentPosition());
                telemetry.update();
            }

            servo.setPosition(servoOpenPos);
            servo2.setPosition(servo2OpenPos);

            left.setTargetPosition(left.getCurrentPosition() + 340 * backMotorDirections);
            right.setTargetPosition(right.getCurrentPosition() - 340 * backMotorDirections);
            frontLeft.setTargetPosition(frontLeft.getCurrentPosition() - 401);
            frontRight.setTargetPosition(frontRight.getCurrentPosition() + 401);

            while (opModeIsActive() && (left.isBusy() && right.isBusy() && frontLeft.isBusy() && frontRight.isBusy())) {
                telemetry.addData("back right current position", right.getCurrentPosition());
                telemetry.addData("back left current position", left.getCurrentPosition());
                telemetry.addData("front right current position", frontRight.getCurrentPosition());
                telemetry.addData("front left current position", frontLeft.getCurrentPosition());
                telemetry.update();
            }

            requestOpModeStop();

        } else if (BalancingStone == 2){
            servoJewel.setPosition(0.4);

            sleep(1000);

            Color.RGBToHSV(color.red() * 8, color.green() * 8, color.blue() * 8, colorVal);

            if (colorVal[0] <= 20 || colorVal[0] >= 340){
                telemetry.addData("color", "red");
                telemetry.update();

                left.setTargetPosition(left.getCurrentPosition() - 305 * backMotorDirections);
                right.setTargetPosition(right.getCurrentPosition() - 305 * backMotorDirections);
                frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + 360);
                frontRight.setTargetPosition(frontRight.getCurrentPosition() + 360);

                while (opModeIsActive() && (left.isBusy() && right.isBusy() && frontLeft.isBusy() && frontRight.isBusy())) {
                    telemetry.addData("back right current position", right.getCurrentPosition());
                    telemetry.addData("back left current position", left.getCurrentPosition());
                    telemetry.addData("front right current position", frontRight.getCurrentPosition());
                    telemetry.addData("front left current position", frontLeft.getCurrentPosition());
                    telemetry.update();
                }

                left.setTargetPosition(left.getCurrentPosition() + 305 * backMotorDirections);
                right.setTargetPosition(right.getCurrentPosition() + 305 * backMotorDirections);
                frontLeft.setTargetPosition(frontLeft.getCurrentPosition() - 360);
                frontRight.setTargetPosition(frontRight.getCurrentPosition() - 360);

                while (opModeIsActive() && (left.isBusy() && right.isBusy() && frontLeft.isBusy() && frontRight.isBusy())) {
                    telemetry.addData("back right current position", right.getCurrentPosition());
                    telemetry.addData("back left current position", left.getCurrentPosition());
                    telemetry.addData("front right current position", frontRight.getCurrentPosition());
                    telemetry.addData("front left current position", frontLeft.getCurrentPosition());
                    telemetry.update();
                }

                servoJewel.setPosition(1);

            } else if (colorVal[0] >= 220 && colorVal[0] <= 260){
                telemetry.addData("color", "blue");
                telemetry.update();

                left.setTargetPosition(left.getCurrentPosition() + 305 * backMotorDirections);
                right.setTargetPosition(right.getCurrentPosition() + 305 * backMotorDirections);
                frontLeft.setTargetPosition(frontLeft.getCurrentPosition() - 360);
                frontRight.setTargetPosition(frontRight.getCurrentPosition() - 360);

                while (opModeIsActive() && (left.isBusy() && right.isBusy() && frontLeft.isBusy() && frontRight.isBusy())) {
                    telemetry.addData("back right current position", right.getCurrentPosition());
                    telemetry.addData("back left current position", left.getCurrentPosition());
                    telemetry.addData("front right current position", frontRight.getCurrentPosition());
                    telemetry.addData("front left current position", frontLeft.getCurrentPosition());
                    telemetry.update();
                }

                left.setTargetPosition(left.getCurrentPosition() - 305 * backMotorDirections);
                right.setTargetPosition(right.getCurrentPosition() - 305 * backMotorDirections);
                frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + 360);
                frontRight.setTargetPosition(frontRight.getCurrentPosition() + 360);

                while (opModeIsActive() && (left.isBusy() && right.isBusy() && frontLeft.isBusy() && frontRight.isBusy())) {
                    telemetry.addData("back right current position", right.getCurrentPosition());
                    telemetry.addData("back left current position", left.getCurrentPosition());
                    telemetry.addData("front right current position", frontRight.getCurrentPosition());
                    telemetry.addData("front left current position", frontLeft.getCurrentPosition());
                    telemetry.update();
                }

                servoJewel.setPosition(1);

            } else{
                telemetry.addData("color", "unknown");
                telemetry.update();

                servoJewel.setPosition(1);

                sleep(500);
            }

            left.setTargetPosition(left.getCurrentPosition() + 1701 * backMotorDirections);
            right.setTargetPosition(right.getCurrentPosition() - 1701 * backMotorDirections);
            frontLeft.setTargetPosition(frontLeft.getCurrentPosition() - 1997);
            frontRight.setTargetPosition(frontRight.getCurrentPosition() + 1997);

            while (opModeIsActive() && (left.isBusy() && right.isBusy() && frontLeft.isBusy() && frontRight.isBusy())) {
                telemetry.addData("back right current position", right.getCurrentPosition());
                telemetry.addData("back left current position", left.getCurrentPosition());
                telemetry.addData("front right current position", frontRight.getCurrentPosition());
                telemetry.addData("front left current position", frontLeft.getCurrentPosition());
                telemetry.update();
            }

            left.setPower(0.4);
            right.setPower(0.4);
            frontLeft.setPower(0.4);
            frontRight.setPower(0.4);

            left.setTargetPosition(left.getCurrentPosition() - 669 * backMotorDirections);
            right.setTargetPosition(right.getCurrentPosition() - 669 * backMotorDirections);
            frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + 790);
            frontRight.setTargetPosition(frontRight.getCurrentPosition() + 790);

            while (opModeIsActive() && (left.isBusy() && right.isBusy() && frontLeft.isBusy() && frontRight.isBusy())) {
                telemetry.addData("back right current position", right.getCurrentPosition());
                telemetry.addData("back left current position", left.getCurrentPosition());
                telemetry.addData("front right current position", frontRight.getCurrentPosition());
                telemetry.addData("front left current position", frontLeft.getCurrentPosition());
                telemetry.update();
            }

            left.setPower(0.2);
            right.setPower(0.2);
            frontLeft.setPower(0.2);
            frontRight.setPower(0.2);

            left.setTargetPosition(left.getCurrentPosition() - 534 * backMotorDirections);
            right.setTargetPosition(right.getCurrentPosition() + 534 * backMotorDirections);
            frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + 630);
            frontRight.setTargetPosition(frontRight.getCurrentPosition() - 630);

            while (opModeIsActive() && (left.isBusy() && right.isBusy() && frontLeft.isBusy() && frontRight.isBusy())) {
                telemetry.addData("back right current position", right.getCurrentPosition());
                telemetry.addData("back left current position", left.getCurrentPosition());
                telemetry.addData("front right current position", frontRight.getCurrentPosition());
                telemetry.addData("front left current position", frontLeft.getCurrentPosition());
                telemetry.update();
            }

            servo.setPosition(servoOpenPos);
            servo2.setPosition(servo2OpenPos);

            left.setTargetPosition(left.getCurrentPosition() + 340 * backMotorDirections);
            right.setTargetPosition(right.getCurrentPosition() - 340 * backMotorDirections);
            frontLeft.setTargetPosition(frontLeft.getCurrentPosition() - 401);
            frontRight.setTargetPosition(frontRight.getCurrentPosition() + 401);

            while (opModeIsActive() && (left.isBusy() && right.isBusy() && frontLeft.isBusy() && frontRight.isBusy())) {
                telemetry.addData("back right current position", right.getCurrentPosition());
                telemetry.addData("back left current position", left.getCurrentPosition());
                telemetry.addData("front right current position", frontRight.getCurrentPosition());
                telemetry.addData("front left current position", frontLeft.getCurrentPosition());
                telemetry.update();
            }

            requestOpModeStop();

        }

    }
}