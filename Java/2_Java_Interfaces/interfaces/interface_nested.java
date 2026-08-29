

interface Bird {
    public void canFly();

    interface NonFlyingBird {
        public void canRun();
    }
}


class Eagle implements Bird {
    @Override
    public void canFly() {

    }
}

class Emu implements Bird.NonFlyingBird {
    @Override
    public void canRun() {

    }
}

public class interface_nested {
    public static void main(String[] args) {
        Bird eagle = new Eagle();
        Bird.NonFlyingBird emu = new Emu();
    }
}