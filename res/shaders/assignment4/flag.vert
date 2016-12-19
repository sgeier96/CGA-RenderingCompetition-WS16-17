#version 330 core

layout (location = 0) in vec3 position;
layout (location = 1) in vec2 tc;

uniform vec2 wave;

out DATA{
    vec2 tc;
    vec4 position;
    vec2 wave;
} vs_out;

void main(){
      vec4 positionTemp = vec4(position, 1.0f);
      float c = positionTemp.x;

      if(positionTemp.x <= 0.05f){
           c = 0.0f;
      }else{
           c = positionTemp.x - 0.05f;
      }

      positionTemp.y += (c * wave.y) * sin(wave.x + c*10 + positionTemp.y/10);
      positionTemp.x += (c * wave.y) * sin(wave.x + c*10 + position.y/10);

      gl_Position = positionTemp;

      vs_out.tc = tc;
      vs_out.position = positionTemp;
      vs_out.wave = wave;
}