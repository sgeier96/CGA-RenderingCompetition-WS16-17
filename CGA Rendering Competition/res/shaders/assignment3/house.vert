#version 330 core

layout (location = 0) in vec3 position;

void main(){
       //TODO set position house.vert
             // set the variable gl_Position
             
             gl_Position = vec4(position, 1.0);
}