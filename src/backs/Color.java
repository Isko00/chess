package backs;

public enum Color {
    WHITE("W"), BLACK("B"), EMPTY(" ");

    // Every color has it's own mapping sign
    private String sign;

    // constructor 
    private Color(String sign) 
    { 
        this.sign = sign;
    }

    // Returns sign for mapping
    @Override
    public String toString() { 
        return sign;
    }
/*    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + (int)(sign.charAt(0));

        return result;
    }

    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;
        Color c = (Color) o;
        // field comparison
        return hashCode() == c.hashCode();
    }
*/
}
