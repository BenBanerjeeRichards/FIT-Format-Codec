package main.java.com.benbr

enum BaseDataType {
    ENUM(0),
    SINT8(1),
    UINT8(2),
    SINT16(131),
    UINT16(132),
    SINT32(133),
    UINT32(134),
    STRING(7),
    FLOAT32(136),
    FLOAT64(137),
    UINT8Z(10),
    UINT16Z(139),
    UINT32Z(140),
    BYTE(13)

    private int value

    BaseDataType(int value) {
        this.value = value
    }

    int value() {
        return value
    }

    @Override
    String toString() {
        return this.value
    }
}