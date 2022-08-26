import java.util.HashMap;
import java.util.Map;

public class TestMapEntry {
    public static void main(String[] args) {
        Map<String, String> students = new HashMap<>();
        students.put("Max", "Pyatochkin");
        students.put("Ivan", "Zagoruyko");
        students.put("Sasha", "Baron Koehn");

        System.out.println(students.entrySet());
        System.out.println(students);

        // 1 способ получения значения по ключу
        // for (String s: students.keySet()) {
        //     String lastname = students.get(s);
        // }

        // 2 cпособ получения значения по ключу - более употребим
        //Map.Entry<K, V> - это интерфейс, который позволяет получить доступ к записям в словаре
        //мы создаем переменную student типа Map.Entry
        for (Map.Entry<String, String> student: students.entrySet()) {
            System.out.println(
                    "Student name is = " + student.getKey() + " and student last name is = " + student.getValue()
            );
        }
    }
}