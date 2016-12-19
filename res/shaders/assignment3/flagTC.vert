#version 330 core

layout (location = 0) in vec3 position;
layout (location = 1) in vec2 tcIn;

//TODO get the texture-coordinates flagTC.vert
        // get the texture-coordinates from the vao

//TODO get uniform flagTC.vert
        // receive the uniform send by the Flag-Object
        
uniform vec2 flagAnimation;

//TODO pass values flagTC.vert
        // pass the texture-coordinates (tc) to following shaders (the fragment shader)

out vec2 tc;

void main(){
      vec3 positionTemp = position;
      float x0;

      //the flag pole shouldnt move
      if(positionTemp.x <= 0.05f){
           x0 = 0.0f;
      }else{
           x0 = positionTemp.x - 0.05f;
      }

      //TODO relocate position flagTC.vert
            // relocate the x and y positions.
            // use following function:
            // currentPosition += (x0 * motionMagnitude) * sin(flagParameter + (currentXPosition * a) + (currentYPosition / b))
            // a and b are experimental variables. Try values from 0 to 20 and select the ones you think fit the most.
            // (you need to relocate the x and y positions separately)
            // set the gl_position equal to the new position.
            
            float a = 20.0;
            float b = 1.0;
            
            positionTemp.x += (x0 * flagAnimation.y) * sin(flagAnimation.x + (positionTemp.x * a) + (positionTemp.y / b));
            positionTemp.y += (x0 * flagAnimation.y) * sin(flagAnimation.x + (positionTemp.x * a) + (positionTemp.y / b));
            
            gl_Position = vec4(positionTemp, 1.0);

      //TODO fill passed values flagTC.vert
            // fill the variables that get passed to the following shaders
            
            tc = tcIn;

}