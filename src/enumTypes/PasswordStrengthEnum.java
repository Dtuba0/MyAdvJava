package enumTypes;

public enum PasswordStrengthEnum {
    LOW(10),//ORDINAL : 0
    MEDIUM(50),//1
    HIGH(100);//2

    private final int level;//field

    //paramli const
    PasswordStrengthEnum(int level) {
        this.level = level;
    }


    //getter
    public int getLevel() {
        return level;
    }

}
