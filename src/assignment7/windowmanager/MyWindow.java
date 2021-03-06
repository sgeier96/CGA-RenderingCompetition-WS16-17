package assignment7.windowmanager;


import assignment7.entitites.Scene;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

//VM-Options for MAC: -Djava.awt.headless=true -Djava.library.path=libs/LWJGL/native -XstartOnFirstThread

/**
 * Created by Dennis Dubbert on 11.09.16.
 */
public class MyWindow extends Window {

	private Scene scene;


	public MyWindow(int width, int height){
		super(width, height);
	}

	// Gets called once after the window is created //
	// e.g. for setting up the scene and OpenGL parameters (e.g. glClearColor,...) //
	public void onInit(){
		/*extra auf 0.01f gesetzt, da die Sterne selbst noch licht abgeben und damit soll das ganze vereinfacht werden.
		 *Möglicherweise muss der Wert sogar noch erhöht werden.
		 */
		glClearColor(0.04f, 0.04f, 0.04f, 1);
//		    	glClearColor(1, 1, 1, 1);
		glDisable(GL_CULL_FACE);
		glEnable(GL_DEPTH_TEST);
		glViewport(0,0,width,height);

		scene = new Scene();
		scene.updatePerspective(width, height);

	}

	public void onUpdate(double currentTime) {
		scene.update();
	}

	// Gets called everytime the image is rendered //
	// currentTime is the time passed since window was created //
	public void onRender(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		scene.render();
	}

	// Gets called whenever window is resized //
	// width / height - The new width and height of the window //
	public void onResize(int width, int height){
		// simple solution (adjusted to window size)
		this.width = width;

		this.height = height;

		// determin the shorter side
		// int shortSide = (width > height) ? height : width;

		// set the viewport with glViewport();
		glViewport(0, 0, width, height);
		scene.updatePerspective(width, height);
	}

	// Gets called whenever a key on the keyboard is pressed //
	// cursorPositionX - x-Position inside the window/screen (window preferred)//
	// cursorPositionY - y-Position inside the window/screen (window preferred)//
	// pressedKey - key that got pressed //
	public void onKeyboard(float cursorPositionX, float cursorPositionY, int pressedKey, int mods){

		switch(pressedKey){
		case GLFW_KEY_ESCAPE:
			glfwSetWindowShouldClose(window, GL_TRUE);
			break;
		case GLFW_KEY_W:
			scene.getActiveCamera().moveCameraUpwards();
			scene.getSpaceship().moveSpaceshipUpwards();
			moveWindowPos(0, -5);
			break;
		case GLFW_KEY_S:
			scene.getActiveCamera().moveCameraDownwards();
			scene.getSpaceship().moveSpaceshipDownwards();
			moveWindowPos(0, 5);
			break;
		case GLFW_KEY_A:
			scene.getActiveCamera().turnCameraLeft();
			scene.getSpaceship().moveSpaceshipLeft();
			moveWindowPos(-5, 0);
			break;
		case GLFW_KEY_D:
			scene.getActiveCamera().turnCameraRight();
			scene.getSpaceship().moveSpaceshipRight();
			moveWindowPos(5, 0);
			break;
		case GLFW_KEY_N:
			scene.getActiveCamera().moveCameraForwards();
			break;
		case GLFW_KEY_M:
			scene.getActiveCamera().moveCameraBackwards();
			break;
		case GLFW_KEY_SPACE:
			scene.changeShader();
		}
	}

	// Gets called whenever the mouse is moved or a button on the mouse was pressed/released //
	// cursorPositionX - x-Position inside the window/screen (window preferred)//
	// cursorPositionY - y-Position inside the window/screen (window preferred)//
	// button - Button that was pressed (left/right) //
	// action - Was the button released or pressed? //
	public void onMouse(float cursorPositionX, float cursorPositionY, int button, int action){

	}
}
