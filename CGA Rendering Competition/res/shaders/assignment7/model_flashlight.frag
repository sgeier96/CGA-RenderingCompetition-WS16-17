#version 330 core

out vec4 color;

in vec2 tc;
in vec3 surface_normal;
in vec3 to_camera_vector;

//TODO input model_flashlight.frag

uniform sampler2D tex0;
uniform sampler2D tex1;
uniform float shininess;
uniform float width;
uniform float height;

void main(){


	vec3 light_color = texture(tex1, vec2(gl_FragCoord.x/width, gl_FragCoord.y/height)).xyz;
	//vec3 light_color = texture(tex1, -to_camera_vector.xy).xyz;
	vec3 to_light_vector = to_camera_vector;

	//TODO ambient light cube_flashlight.frag
	vec3     ambient_light            = light_color * 0.1f;

	//TODO diffuse light cube_flashlight.frag
	vec3 DiffuseTerm = texture(tex0, tc).xyz * light_color;
	//TODO specular light cube_flashlight.frag
	vec3 normalized_surface_normal = normalize(surface_normal);
	vec3 normalized_to_light_vector = normalize(to_light_vector);

	float cosAlpha = max(0.0, dot(normalized_surface_normal, normalized_to_light_vector));

	vec3 V = normalize(to_camera_vector);
	vec3 R = normalize(reflect(-normalized_to_light_vector, normalized_surface_normal));

	float cosBeta = max(0.0, dot(R,V));
	float cosBetak = pow(cosBeta, shininess);

	vec3 SpecularTerm = texture(tex0, tc).xyz * light_color;


	color = vec4(DiffuseTerm * cosAlpha, 1.0);
	color += vec4(SpecularTerm * cosBetak, 0.0);

	if(color.w < 1.0){
		discard;
	}
}
