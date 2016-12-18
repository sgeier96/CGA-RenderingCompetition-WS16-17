package assignment7.entitites;

import assignment7.graphics.Material;
import assignment7.graphics.Shaderprogram;
import assignment7.graphics.Texture;
import assignment7.graphics.VertexArrayObject;
import assignment7.utilities.OBJLoader;
import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL13.GL_TEXTURE0;

/**
 * Created by Dennis Dubbert on 23.11.16.
 */
public class Statue {
    private VertexArrayObject vao;
    private Texture texture;
    private Material material;


    public Statue() {
        texture = new Texture("res/images/statue.png");
        material = new Material(5f);
        fillVAO();
    }

    private void fillVAO(){
        vao = OBJLoader.loadObjModel("res/models/statue.obj");
    }

    public void update(){

    }

    public void render(Shaderprogram shader, float size){
        shader.useProgram();
        texture.bind(GL_TEXTURE0);
        material.bind(shader);
        Matrix4f model_matrix;
        for(int i = 0; i<5; i++){
            model_matrix = new Matrix4f().translate(32f, 10f, i*(-10)).rotateZ((float)Math.toRadians(45)).rotateY((float)Math.toRadians(-90)).scale(3f);
            shader.setUniformMat4f("model_matrix", model_matrix);
            vao.render();
            model_matrix = new Matrix4f().translate(-32f, 10f, i*(-10)).rotateZ((float)Math.toRadians(-45)).rotateY((float)Math.toRadians(90)).scale(3f);
            shader.setUniformMat4f("model_matrix", model_matrix);
            vao.render();
        }

        model_matrix = new Matrix4f().translate(-9.5f, 10f, -(size + 22)).rotateX((float)Math.toRadians(45)).scale(3f);
        shader.setUniformMat4f("model_matrix", model_matrix);
        vao.render();
        model_matrix = new Matrix4f().translate(10.5f, 10f, -(size + 22)).rotateX((float)Math.toRadians(45)).scale(3f);
        shader.setUniformMat4f("model_matrix", model_matrix);
        vao.render();
    }
}
