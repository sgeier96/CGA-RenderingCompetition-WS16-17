package assignment7.entitites;

import assignment7.graphics.Material;
import assignment7.graphics.Shaderprogram;
import assignment7.graphics.Texture;
import assignment7.graphics.VertexArrayObject;
import assignment7.utilities.OBJLoader;
import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL13.GL_TEXTURE0;

public class Sun {
    private VertexArrayObject vao;
    private Texture texture;
    private Material material;


    public Sun() {
        texture = new Texture("res/images/sun.jpg");
        material = new Material(15f);
        fillVAO();
    }

    private void fillVAO(){
        vao = OBJLoader.loadObjModel("res/models/sun.obj");
    }

    public void update(){

    }

    public void render(Shaderprogram shader, float size){
        shader.useProgram();
        texture.bind(GL_TEXTURE0);
        material.bind(shader);

        Matrix4f model_matrix = new Matrix4f().translate(-10.0f, 8.0f, -(size/2 + 10)).scale(1.0f);
        shader.setUniformMat4f("model_matrix", model_matrix);
        vao.render();
    }
}
