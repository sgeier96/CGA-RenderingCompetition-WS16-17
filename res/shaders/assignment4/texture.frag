#version 330 core

out vec4 color;

uniform sampler2D tex0;
uniform sampler2D tex1;

uniform float alpha;

in vec2 tc;

void main(){
	vec4 tempColorTex0 = texture(tex0, tc);
	vec4 tempColorTex1 = texture(tex1, tc);

	//TODO set the new color texture.frag
	// define the new color as an interpolation between both textures (use the alpha value)

	//color = texture(tex0, tc);

	color = alpha * tempColorTex1 + (1 - alpha) * tempColorTex0;

	if(color.w < 1.0){
		discard;
	}
}
