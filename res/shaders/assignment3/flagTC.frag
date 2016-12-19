#version 330 core

out vec4 color;

uniform sampler2D tex;

//TODO get variables from .vert Shader flagTC.frag
        // get the variables that got passed by the vertex shader
        
in vec2 tc;

void main(){
       //TODO set the texture color flagTC.frag
              // define the color. It should be depending on the texture and the texture-coordinates
              // use: texture(index of active texture tex, texture-coordinates)
       color = texture(tex, tc);

       //dismisses all transparent parts of the texture
       if(color.w < 1.0){
            discard;
       }
}