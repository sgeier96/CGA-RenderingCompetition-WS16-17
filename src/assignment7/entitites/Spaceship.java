package assignment7.entitites;

import assignment7.graphics.Material;
import assignment7.graphics.Shaderprogram;
import assignment7.graphics.Texture;
import assignment7.graphics.VertexArrayObject;
import assignment7.utilities.OBJLoader;
import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL13.GL_TEXTURE0;

/**
 * Created by Dennis Dubbert on 24.11.16.
 */
public class Spaceship {
    private VertexArrayObject vao;
    private Texture texture;
    private Material material;
    
    public Matrix4f model_matrix;
    public float xCoordinate = 0.5f;
    public float yCoordinate = -5f;
    public float moveParam = 5;
    private float rotationAroundX = 0;
    private float rotationAroundY = 270;
    private float rotationAroundZ = 0;


    public Spaceship() {
    	//Hier noch einfach mit einer goldenen Textur versehen
        texture = new Texture("res/images/gold.jpg");
        material = new Material(15f);
        fillVAO();
    }

    private void fillVAO(){
        vao = OBJLoader.loadObjModel("res/models/spaceship.obj");
    }

    public void update(){

    }
    
    public void moveSpacecruiserUpwards(){
    	for(int i = 0; i<10; i++){
    		yCoordinate += 0.5f;
    	}
    	
    }
    
    public void moveSpacecruiserDownwards(){
    	yCoordinate -= moveParam;
    }
    
    public void moveSpacecruiserRight(){
    	xCoordinate += moveParam;
    }
    
    public void moveSpacecruiserLeft(){
    	xCoordinate -= moveParam;
    }

    public void render(Shaderprogram shader, float size){
        shader.useProgram();
        texture.bind(GL_TEXTURE0);
        material.bind(shader);

        //Die Transformationen
        Matrix4f model_matrix = new Matrix4f().translate(xCoordinate, yCoordinate, -(size/2 + 10))
        		.rotateX((float)Math.toRadians(rotationAroundX)).rotateY((float)Math.toRadians(rotationAroundY))
        		.rotateZ((float)Math.toRadians(rotationAroundZ)).scale(0.4f);
        
        shader.setUniformMat4f("model_matrix", model_matrix);
        vao.render();
    }
}
