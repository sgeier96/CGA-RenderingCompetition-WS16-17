package assignment7.graphics;

import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * Created by Dennis Dubbert on 09.11.16.
 */
public abstract class Camera {
    protected float sideAngle; // also known as "phi"
    protected float downAngle; // also known as "theta"
    protected float cameraDistance;
    protected Vector3f lookAt;
    protected Vector3f upVector;
    protected Vector3f cameraPosition;
    protected Matrix4f view_matrix;

    public Camera(){
        sideAngle                 = (float)Math.toRadians(0);
        downAngle                 = (float)Math.toRadians(0);
        cameraDistance            = 1;
        lookAt                    = new Vector3f(0);
        upVector                  = new Vector3f(0,1,0);
        cameraPosition            = new Vector3f();
        view_matrix               = new Matrix4f();
    }

    public abstract void updateViewMatrix();

    public abstract void moveCameraUpwards();

    public abstract void moveCameraDownwards();

    public abstract void turnCameraRight();

    public abstract void turnCameraLeft();

    public abstract void moveCameraForwards();

    public abstract void moveCameraBackwards();

    public Matrix4f getView_matrix(){
        return view_matrix;
    }
}
