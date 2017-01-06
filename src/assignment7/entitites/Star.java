package assignment7.entitites;

import static org.lwjgl.opengl.GL13.GL_TEXTURE0;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.joml.Matrix4f;

import assignment7.graphics.Material;
import assignment7.graphics.Shaderprogram;
import assignment7.graphics.Texture;
import assignment7.graphics.VertexArrayObject;
import assignment7.utilities.OBJLoader;

public class Star {
	
private VertexArrayObject vao;
private Texture texture;
private Material material;

public Matrix4f model_matrix;
private ArrayList<Float> xPositions = new ArrayList<Float>();
private ArrayList<Float> yPositions = new ArrayList<Float>();
private ArrayList<Float> zPositions = new ArrayList<Float>();
private ArrayList<Float> StarSizes = new ArrayList<Float>();
private ArrayList<Float> rotations = new ArrayList<Float>();

private int StarDisplayed = 30;


public Star() {
	texture = new Texture("res/images/white.jpg");
	material = new Material(15f);
	randomizeSize();
	randomizeStarPosition();
	randomizeRotationStar();
	fillVAO();
	
}

private void fillVAO(){
	vao = OBJLoader.loadObjModel("res/models/star.obj");
}

public void update(){

}

public void randomizeSize(){
	for(int i = 0; i < StarDisplayed; i++){
		StarSizes.add((float) (((float)Math.toRadians((float)Math.random())+0.4)*15f));
	}
}

public void resizeStar(int AsteroidIndex){
	StarSizes.set(AsteroidIndex, (float) (((float)Math.toRadians((float)Math.random())+0.4)*15f));
}


public void rearrangeStar(int AsteroidIndex){
	xPositions.set(AsteroidIndex, (float) ThreadLocalRandom.current().nextInt(-300, 301));
	yPositions.set(AsteroidIndex, (float) ThreadLocalRandom.current().nextInt(-300, 301));
	zPositions.set(AsteroidIndex, (float) ThreadLocalRandom.current().nextInt(-1500, -999));
}

public void randomizeStarPosition(){
	for(int i = 0; i < StarDisplayed; i++){
		//Zufallszahlen in einem gewissen Bereich (bspw. [-300;300]) 
		//+1 weil die Obergrenze exkludiert wird, also nicht als Zufallszahl entstehen kann
		xPositions.add((float) ThreadLocalRandom.current().nextInt(-1000, 1001));
		yPositions.add((float) ThreadLocalRandom.current().nextInt(-1000, 1001));
		zPositions.add((float) ThreadLocalRandom.current().nextInt(-2500, -1999));
	}
}

public void randomizeRotationStar(){
	for(int i = 0; i < StarDisplayed*3; i++){
		rotations.add((float)Math.toRadians((float)Math.random()*359));
		rotations.add((float)Math.toRadians((float)Math.random()*359));
		rotations.add((float)Math.toRadians((float)Math.random()*359));
	}
}

public void rotateStar(){		
	
	for(int i = 0; i < StarDisplayed; i++){
		rotations.set(i*3, (rotations.get(i*3) + (float)Math.toRadians((float)Math.random()/6)) % 360);
		rotations.set(i*3+1, (rotations.get(i*3+1) + (float)Math.toRadians((float)Math.random()/6)) % 360);
		rotations.set(i*3+2, (rotations.get(i*3+2) + (float)Math.toRadians((float)Math.random()/6)) % 360);
	}
}

public void render(Shaderprogram shader){
	shader.useProgram();
	texture.bind(GL_TEXTURE0);
	material.bind(shader);

	model_matrix = new Matrix4f().translate(xPositions.get(0), yPositions.get(0), zPositions.get(0)).scale(StarSizes.get(0))
			.rotateX(rotations.get(0)).rotateY(rotations.get(1)).rotateZ(rotations.get(2));
	rotateStar();
	//Geschwindigkeit der Kometen
	for(int i = 0; i < zPositions.size(); i++){
		zPositions.set(i, zPositions.get(i)+100f);
	}
	
	//Aus der nPlane raus -> neu bei -1000f gezeichnet
	for(int i = 0; i < StarDisplayed; i++){
		if(zPositions.get(i) > 0.01f){
			rearrangeStar(i);
			resizeStar(i);
		}
	}
	
	for (int i = 1; i < StarDisplayed; i++){
		model_matrix = new Matrix4f().translate(xPositions.get(i), yPositions.get(i), zPositions.get(i)).scale(2.f)
				.rotateX(rotations.get(i*3)).rotateY(rotations.get(i*3+1)).rotateZ(rotations.get(i*3+2));
		shader.setUniformMat4f("model_matrix", model_matrix);
		vao.render();
	}

	shader.setUniformMat4f("model_matrix", model_matrix);
	vao.render();

 }
}
