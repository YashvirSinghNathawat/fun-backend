interface LivingThing {
    default boolean canBreathe() {
        return true;
    }
}

interface Bird extends LivingThing {
    boolean canBreathe();
}

class Eagle implements Bird {
    @Override
    public boolean canBreathe() {
        return true;
    }
}
