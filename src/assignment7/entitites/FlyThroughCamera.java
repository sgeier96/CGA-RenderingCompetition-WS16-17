package assignment7.entitites;

import assignment7.graphics.Camera;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * Created by Dennis Dubbert on 09.11.16.
 */
public class FlyThroughCamera extends Camera {
    private Vector3f cameraToTargetVector;

    public FlyThroughCamera(){
        super();
        cameraPosition          = new Vector3f(0, 0, cameraDistance);
        cameraToTargetVector    = new Vector3f();
        sideAngle               = (float)Math.toRadians(-90);
        downAngle               = (float)Math.toRadians(90);
    }

    @Override
    public void updateViewMatrix() {
        cameraToTargetVector = new Vector3f(0, cameraDistance, 0);
        Matrix3f transformationsMatrix = new Matrix3f();
        transformationsMatrix.rotateY(sideAngle);
        transformationsMatrix.rotateZ(downAngle);
        cameraToTargetVector.mul(transformationsMatrix);
        lookAt = new Vector3f();
        cameraPosition.add(cameraToTargetVector, lookAt);

        view_matrix = new Matrix4f().lookAt(cameraPosition, lookAt, upVector);
    }

    @Override
    public void moveCameraUpwards() {
        cameraPosition.add(0,5,0);
    }

    @Override
    public void moveCameraDownwards() {
    	cameraPosition.add(0,-5,0);
    }

    @Override
    public void turnCameraRight() {
    	for(int i = 0; i < 5; i++){
        	cameraPosition.add(1,0,0);
        	}
    }

    @Override
    public void turnCameraLeft() {
    	/*
    	 * Testweise eingebaut. So Ruckelfreier?
    	 * (anstatt: cameraPosition.add(-5,0,0);)
    	 */
    	for(int i = 0; i < 5; i++){
    	cameraPosition.add(-1,0,0);
    	}
    }

    @Override
    public void moveCameraForwards() {
        Vector3f viewDirection = new Vector3f(cameraToTargetVector);
        viewDirection.y = 0;
        cameraPosition.add(viewDirection.mul(0.5f));
    }

    @Override
    public void moveCameraBackwards() {
        Vector3f viewDirection = new Vector3f(cameraToTargetVector);
        viewDirection.y = 0;
        viewDirection.negate();
        cameraPosition.add(viewDirection.mul(0.5f));
    }
}
