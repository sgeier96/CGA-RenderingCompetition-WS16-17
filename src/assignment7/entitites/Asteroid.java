package assignment7.entitites;

import assignment7.graphics.Material;
import assignment7.graphics.Shaderprogram;
import assignment7.graphics.Texture;
import assignment7.graphics.VertexArrayObject;
import assignment7.utilities.OBJLoader;
import assignment7.utilities.OBJLoaderBACKUPORIGINAL;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL13.GL_TEXTURE0;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Dennis Dubbert on 23.11.16.
 */
public class Asteroid {
	private VertexArrayObject vao;
	private Texture texture, texture2;
	private Material material;

	private ArrayList<Float> xPositions = new ArrayList<Float>();
	private ArrayList<Float> yPositions = new ArrayList<Float>();
	private ArrayList<Float> zPositions = new ArrayList<Float>();
	private ArrayList<Float> AsteroidSizes = new ArrayList<Float>();
	private ArrayList<Float> rotations = new ArrayList<Float>();

	private int AsteroidsDisplayed = 30;


	public Asteroid() {
		texture = new Texture("res/images/gold.jpg");
		texture2 = new Texture("res/images/terrakotta.jpg");
		material = new Material(15f);
		fillVAO();
		randomizeSize();
		randomizeAsteroidPosition();
		randomizeRotationAsteroid();
	}

	private void fillVAO(){
		vao = OBJLoader.loadObjModel("res/models/asteroid.obj");
	}

	public void update(){

	}

	public void randomizeSize(){
		for(int i = 0; i < AsteroidsDisplayed; i++){
			AsteroidSizes.add((float) (((float)Math.toRadians((float)Math.random())+0.4)*2.4f));
		}
	}

	public void resizeAsteroid(int AsteroidIndex){
		AsteroidSizes.set(AsteroidIndex, (float) (((float)Math.toRadians((float)Math.random())+0.4)*2.4f));
	}


	public void rearrangeAsteroid(int AsteroidIndex){
		xPositions.set(AsteroidIndex, (float) ThreadLocalRandom.current().nextInt(-300, 301));
		yPositions.set(AsteroidIndex, (float) ThreadLocalRandom.current().nextInt(-300, 301));
		zPositions.set(AsteroidIndex, (float) ThreadLocalRandom.current().nextInt(-1500, -999));
	}

	public void randomizeAsteroidPosition(){
		for(int i = 0; i < AsteroidsDisplayed; i++){
			//Zufallszahlen in einem gewissen Bereich (bspw. [-300;300]) 
			//+1 weil die Obergrenze exkludiert wird, also nicht als Zufallszahl entstehen kann
			xPositions.add((float) ThreadLocalRandom.current().nextInt(-300, 301));
			yPositions.add((float) ThreadLocalRandom.current().nextInt(-300, 301));
			zPositions.add((float) ThreadLocalRandom.current().nextInt(-1500, -999));
		}
	}
	
	public void randomizeRotationAsteroid(){
		for(int i = 0; i < AsteroidsDisplayed*3; i++){
			rotations.add((float)Math.toRadians((float)Math.random()*359));
			rotations.add((float)Math.toRadians((float)Math.random()*359));
			rotations.add((float)Math.toRadians((float)Math.random()*359));
		}
	}
	
	public void rotateAsteroid(){		
		
		for(int i = 0; i < AsteroidsDisplayed; i++){
			rotations.set(i*3, (rotations.get(i*3) + (float)Math.toRadians((float)Math.random()/6)) % 360);
			rotations.set(i*3+1, (rotations.get(i*3+1) + (float)Math.toRadians((float)Math.random()/6)) % 360);
			rotations.set(i*3+2, (rotations.get(i*3+2) + (float)Math.toRadians((float)Math.random()/6)) % 360);
		}
	}

	public void render(Shaderprogram shader){
		shader.useProgram();
		material.bind(shader);

		Matrix4f model_matrix = new Matrix4f().translate(xPositions.get(0), yPositions.get(0), zPositions.get(0)).scale(AsteroidSizes.get(0))
				.rotateX(rotations.get(0)).rotateY(rotations.get(1)).rotateZ(rotations.get(2));
		rotateAsteroid();
		//Geschwindigkeit der Kometen
		for(int i = 0; i < zPositions.size(); i++){
			zPositions.set(i, zPositions.get(i)+1f);
		}
		
		//Aus der nPlane raus -> neu bei -1000f gezeichnet
		for(int i = 0; i < AsteroidsDisplayed; i++){
			if(zPositions.get(i) > 0.01f){
				rearrangeAsteroid(i);
				resizeAsteroid(i);
			}
		}

		shader.setUniformMat4f("model_matrix", model_matrix);
		vao.render();

		for (int i = 1; i < AsteroidsDisplayed; i++){
			model_matrix = new Matrix4f().translate(xPositions.get(i), yPositions.get(i), zPositions.get(i)).scale(AsteroidSizes.get(i))
					.rotateX(rotations.get(i*3)).rotateY(rotations.get(i*3+1)).rotateZ(rotations.get(i*3+2));
			shader.setUniformMat4f("model_matrix", model_matrix);
			vao.render();
		}
	}
}
