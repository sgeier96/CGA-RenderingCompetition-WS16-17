package assignment7.entitites;

import assignment7.graphics.Material;
import assignment7.graphics.Shaderprogram;
import assignment7.graphics.Texture;
import assignment7.graphics.VertexArrayObject;
import assignment7.utilities.OBJLoader;
import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL13.GL_TEXTURE0;

/**
 * Created by Dennis Dubbert on 25.11.16.
 */
public class Grave {
    private VertexArrayObject vao;
    private Texture texture;
    private Material material;


    public Grave() {
        texture = new Texture("res/images/terrakotta.jpg");
        material = new Material(15f);
        fillVAO();
    }

    private void fillVAO(){
        vao = OBJLoader.loadObjModel("res/models/grave.obj");
    }

    public void update(){

    }

    public void render(Shaderprogram shader, float size){
        shader.useProgram();
        texture.bind(GL_TEXTURE0);
        material.bind(shader);

        Matrix4f model_matrix = new Matrix4f().translate(0f, -10f, -(size + 20)).rotateY((float)Math.toRadians(90)).scale(0.4f);
        shader.setUniformMat4f("model_matrix", model_matrix);
        vao.render();
    }
}
