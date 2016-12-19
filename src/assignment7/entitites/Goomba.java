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
public class Goomba {
    private VertexArrayObject vao;
    private Texture texture;
    private Material material;


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

    public void render(Shaderprogram shader, float size){
        shader.useProgram();
        texture.bind(GL_TEXTURE0);
        material.bind(shader);

        Matrix4f model_matrix = new Matrix4f().translate(0.5f, -1.5f, -(size/2 + 10)).scale(0.8f);
        shader.setUniformMat4f("model_matrix", model_matrix);
        vao.render();
    }
}
