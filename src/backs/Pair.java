package backs;

public class Pair {
    // Stores only two coordinates
    private int i, j;

    // Constructor from separate integers
    public Pair(int i, int j) {
        this.i = i;
        this.j = j;
    }

    // Constructor from integer array of length two
    public Pair(int[] arr) {
        i = arr[0];
        j = arr[1];
    }

    // Getter for separate of both values
    public int i() {return this.i;}

    public int j() {return this.j;}

    // Getter for integer array of length two
    public int[] getArr() {
        int[] answer = {i, j};
        return answer;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + i;
		result = prime * result + j;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pair other = (Pair) obj;
		if (hashCode() != other.hashCode())
			return false;
		return true;
	}

	// Sum of two pair objects
	public Pair add(Pair pair) {
        Pair answer;

        answer = new Pair(i + pair.i(), j + pair.j());

        return answer;
    }
}
