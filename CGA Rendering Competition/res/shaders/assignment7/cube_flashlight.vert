#version 330 core

layout (location = 0) in vec3 position;
layout (location = 1) in vec3 tcIn;
layout (location = 2) in vec3 normals;


out vec3 tc;
out vec3 surface_normal;
out vec3 to_camera_vector;

//TODO define output cube.vert

uniform mat4 model_matrix;
uniform mat4 projection_matrix;
uniform mat4 view_matrix;

void main(){
	mat4 model_view              = view_matrix * model_matrix;
	vec3 position_in_viewspace   = vec4(model_view * vec4(position, 1.0)).xyz;

	//TODO normal_matrix / surface_normal / vectors cube.vert

	vec4 n = vec4(normals, 0.0);
	mat4 normal_matrix = transpose(inverse(model_view));
	surface_normal = (normal_matrix * n).xyz;

	to_camera_vector = -position_in_viewspace.xyz;

	vec4 lp = view_matrix * vec4(to_camera_vector, 1.0);
	//to_light_vector = (lp - position_in_viewspace).xyz;

	gl_Position                  = projection_matrix * vec4(position_in_viewspace, 1.0);

	tc                           = tcIn;
}
