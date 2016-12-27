package assignment7.windowmanager;

import assignment7.utilities.Input;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.awt.Dimension;



/**
 * Created by Dennis Dubbert on 06.09.16.
 */
public abstract class Window {
	protected int   width, height;
	protected long  window;

	/*
	 * In ScreenSize wird die Größe des Aktuellen Bildschirms abgespeichert.
	 * Daraufhin wird die initiale x- und y-Koordinate auf die Mitte des Bildschirmes gesetzt.
	 * Da aber die linkere obere Ecke des Fensters zentriert wäre und nicht die Mitte dessen, 
	 * wurde 320 von der x-Koordinate und 240 von der y-Koordinate abgezogen. (was der Hälfte des Fensters entspricht. Siehe MyWindow.java)
	 */
	protected Dimension ScreenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	protected int xWindowPos = (int) ScreenSize.getWidth()/2 - 320;
	protected int yWindowPos = (int) ScreenSize.getHeight()/2 - 240;

	public Window(int width, int height) {
		this.width = width;
		this.height = height;
		start();
	}

	private void init(){
		if(glfwInit() != GL_TRUE){
			System.err.println("Could not initialize our glfw!");
			return;
		}

		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);

		window = glfwCreateWindow(width, height, "assignment7", NULL, NULL);

		glfwSetWindowPos(window, xWindowPos, yWindowPos);
		System.out.println((int)ScreenSize.getHeight()/2);


		if(window == NULL){
			System.err.println("Could not create our Window!");
			return;
		}


		Input.init(window, this);
		glfwMakeContextCurrent(window);
		glfwShowWindow(window);

		GL.createCapabilities();

		System.out.println("Your OpenGL version is " + glGetString(GL_VERSION));
		onInit();
	}

	public void start(){
		init();

		while(true){

			update();
			render();

			if(glfwWindowShouldClose(window) == GL_TRUE){
				break;
			}
		}

		glfwDestroyWindow(window);
		glfwTerminate();
	}

	private void update(){
		glfwPollEvents();
		onUpdate(glfwGetTime());
	}

	private void render(){
		onRender();
		glfwSwapBuffers(window);
	}

	public void moveWindowPos(int x, int y){

		/*
		 * Um das leichte ruckeln zu entfernen könnte man statt einmal um 1 zu erhöhen/erniedrigen 10 mal um 0.1f erhöhen/erniedrigen.
		 * Gedanke dahinter: die Taktrate der GPU ist um einiges Schneller als die einfache erhöhung um 1. 
		 * Erste Idee (noch falsch!): 
		 * if( x < 0 ){
			for(float i = x; i < 0; i++){
				glfwSetWindowPos(window, xWindowPos-=0.1f, yWindowPos);
				}
			}
		if( x > 0 ){
			for(float i = 0; i < x; i++){
				glfwSetWindowPos(window, xWindowPos+=0.1f, yWindowPos);
			}
		}
		if( y < 0 ){
			for(float i = y; i < 0; i++){
				glfwSetWindowPos(window, xWindowPos, yWindowPos-=0.1f);
			}
		}
		if( y > 0 ){
			for(float i = 0; i < y; i++){
				glfwSetWindowPos(window, xWindowPos, yWindowPos+=0.1f);
			}
		}
		 */
				xWindowPos = xWindowPos+x;
				yWindowPos = yWindowPos+y;
				glfwSetWindowPos(window, xWindowPos, yWindowPos);
				
	}

	protected abstract void onInit();
	protected abstract void onUpdate(double currentTime);
	protected abstract void onRender();
	public abstract void onResize(int width, int height);
	public abstract void onKeyboard(float cursorPositionX, float cursorPositionY, int pressedKey, int mods);
	public abstract void onMouse(float cursorPositionX, float cursorPositionY, int button, int action);

}


