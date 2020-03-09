package utils;

public enum MorphologicalOperation {
    OPENING,
    CLOSING;

    public static MorphologicalOperation of(String key) {
        for (MorphologicalOperation entry : MorphologicalOperation.values()) {
            if (entry.toString().equals(key.toUpperCase())) {
                return entry;
            }
        }
        throw new IllegalArgumentException("Error: key is not a morphological operation");
    }
}
