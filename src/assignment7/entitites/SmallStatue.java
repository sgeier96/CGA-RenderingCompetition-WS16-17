package assignment7.entitites;

import assignment7.graphics.Material;
import assignment7.graphics.Shaderprogram;
import assignment7.graphics.Texture;
import assignment7.graphics.VertexArrayObject;
import assignment7.utilities.OBJLoader;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL13.GL_TEXTURE0;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by Dennis Dubbert on 23.11.16.
 */
public class SmallStatue {
	private VertexArrayObject vao;
	private Texture texture, texture2;
	private Material material;

	//Zufällige x und y Positionen der jeweiligen Kometen
	private ArrayList<Float> xPositions = new ArrayList<Float>();
	private ArrayList<Float> yPositions = new ArrayList<Float>();
	private ArrayList<Float> zPositions = new ArrayList<Float>();

	private int cometsDisplayed = 20;
	

	public SmallStatue() {
		texture = new Texture("res/images/gold.jpg");
		texture2 = new Texture("res/images/terrakotta.jpg");
		material = new Material(15f);
		fillVAO();
		randomizeCometPosition();
	}

	private void fillVAO(){
		vao = OBJLoader.loadObjModel("res/models/armadillo.obj");
	}

	public void update(){

	}

	public void rearrangeComet(int cometIndex){
		xPositions.set(cometIndex, (float)Math.random()*300);
		yPositions.set(cometIndex, (float)Math.random()*300);
		zPositions.set(cometIndex, -1000f + (float)Math.random()*500);
	}
	
	public void randomizeCometPosition(){
		for(int i = 0; i < cometsDisplayed; i++){
			xPositions.add((float)Math.random()*300);
			yPositions.add((float)Math.random()*300);
			zPositions.add(-1000f + (float)Math.random()*500);
		}
	}
	
	public void increaseZPosition(){
		
	}

	public void render(Shaderprogram shader){
		shader.useProgram();
		material.bind(shader);

		Matrix4f model_matrix = new Matrix4f().translate(xPositions.get(0), yPositions.get(0), zPositions.get(0)).scale(4f);

		//Geschwindigkeit der Kometen
		for(int i = 0; i < zPositions.size(); i++){
			zPositions.set(i, zPositions.get(i)+4f);
		}
		//Aus der nPlane raus -> neu bei -1000f gezeichnet
		for(int i = 0; i < cometsDisplayed; i++){
			if(zPositions.get(i) > 0.01f){
				rearrangeComet(i);
			}
		}
		
		shader.setUniformMat4f("model_matrix", model_matrix);
		vao.render();
		
		for (int i = 1; i < cometsDisplayed; i++){
			model_matrix = new Matrix4f().translate(xPositions.get(i), yPositions.get(i), zPositions.get(i)).scale(4f);
			shader.setUniformMat4f("model_matrix", model_matrix);
			vao.render();
		}
	}
}
