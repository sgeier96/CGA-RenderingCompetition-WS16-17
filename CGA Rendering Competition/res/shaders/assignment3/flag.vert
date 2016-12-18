#version 330 core

layout (location = 0) in vec3 position;
//TODO get uniform flag.vert
        // receive the uniform send by the Flag-Object
        
uniform vec2 flagAnimation;

void main(){
      vec3 positionTemp = position;
      float x0;

      //the flag pole shouldnt move
      if(positionTemp.x <= 0.05f){
           x0 = 0.0f;
      }else{
           x0 = positionTemp.x - 0.05f;
      }


      //TODO relocate position flag.vert
              // relocate the x and y positions.
              // use following function:
              // (x0 * motionMagnitude) * sin( flagParameter + (currentXPosition * a) + (currentYPosition / b))
              // a and b are experimental variables. Try values from 0 to 20 and select the ones you think fit the most.
              // (you need to relocate the x and y positions separately)
              // set the gl_position equal to the new position.
			
			positionTemp.x += (x0 * flagAnimation.y) * sin(flagAnimation.x + (positionTemp.x * 1) + (positionTemp.y / 20));
            positionTemp.y += (x0 * flagAnimation.y) * sin(flagAnimation.x + (positionTemp.x * 1) + (positionTemp.y / 20));
			
			//position = vec3(positionTemp.x + (x0 * flagAnimation) * sin( flagAnimation + (positionTemp.x * 5) + (positionTemp.y / 10)),positionTemp.y + (x0 * flagAnimation) * sin( flagAnimation + (positionTemp.x * 5) + (positionTemp.y / 10)), 1.0); 
			
			gl_Position = vec4(positionTemp, 1.0);

}