package assignment7.entitites;

import assignment7.graphics.Material;
import assignment7.graphics.Shaderprogram;
import assignment7.graphics.Texture;
import assignment7.graphics.VertexArrayObject;
import assignment7.utilities.OBJLoader;
import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL13.GL_TEXTURE0;

public class Spaceship {
	private VertexArrayObject vao;
	private Texture texture;
	private Material material;

	public Matrix4f model_matrix;
	public static float xCoordinate = 0.5f;
	public static float yCoordinate = -5f;
	public float moveParam = 0.5f;
	private static float rotationAroundX = 0;
	private float rotationAroundY = 270;
	private static float rotationAroundZ = 0;


	public Spaceship() {
		texture = new Texture("res/images/spaceship_red.jpg");

		material = new Material(15f);
		fillVAO();
	}

	private void fillVAO(){
		vao = OBJLoader.loadObjModel("res/models/spaceship.obj");
	}

	public void update(){

	}

	public void moveSpaceshipUpwards(){
		for(int i = 0; i<10; i++){
			yCoordinate += 0.5f;
		}
		
		if(rotationAroundX <= 15)
		{
			rotationAroundX += 4;
		}
		if(rotationAroundX > 15 && rotationAroundX <= 20)
		{
			rotationAroundX += 2;
		}
		if(rotationAroundX <= 30 && rotationAroundX > 20)
		{
			rotationAroundX += 1;
		}

	}

	public void moveSpaceshipDownwards(){
		for(int i = 0; i<10; i++){
			yCoordinate -= moveParam;
		}
		if(rotationAroundX >= -15)
		{
			rotationAroundX -= 4;
		}
		if(rotationAroundX < -15 && rotationAroundX >= -20)
		{
			rotationAroundX -= 2;
		}
		if(rotationAroundX >= -30 && rotationAroundX < -20)
		{
			rotationAroundX -= 1;
		}
	}

	public void moveSpaceshipRight(){
		for(int i = 0; i<10; i++){
			xCoordinate += moveParam;
		}
		if(rotationAroundZ >= -25)
		{
			rotationAroundZ -= 4;
		}
		if(rotationAroundZ < -25 && rotationAroundZ >= -30)
		{
			rotationAroundZ -= 2;
		}
		if(rotationAroundZ >= -40 && rotationAroundZ < -30)
		{
			rotationAroundZ -= 1;
		}
	}

	public void moveSpaceshipLeft(){
		for(int i = 0; i<10; i++){
			xCoordinate -= moveParam;
		}
		
		if(rotationAroundZ <= 25)
		{
			rotationAroundZ += 4;
		}
		if(rotationAroundZ > 25 && rotationAroundZ <= 30)
		{
			rotationAroundZ += 2;
		}
		if(rotationAroundZ <= 40 && rotationAroundZ > 30)
		{
			rotationAroundZ += 1;
		}
	}

	/*
	 * Verbesserung notwendig
	 */
	public static void rebalanceSpaceship(){
		while(rotationAroundZ != 0 || rotationAroundX != 0){
			if(rotationAroundZ > 0){
				rotationAroundZ -= 1;
			}
			if(rotationAroundZ < 0){
				rotationAroundZ +=1;
			}
			if(rotationAroundX > 0){
				rotationAroundX -= 1;
			}
			if(rotationAroundX < 0){
				rotationAroundX +=1;
			}
		}
	}

	public void render(Shaderprogram shader, float size){
		shader.useProgram();
		texture.bind(GL_TEXTURE0);
		material.bind(shader);

		//Die Transformationen
		model_matrix = new Matrix4f().translate(xCoordinate, yCoordinate, -(size/2 + 20))
				.rotateZ((float)Math.toRadians(rotationAroundZ)).rotateX((float)Math.toRadians(rotationAroundX)).rotateY((float)Math.toRadians(rotationAroundY))
				.scale(0.4f);

		shader.setUniformMat4f("model_matrix", model_matrix);
		vao.render();
	}
}
