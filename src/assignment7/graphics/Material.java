package assignment7.graphics;

/**
 * Created by Dennis Dubbert on 25.11.16.
 */
public class Material {
    private float shininess;

    public Material(float shininess) {
        this.shininess = shininess;
    }

    public float getShininess() {
        return shininess;
    }

    public void setShininess(float shininess) {
        this.shininess = shininess;
    }

    public void bind(Shaderprogram shader){
        shader.setUniform1f("shininess", shininess);
    }
}
