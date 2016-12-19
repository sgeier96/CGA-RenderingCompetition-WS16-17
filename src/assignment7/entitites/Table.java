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
public class Table {
    private VertexArrayObject vao;
    private Texture texture;
    private Material material;

    public Table() {
        texture = new Texture("res/images/concrete.jpg");
        material = new Material(5f);
        fillVAO();
    }

    private void fillVAO(){
        vao = OBJLoader.loadObjModel("res/models/table.obj");
    }

    public void update(){

    }

    public void render(Shaderprogram shader, float size){
        shader.useProgram();
        texture.bind(GL_TEXTURE0);
        material.bind(shader);
        Matrix4f model_matrix;
        for(int i = 0; i<5; i++){
            model_matrix = new Matrix4f().translate(28, -10, i*(-10)).scale(0.3f);
            shader.setUniformMat4f("model_matrix", model_matrix);
            vao.render();
            model_matrix = new Matrix4f().translate(-19, -10, i*(-10)).scale(0.3f);
            shader.setUniformMat4f("model_matrix", model_matrix);
            vao.render();
        }

        model_matrix = new Matrix4f().translate(4.5f, -10, -(size/2 + 10)).scale(0.3f);
        shader.setUniformMat4f("model_matrix", model_matrix);
        vao.render();
    }
}
