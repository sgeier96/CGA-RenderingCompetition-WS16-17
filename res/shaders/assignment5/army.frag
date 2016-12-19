#version 330 core

out vec4 color;

uniform sampler2D tex0;

in vec2 tc;
in vec3 norm;

void main(){

       color = texture(tex0, tc);
       if(color.w < 1.0){
               discard;
       }
       vec3 n = normalize(norm);
       //color = vec4((n + vec3(1.0)) / 2, 1.0);
       float cosNZ = abs(dot(n, vec3(0,0,1)));
       color = color * cosNZ;
}