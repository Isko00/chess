package backs;

/* Class for ArrayList of type <Tile>
*  with only difference in function 
*  indexOf(), this version looks for values
*  stored in Pair attribute of Tile object
*/

public class TileSet {
    int length = 0;
    Tile[] array = new Tile[length];

    // adds new object to the end of array
    public void add(Tile element) {
        Tile[] newArray = new Tile[length + 1];

        for (int i = 0; i < length; i++) {
            newArray[i] = array[i];
        }

        newArray[length] = element;
        length++;
        array = newArray;
    }

    /* inserts new object at given index, shifting all 
    *  next objects to right
    */
    public void add(int index, Tile element) {
        if (length < index || index < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            length++;
            Tile[] newArray = new Tile[length];
            for (int i = 0; i < length; i++) {
                if (i < index) {
                    newArray[i] = array[i];
                } else if (i == index) {
                    newArray[i] = element;
                } else {
                    newArray[i] = array[i - 1];
                }
            }
            array = newArray;
        }
    }

    // Getter for values by index
    public Tile get(int index) {
        return array[index];
    }

    // Setter for values by index
    public void set(int index, Tile element) {
        if (length < index || index < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            array[index] = element;
        }
    }

    // Returns size(number of elements) of array
    public int size() {return this.length;}

    /* Looks for first occurrence of given object
    *  in array, and returns it's index
    */
    public int indexOf(Tile element) {
        int answer = -1;

        // Compares by values stored in Pair attribute
        for (int i = 0; i < length; i++) {
            if (array[i].equals(element)) {
                answer = i;
                break;
            }
        }

        return answer;
    }

    public void erase(int index) {
        if (length < index || index < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            length--;
            Tile[] newArray = new Tile[length];
            for (int i = 0; i < length; i++) {
                if (i < index) {
                    newArray[i] = array[i];
                } else {
                    newArray[i] = array[i + 1];
                }
            }
            array = newArray;
        }
    }

    // Prints names and Pair values of all stored tiles
    public void printInConsole() {
        System.out.println("Elements:");

        for (int i = 0; i < length; i++) {
            Piece piece = array[i].getPiece();
            String name = piece.getName();
            Pair pair = array[i].getPair();
            System.out.println(i + ") " + name 
                    + " [" + pair.i() + ", " 
                    + pair.j() + "];");
        }
    }
}
