package Map.Element;

public enum Elementary {

    null_(),
    Fire(),
    Water(),
    Earth(),
    Air(),
    Ether(),
    Abysse(),
    Rune();

    public static Elementary[] eff(Elementary e) {
        Elementary weak[] = new Elementary[2];
        switch (e) {
            case Fire:
                weak[0] = Air;
                weak[1] = Earth;
                return weak;

            case Water:
                weak[0] = Fire;
                weak[1] = null_;
                return weak;

            case Earth:
                weak[0] = Water;
                weak[1] = Air;
                return weak;

            case Air:
                weak[0] = Water;
                weak[1] = null_;
                return weak;

            case Ether:
                weak[0] = Fire;
                weak[1] = Earth;
                return weak;

            case Abysse:
                weak[0] = Ether;
                weak[1] = Rune;
                return weak;

            case Rune:
                weak[0] = Abysse;
                weak[1] = Ether;
                return weak;

            default:
                weak[0] = null_;
                weak[1] = null_;
                return weak;
        }
    }

    public static Elementary[] weakness(Elementary e) {
        Elementary weak[] = new Elementary[2];
        switch (e) {
            case Fire:
                weak[0] = Water;
                weak[1] = Ether;
                return weak;

            case Water:
                weak[0] = Air;
                weak[1] = Earth;
                return weak;

            case Earth:
                weak[0] = Fire;
                weak[1] = Ether;
                return weak;

            case Air:
                weak[0] = Fire;
                weak[1] = Earth;
                return weak;

            case Ether:
                weak[0] = Abysse;
                weak[1] = Rune;
                return weak;

            case Abysse:
                weak[0] = Ether;
                weak[1] = Rune;
                return weak;

            case Rune:
                weak[0] = Abysse;
                weak[1] = Ether;
                return weak;

            default:
                weak[0] = null_;
                weak[1] = null_;
                return weak;
        }
    }

    private Elementary() {

    }
}
