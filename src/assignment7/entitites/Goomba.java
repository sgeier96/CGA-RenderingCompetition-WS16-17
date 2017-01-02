package assignment7.entitites;

import assignment7.graphics.Material;
import assignment7.graphics.Shaderprogram;
import assignment7.graphics.Texture;
import assignment7.graphics.VertexArrayObject;
import assignment7.utilities.Input;
import assignment7.utilities.OBJLoader;
import assignment7.utilities.OBJLoaderBACKUPORIGINAL;

import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL13.GL_TEXTURE0;

import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Dennis Dubbert on 24.11.16.
 */
public class Goomba {
    private VertexArrayObject vao;
    private Texture texture;
    private Material material;
    
    public Matrix4f model_matrix;
    public float xCoordinate = 0.5f;
    public float yCoordinate = -8f;
    public float moveParam = 5;

    public Goomba() {
        texture = new Texture("res/images/gold.jpg");
        material = new Material(15f);
        fillVAO();
    }

    private void fillVAO(){
        vao = OBJLoader.loadObjModel("res/models/goomba.obj");
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
        
        model_matrix = new Matrix4f().translate(xCoordinate, yCoordinate, -(size/2 + 10)).scale(0.8f);
        shader.setUniformMat4f("model_matrix", model_matrix);
        vao.render();
    }
    
}
