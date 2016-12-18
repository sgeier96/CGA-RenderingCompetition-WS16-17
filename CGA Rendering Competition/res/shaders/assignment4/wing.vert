#version 330 core

layout (location = 0) in vec3 position;
layout (location = 1) in vec2 tcIn;

//TODO get the passed model matrix wing.vert

uniform mat4 modelMatrix;

out vec2 tc;

void main(){
//TODO set new position wing.vert
    // set the new vertex position defined by the model matrix and the current position

       gl_Position = modelMatrix * vec4(position, 1.0f);
       tc = tcIn;
}
