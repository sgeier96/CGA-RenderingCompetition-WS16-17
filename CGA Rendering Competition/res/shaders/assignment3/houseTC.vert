#version 330 core

layout (location = 0) in vec3 position;
layout (location = 1) in vec2 tcIn;

//TODO get the texture-coordinates houseTC.vert
        // get the texture-coordinates from the vao

//TODO pass values houseTC.vert
        // pass the texture-coordinates (tc) to following shaders (the fragment shader)

out vec2 tc;

void main(){
       //TODO set position houseTC.vert
            // set the gl_position equal to the position.
            gl_Position = vec4(position, 1.0);

       //TODO fill passed values houseTC.vert
            // fill the variables that get passed to the following shaders
            tc = tcIn;

}