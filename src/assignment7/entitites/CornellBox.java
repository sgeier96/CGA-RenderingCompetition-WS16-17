package assignment7.entitites;

import assignment7.graphics.Material;
import assignment7.graphics.Shaderprogram;
import assignment7.graphics.Texture;
import assignment7.graphics.VertexArrayObject;
import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL13.GL_TEXTURE0;

/**
 * Created by Dennis Dubbert on 23.11.16.
 */
public class CornellBox {
    private VertexArrayObject vao;
    private Texture texture;
    private float[] vertexPositions, tcs, normals;
    private int[] indices;
    private float size;
    private Material material;


    public CornellBox(float size){
        this.size = size;
        vertexPositions = new float[]{
                size , size, size,
                -size , size , size,
                -size , -size, size,
                size , -size , size,
                size , -size , -size,
                size , size , -size,
                -size , size , -size,
                -size , -size , -size,
        };

        indices = new int[]{
                0, 1, 2,    2, 3, 0,
                0, 3, 4,    4, 5, 0,
                0, 5, 6,    6, 1, 0,
                1, 6, 7,    7, 2, 1,
                7, 4, 3,    3, 2, 7,
                4, 7, 6,    6, 5, 4
        };

        normals = new float[]{
                -0.577350f, -0.577350f, -0.577350f,
                0.577350f, -0.577350f, -0.577350f,
                0.577350f, 0.577350f, -0.577350f,
                -0.577350f, 0.577350f, -0.577350f,
                -0.577350f, 0.577350f, 0.577350f,
                -0.577350f, -0.577350f, 0.577350f,
                0.577350f, -0.577350f, 0.577350f,
                0.577350f, 0.577350f, 0.577350f
        };

        texture = new Texture(new String[]{"res/images/weltraum.png","res/images/weltraum.png","res/images/weltraum.png","res/images/weltraum.png","res/images/weltraum.png","res/images/weltraum.png"});

        material = new Material(5);

        fillVAO();
    }

    private void fillVAO(){

        vao = new VertexArrayObject();
        vao.bindData(vertexPositions, 0, 3);
        vao.bindData(vertexPositions, 1,3);
        vao.bindData(normals, 2, 3);
        vao.bindIndices(indices);
    }

    public void update(){
    }

    public void render(Shaderprogram shader){

        shader.useProgram();
        texture.bindCube(GL_TEXTURE0);
        material.bind(shader);

        Matrix4f model_matrix = new Matrix4f().translate(0, 300f, -1900f);
        shader.setUniformMat4f("model_matrix", model_matrix);
        vao.render();
    }
}
