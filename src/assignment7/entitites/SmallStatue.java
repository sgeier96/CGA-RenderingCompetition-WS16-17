package assignment7.entitites;

import assignment7.graphics.Material;
import assignment7.graphics.Shaderprogram;
import assignment7.graphics.Texture;
import assignment7.graphics.VertexArrayObject;
import assignment7.utilities.OBJLoader;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL13.GL_TEXTURE0;

/**
 * Created by Dennis Dubbert on 23.11.16.
 */
public class SmallStatue {
    private VertexArrayObject vao;
    private Texture texture, texture2;
    private Material material;

    //Zufällige x und y Positionen
    private float xPosition;
    private float yPosition;
    private float zPosition;


    public SmallStatue() {
        texture = new Texture("res/images/gold.jpg");
        texture2 = new Texture("res/images/terrakotta.jpg");
        material = new Material(15f);
        fillVAO();
    }

    private void fillVAO(){
        vao = OBJLoader.loadObjModel("res/models/armadillo.obj");
    }

    public void update(){

    }
    
    public void render(Shaderprogram shader){
    	xPosition  = (float)Math.random()*50;
    	yPosition  = (float)Math.random()*50;
    	shader.useProgram();
        material.bind(shader);

        for(int i = 0; i<5; i++){
            if(i%2 == 0){
                texture.bind(GL_TEXTURE0);
            }else{
                texture2.bind(GL_TEXTURE0);
            }

            Matrix4f model_matrix = new Matrix4f().translate(xPosition, yPosition, -1000f + zPosition).scale(4f);
            //Geschwindigkeit der Kometen
            zPosition += 0.5f;
            shader.setUniformMat4f("model_matrix", model_matrix);
            vao.render();
            //model_matrix = new Matrix4f().translate(-22.5f, 0.5f, i*(-10)).rotateY((float)Math.toRadians(-90)).scale(2f);
//            shader.setUniformMat4f("model_matrix", model_matrix);
//            vao.render();
        }
    }
}
