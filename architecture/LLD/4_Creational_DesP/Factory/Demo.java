public class Demo {
    public static void main(String[] args) {
        ShapeFactory circleFactory = new CircleCreator();
        circleFactory.createShape().draw();

        ShapeFactory rectangleFactory = new RectangleCreator();
        rectangleFactory.createShape().draw();
    }
}
