/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.adk.core;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.NIVisionException;

/**
 *
 * @author dtjones
 */
public class Tracker {

    static Tracker instance = null;
    Target.Threshold firstColor = new Target.Threshold(60,80,0,10,220,255);
    Target.Position position = Target.Position.above;
    AxisCamera camera = AxisCamera.getInstance();

    public static Tracker getInstance() {
        if (instance == null) {
            instance = new Tracker();
        }
        return instance;
    }

    public void setFirstColorThresholds(int lowerHue, int upperHue,
            int lowerSaturation, int upperSaturation,
            int lowerLuminance, int upperLuminance) {
        firstColor = new Target.Threshold(lowerHue, upperHue,
                lowerSaturation, upperSaturation,
                lowerLuminance, upperLuminance);
    }

    public Target getTarget() throws NIVisionException, AxisCameraException {
        while (true) {
            ColorImage image = camera.getImage();
            try {
                return Target.getTarget(image, position, firstColor);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (image != null) {
                    image.free();
                }
                image = null;
            }
            Timer.delay(.001);
        }
    }
}
