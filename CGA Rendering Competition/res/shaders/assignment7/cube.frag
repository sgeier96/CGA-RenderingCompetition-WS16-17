#version 330 core

out vec4 color;

in vec3 tc;
in vec3 surface_normal;
in vec3 to_camera_vector;
in vec3 to_light_vector;

//TODO input cube.frag

uniform samplerCube tex0;
uniform vec3 light_color;
uniform float shininess;

void main(){
	//TODO delete cube.frag

	vec3 normalized_surface_normal = normalize(surface_normal);
	vec3 normalized_to_light_vector = normalize(to_light_vector);

	float cosAlpha = max(0.0, dot(normalized_surface_normal, normalized_to_light_vector));

	//ambient light
	vec3     ambient_light            = light_color * 0.1f;

	//TODO diffuse light cube.frag
	vec3 DiffuseTerm = texture(tex0, tc).xyz * light_color;

	//TODO specular light cube.frag
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
