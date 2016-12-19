#version 330 core

layout (location = 0) in vec3 position;
layout (location = 1) in vec2 tcIn;
layout (location = 2) in vec3 normals;


out vec2 tc;
out vec3 norm;

uniform mat4 model_matrix;
uniform mat4 projection_matrix;

//TODO get matrix army.vert task 5.2.4
uniform mat4 view_matrix;

void main(){
       //TODO calculate gl_Position army.vert task 5.2.4
       gl_Position = projection_matrix * view_matrix * model_matrix * vec4(position, 1.0f);

       tc = tcIn;
       mat4 normal_Matrix = transpose(inverse(model_matrix));
       norm = (normal_Matrix * vec4(normals, 1.0)).xyz;
}