package test;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Description:
 * @Date: 2024/6/27 16:09
 */
public class Test04 {
    public static void main(String[] args) {
        //extracted();
        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("2", null);
    }

    private static void extracted() {
        User user = new User();
        user.setAge(18);
        Optional.ofNullable(user)
                .map(a-> a.getAge())
                .ifPresent(a-> a += 10);
        //.ifPresent(a->System.out.println(a));
        Object o = Optional.ofNullable(null).orElseGet(()->{
            return "222";
        });
        System.out.println(o);
    }


    static class User {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

}
