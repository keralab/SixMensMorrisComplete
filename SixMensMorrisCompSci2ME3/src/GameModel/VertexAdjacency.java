package GameModel;

/**
 * Data Structure for holding vertices adjacent to another
 * @see Direction
 */
public class VertexAdjacency {
	private final int v;
	private final Direction d;
	
	/**
	 * Construct a VertexAdjacency
	 * @param d - Direction the vertex v connects
	 * @param v - Connecting vertex
	 * @see Direction
	 */
	public VertexAdjacency(Direction d, int v){
		this.d = d;
		this.v = v;
	}

	/**
	 * Get Vertex that is adjacent
	 * @return - Vertex position that is adjacent
	 */
	public int getV() {
		return v;
	}

	/**
	 * Get Direction of Adjacency 
	 * @return - Direction of Adjacency
	 * @see Direction
	 */
	public Direction getD() {
		return d;
	}
}
