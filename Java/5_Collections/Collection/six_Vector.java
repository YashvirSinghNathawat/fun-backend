import java.util.Stack;
import java.util.Vector;

public class six_Vector {
    public static void main(String[] args) {
        Vector<Integer> vec = new Vector<>();
        vec.add(0,10);   //Thread Safe

        Stack<Integer> st = new Stack<>();   // Stack extends Vector
        st.push(1);
        st.pop();
    }
}
