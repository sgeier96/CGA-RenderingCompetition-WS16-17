#version 330 core

out vec4 color;

void main(){
       //TODO set the fragment color flag.frag
       //define the color. It should be depending on the x and y position of the fragment (gl_FragCoord)
       
       	float x = gl_FragCoord.x / 512;
        float y = gl_FragCoord.y / 512;
             
        color = vec4(x,y, 0.0, 1.0);
}