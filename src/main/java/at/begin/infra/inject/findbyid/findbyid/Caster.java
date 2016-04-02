package at.begin.infra.inject.findbyid.findbyid;

public enum Caster {
    LongID(Long::parseLong);

    private final CasterInterface casterInterface;

    Caster(CasterInterface casterInterface) {
        this.casterInterface = casterInterface;
    }

    public Object cast(String parameter) {
        return casterInterface.cast(parameter);
    }
}
