package assignment7.entitites;

import assignment7.graphics.Camera;
import assignment7.graphics.Shaderprogram;
import assignment7.graphics.Texture;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL11.*;

public class Scene {
	private Asteroid asteroid;
	private Spaceship spaceship;
	private Sun sun;
	private Star star;
	private CornellBox box;
	
	private float sunsize = 1000;
	private static int amountOfCollisions = 0;

	private float fov, nplane, fplane;
	private Matrix4f projection_matrix;
	private FlyThroughCamera camera;
	private float size;
	private Vector3f light_position, light_color;
	private Texture flashlight_texture;
	private int shader_mode;
	private Shaderprogram[] cube_shaderprograms, model_shaderprograms;


	public Scene(){
		//setup models
		size = 30;
		asteroid = new Asteroid();
		spaceship = new Spaceship();
		sun = new Sun();
		star = new Star();
	    box = new CornellBox(1901f);
		//setup camera
		projection_matrix = new Matrix4f();
		camera = new FlyThroughCamera();
		fov = 40;
		nplane = 0.01f;
//______** fPlane geändert da die Asteroiden hinter der Sonne auftauchen.
		fplane = 4000f;

		//setup shader
		shader_mode = 0;
		cube_shaderprograms = new Shaderprogram[2];
		model_shaderprograms = new Shaderprogram[2];
		cube_shaderprograms[0] = new Shaderprogram("res/shaders/assignment7/cube.vert", "res/shaders/assignment7/cube.frag");
		model_shaderprograms[0] = new Shaderprogram("res/shaders/assignment7/model.vert", "res/shaders/assignment7/model.frag");
		cube_shaderprograms[1] = new Shaderprogram("res/shaders/assignment7/cube_flashlight.vert", "res/shaders/assignment7/cube_flashlight.frag");
		model_shaderprograms[1] = new Shaderprogram("res/shaders/assignment7/model_flashlight.vert", "res/shaders/assignment7/model_flashlight.frag");
		cube_shaderprograms[0].setUniform1i("tex0", 0);
		model_shaderprograms[0].setUniform1i("tex0", 0);
		cube_shaderprograms[1].setUniform1i("tex0", 0);
		model_shaderprograms[1].setUniform1i("tex0", 0);

		//setup top-light
		
//______**alte Lichtfarbe und Position
//		light_position = new Vector3f(0, size+10, -(size-10));
		light_position = new Vector3f(-650.0f, 700.0f, -1500.0f);
		light_color = new Vector3f(1.0f, 1.0f, 1.0f);
		
		cube_shaderprograms[0].setUniform3f("light_position", light_position);
		cube_shaderprograms[0].setUniform3f("light_color", light_color);
		model_shaderprograms[0].setUniform3f("light_position", light_position);
		model_shaderprograms[0].setUniform3f("light_color", light_color);

		//setup flashlight

//______flashlight wird wahrscheinlich nicht gebraucht		
//		flashlight_texture = new Texture("res/images/flashlight.jpg");
		cube_shaderprograms[1].setUniform1i("tex1", 1);
		model_shaderprograms[1].setUniform1i("tex1", 1);
		model_shaderprograms[1].useProgram();
//		flashlight_texture.bind(GL_TEXTURE1);
		cube_shaderprograms[1].useProgram();
//		flashlight_texture.bind(GL_TEXTURE1);

	}

	public void update(){
		camera.updateViewMatrix();
	}

	public void render(){
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
		cube_shaderprograms[shader_mode].setUniformMat4f("projection_matrix", projection_matrix);
		cube_shaderprograms[shader_mode].setUniformMat4f("view_matrix", camera.getView_matrix());
		model_shaderprograms[shader_mode].setUniformMat4f("projection_matrix", projection_matrix);
		model_shaderprograms[shader_mode].setUniformMat4f("view_matrix", camera.getView_matrix());

		//Das Raumschiff
		spaceship.render(model_shaderprograms[shader_mode], size);
		//Die Kometen
		asteroid.render(model_shaderprograms[shader_mode]);
        //Die Sonne		
		sun.render(model_shaderprograms[shader_mode],sunsize);
		//Die Sterne
		star.render(model_shaderprograms[shader_mode]);
		box.render(cube_shaderprograms[shader_mode]);

		
	}

	public void updatePerspective(int width, int height){
		cube_shaderprograms[1].setUniform1f("width", width);
		cube_shaderprograms[1].setUniform1f("height", height);
		model_shaderprograms[1].setUniform1f("width", width);
		model_shaderprograms[1].setUniform1f("height", height);
		projection_matrix = new Matrix4f().setPerspective((float)Math.toRadians(fov), (float)width/height, nplane, fplane);
	}

	public Spaceship getSpaceship(){
		return spaceship;
	}

	public Camera getActiveCamera(){
		return camera;
	}

	public void changeShader(){
		shader_mode = ++shader_mode % 2;
	}

	public static int getAmountOfCollisions() {
		return amountOfCollisions;
	}

	public static void increaseAmountOfCollisions() {
		amountOfCollisions++;
	}
}
