package Chapter4;

import java.awt.*;

// 아이템 16 - public 클래스에서는 public 필드가 아닌 접근자 메서드를 사용하라
public class Item16 {
    /* 1. public 클래스의 경우 필드에 직접 접근하기 보다는 접근자를 이용해서 접근해야 한다 */
    //1. 필드만 있는 경우  -> 캡슐화의 이점 제공 불가
    public class OnlyField {
        public double x; // x 변수의 이름을 z로 바꾸고 싶으면 API 도 변경되어야 한다.
        public double y;
    }

    //2 접근자와 변경자 메서드를 활용해 데이터 캡슐화
    public class GetterSetterMethod {
        private double a; // a 변수의 이름을 z로 바꾸고 싶으면 getA 메서드와 setA 메서드의 내부 구현을 바꾸면 된다
        private double b; // API는 변경되지 않아도 된다.-> 유연성을 얻음

        public GetterSetterMethod(double a, double b){ this.a = a; this.b = b; }

        public double getA() {
            // 부수 작업 수행 가능
            return a;
        }
        public double getB() { return b; }
        public void setA(double a) { this.a = a; }
        public void setB(double b) { this.b = b; }

    }

    // 3. 필드가 불변이면 필드를 직접 노출할 때의 단점이 조금은 줄어든다. 물론 그렇다고 안심할 순 없다.
    public class ImmutableField {
        public final int n;
        public final int m;

        public ImmutableField(int n, int m ){ this.n = n; this.m = m; }
    }

    /* 2. package-private 클래스 혹은 private 중첩 클래스라면 데이터 필드를 노출해도 괜찮다 */
    class PackagePrivateClass {
        // 어차피 해당 클래스는 외부에서 접근 불가 즉, 팀 내부에서만 사용하는 클래스이다.
        // 그래서 그나마 변경에 따른 사이드 이펙트가 제한적이다.
        public double x;
        public double y;
    }

    /* 3. Java 플랫폼 라이브러리에서 필드를 직접 노출한 클래스 예시 */
    public class DimensionExample {
        Dimension size = new Dimension(100, 100);

        // 필드에 직접 접근이 가능하다.
        int height = size.height;
        int width = size.width;
    }


    /* 4. 필드가 노출된 클래스를 사용할 때는 불안하므로 카피를 해서 써야 한다. 이게 꽤나 불편하고 성능 이슈가 조금 있다 */
    public class DimensionCopyExample {
        int width;
        int height;

        public void doSomething(Dimension d1) {
            // Dimension의 값을 그대로 사용하는 경우 불안하니까 복사해서 적용
            // 이런 방식으로 객체를 전달해야 할 경우 굳이 안만들어도 될 인스턴스를 생성해야 하므로 성능 이슈 존재
            // -> 물론 성능 저하를 불러 일으킬 정도가 되려면 수백만개의 객체가 만들어져야함
            Dimension d2 = new Dimension();
            d2.width = d1.width;
            d2.height = d1.height;
        }
    }
}






