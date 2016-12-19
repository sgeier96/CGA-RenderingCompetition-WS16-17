#version 330 core
layout (location = 0) in vec3 position;

uniform mat4 model_matrix;

//TODO get matrices frustum.vert task 5.3.3
uniform mat4 projection_matrix;
uniform mat4 view_matrix;

void main() {
	//TODO calculate gl_Position frustum.vert task 5.3.3
	gl_Position = projection_matrix * view_matrix * model_matrix * vec4(position, 1.0f);

	//position ist die Position des aktuellen Punktes, den er aus dem vao bekommt
	//vec4(position, 1.0f) -> Homogene Variable wird eingfügt, sodass damit weiter gerechnet werden kann
	//Die model_matrix beinhaltet die ganzen Transformationen, die mit den verschiedenen Punkten multipliziert wird.
	//Mit der view_matrix wird die Kamera an den Ursprung verschoben und die Szene dementsprechend verschoben, sodass die Kamera wieder korrekt auf die Szene zeigt
	//Mit der projection_matrix wird eingegrenzt was genau angezeigt wird. Eingegrenzt wird mittels dem Sichtwinkel, dem Aspekt und dem near-Plane und der far-Plane

	//Notiz: In der projection_matrix muss der Sichtwinkel (fov) in Radianten und Fließkommadarstellung angegeben werden. (Mittels (float) Math.toRadians(fov))
}
