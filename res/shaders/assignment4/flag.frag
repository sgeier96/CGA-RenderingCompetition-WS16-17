#version 330 core

out vec4 color;

uniform sampler2D tex0;
uniform sampler2D tex1;

uniform float alpha;

in DATA{
    vec2 tc;
    vec4 position;
    vec2 wave;
} fs_in;

void main(){
       vec2 waveTemp = fs_in.wave;
       vec4 positionTemp = fs_in.position;
       float x0;

       if(positionTemp.x <= 0.05f){
           x0 = 0.0f;
       }else{
           x0 = positionTemp.x - 0.05f;
       }

       float brightnessFactor = x0*10 * waveTemp.y * cos(waveTemp.x + x0*10 + positionTemp.y/10);

       brightnessFactor *= 0.2f;

       vec4 tempColorTex0 = texture(tex0, fs_in.tc);
       vec4 tempColorTex1 = texture(tex1, fs_in.tc);

       if(tempColorTex0.w < 1.0){
            discard;
       }else{

           //TODO set the new color flag.frag
                // define the new color as an interpolation between both textures (use the alpha value)

            vec4 brightnessVec = vec4( brightnessFactor, brightnessFactor, brightnessFactor, 0.0);
        	color = (alpha * tempColorTex1 + (1 - alpha) * tempColorTex0);

      }
}
