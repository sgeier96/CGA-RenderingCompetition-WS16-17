package assignment7.utilities;


import assignment7.graphics.VertexArrayObject;
import org.joml.Vector3f;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class OBJLoader{

	public static VertexArrayObject loadObjModel(String filePath) {

		FileReader fr = null;
		try {
			fr = new FileReader(new File(filePath));
		} catch (FileNotFoundException e) {
			System.err.println("*OBJ-Datei " + filePath + " konnte nicht gefunden werden.");
			e.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(fr);
		String line;
		List<Vector3f> vertices = new ArrayList<Vector3f>();
		List<Vector3f> normals = new ArrayList<Vector3f>();
		List<Vector3f> textures = new ArrayList<Vector3f>();
		List<Integer> indices = new ArrayList<Integer>();
		ArrayList<String[]> facesArrayList = new ArrayList<String[]>();

		float[] verticesArray = null;
		float[] normalsArray = null;
		float[] texturesArray = null;
		int[] indicesArray = null;

		try {
			while ((line = reader.readLine()) !=null) {
//				line = reader.readLine();
				String[] currentLine = line.split(" ");
				switch(currentLine[0]){
				case"v":
					int i = 1;
					if(currentLine[1].isEmpty()) i = 2;
					Vector3f vertex = new Vector3f(Float.parseFloat(currentLine[i]), Float.parseFloat(currentLine[i+1]), Float.parseFloat(currentLine[i+2]));
					vertices.add(vertex);
					continue;
				case"vt":
					i = 1;
					if(currentLine[1].isEmpty()) i = 2;
					if(currentLine.length == 4){
						Vector3f texture = new Vector3f(Float.parseFloat(currentLine[i]), Float.parseFloat(currentLine[i+1]), Float.parseFloat(currentLine[i+2]));
						textures.add(texture);
					}
					if(currentLine.length == 3){
						Vector3f texture = new Vector3f(Float.parseFloat(currentLine[i]), Float.parseFloat(currentLine[i+1]), 0.0f);
						textures.add(texture);
					}					
					continue;
				case"vn":
					i = 1;
					if(currentLine[1].isEmpty()) i = 2;
					Vector3f normal = new Vector3f(Float.parseFloat(currentLine[i]), Float.parseFloat(currentLine[i+1]), Float.parseFloat(currentLine[i+2]));
					normals.add(normal);
					continue;
				case"f":
					texturesArray = new float[vertices.size() *3];
					normalsArray = new float[vertices.size() *3];
					break;
				default:
					if(!reader.ready())break;
					continue;
				}
				break;
			}


			while (line != null) {
				if (!line.startsWith("f ")) {
					line = reader.readLine();
					continue;
				}
				
				String[] currentLine = line.split(" ");
				int i = 1;
				if(currentLine[1].isEmpty()) i = 2;
				if(currentLine.length == 4){
					String[] vertex1 = currentLine[i].split("/");
					String[] vertex2 = currentLine[i+1].split("/");
					String[] vertex3 = currentLine[i+2].split("/");

					processVertex(vertex1, indices, textures, normals, texturesArray, normalsArray);
					processVertex(vertex2, indices, textures, normals, texturesArray, normalsArray);
					processVertex(vertex3, indices, textures, normals, texturesArray, normalsArray);
				}

				if(currentLine.length == 5){
					String[] vertex1 = currentLine[i].split("/");
					String[] vertex2 = currentLine[i+1].split("/");
					String[] vertex3 = currentLine[i+2].split("/");
					String[] vertex4 = currentLine[i+3].split("/");

					processVertex(vertex1, indices, textures, normals, texturesArray, normalsArray);
					processVertex(vertex2, indices, textures, normals, texturesArray, normalsArray);
					processVertex(vertex3, indices, textures, normals, texturesArray, normalsArray);
					processVertex(vertex4, indices, textures, normals, texturesArray, normalsArray);
				}

				line = reader.readLine();
			}
			
			reader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		verticesArray = new float[vertices.size() * 3];
		indicesArray = new int[indices.size()];

		int vertexPointer = 0;
		for (Vector3f vertex : vertices) {
			verticesArray[vertexPointer++] = vertex.x;
			verticesArray[vertexPointer++] = vertex.y;
			verticesArray[vertexPointer++] = vertex.z;
		}
		for (int i = 0; i < indices.size(); i++) {
			indicesArray[i] = indices.get(i);
		}

		VertexArrayObject vao = new VertexArrayObject();
		vao.bindData(verticesArray, 0, 3);
		vao.bindData(texturesArray, 1, 3);
		vao.bindData(normalsArray, 2, 3);
		vao.bindIndices(indicesArray);
		OpenGLUtils.checkForOpenGLError();
		return vao;
	}

	private static void processVertex(String[] vertexData,  List<Integer> indices,
			List<Vector3f> textures, List<Vector3f> normals,
			float[] texturesArray, float[] normalsArray) {

		int currentVertexPointer = Integer.parseInt(vertexData[0]) - 1;
		indices.add(currentVertexPointer);

		if(!textures.isEmpty()) {
			Vector3f currentTex = textures.get(Integer.parseInt(vertexData[1]) -1);
			texturesArray[currentVertexPointer * 3] = currentTex.x;
			texturesArray[currentVertexPointer * 3 + 1] = 1 - currentTex.y;
			texturesArray[currentVertexPointer * 3 + 2] = 2 - currentTex.z;
		}

		Vector3f currentNorm = normals.get(Integer.parseInt(vertexData[2]) -1);
		normalsArray[currentVertexPointer * 3] = currentNorm.x;
		normalsArray[currentVertexPointer * 3 + 1] = currentNorm.y;
		normalsArray[currentVertexPointer * 3 + 2] = currentNorm.z;
	}
}

