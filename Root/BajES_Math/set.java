package BajES_Math;

public class set {
    private String name;
    private set[] setElements; //Functions and complex numbers can't be in sets, mostly because you can do everything
    public set(String name, set[] setElements) {    //by just naming sets after functions and numbers and using them as proxies. 
        this.name = name;
        this.setElements = setElements;
    }
    public set union(String name, set set1, set set2) {
        set resultSet = new set(name, null);
        for (set element : set1.setElements) {
            resultSet.addElement(element);
        }
        for (set element : set2.setElements) {
            if (!resultSet.contains(element)) {
                resultSet.addElement(element);
            }
        }
        return resultSet;
    }
    public boolean contains(set element) {
        for (set el : setElements) {
            if (el.equals(element)) {
                return true;
            }
        }
        return false;
    }
    public void addElement(set element) {
        int newSize = setElements.length + 1;
        set[] newSetElements = new set[newSize];
        System.arraycopy(setElements, 0, newSetElements, 0, setElements.length);
        newSetElements[setElements.length] = element;
        setElements = newSetElements;
    }
    public String getName() {
        return name;
    }
    public set[] getSetElements() {
        return setElements;
    }
    public int getSize() {
        return setElements.length;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        for (int i = 0; i < setElements.length; i++) {
            sb.append(setElements[i].getName());
            if (i < setElements.length - 1) {
                sb.append(", ");
            }
        }
        sb.append(" }");
        return sb.toString();
    }
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof set)) return false;
        set otherSet = (set) obj;
        if (this.getSize() != otherSet.getSize()) return false;
        for (set element : this.setElements) {
            if (!otherSet.contains(element)) return false;
        }
        return true;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSetElements(set[] setElements) {
        this.setElements = setElements;
    }
    public set copy(String name) {
        set[] newElements = new set[this.setElements.length];
        System.arraycopy(this.setElements, 0, newElements, 0, this.setElements.length);
        return new set(name, newElements);
    }
    public set intersection(String name, set set1, set set2) {
        set resultSet = new set(name, null);
        for (set element : set1.setElements) {
            if (set2.contains(element)) {
                resultSet.addElement(element);
            }
        }
        return resultSet;
    }
    public set difference(String name, set set1, set set2) {
        set resultSet = new set(name, null);
        for (set element : set1.setElements) {
            if (!set2.contains(element)) {
                resultSet.addElement(element);
            }
        }
        return resultSet;
    }
    public set complement(String name, set universalSet, set subset) {
        return difference(name, universalSet, subset);
    }
    public set powerSet(String name) {
        int powerSetSize = (int) Math.pow(2, setElements.length);
        set[] powerSetElements = new set[powerSetSize];
        for (int i = 0; i < powerSetSize; i++) {
            set[] subsetElements = new set[Integer.bitCount(i)];
            int index = 0;
            for (int j = 0; j < setElements.length; j++) {
                if ((i & (1 << j)) > 0) {
                    subsetElements[index++] = setElements[j];
                }
            }
            powerSetElements[i] = new set(name + "_subset_" + i, subsetElements);
        }
        return new set(name, powerSetElements);
    }
    public set cartesianProduct(String name, set set1, set set2) {  //need to be careful here because mathematical sets are unordered
        int productSize = set1.getSize() * set2.getSize();
        set[] productElements = new set[productSize];
        int index = 0;
        for (set element1 : set1.setElements) {
            for (set element2 : set2.setElements) {
                set[] pair = new set[]{element1, element2};
                productElements[index++] = new set(name + "_pair_" + index, pair);
            }
        }
        return new set(name, productElements);
    }
    public set symmetricDifference(String name, set set1, set set2) {
        set unionSet = union(name + "_union", set1, set2);
        set intersectionSet = intersection(name + "_intersection", set1, set2);
        return difference(name, unionSet, intersectionSet);
    }
}
